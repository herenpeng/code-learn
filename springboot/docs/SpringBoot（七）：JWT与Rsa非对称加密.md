# 什么是JWT？

Json web token (JWT), 是为了在网络应用环境间传递声明而执行的一种基于JSON的开放标准（(RFC 7519)。该token被设计为紧凑且安全的，特别适用于分布式站点的单点登录（SSO）场景。JWT的声明一般被用来在身份提供者和服务提供者间传递被认证的用户身份信息，以便于从资源服务器获取资源，也可以增加一些额外的其它业务逻辑所必须的声明信息，该token也可直接被用于认证，也可被加密。

# 什么是Rsa非对称加密？

非对称加密算法需要两个密钥来进行加密和解密，这两个秘钥是公开密钥（public key，简称公钥）和私有密钥（private key，简称私钥）。

公开密钥与私有密钥是一对，如果用公开密钥对数据进行加密，只有用对应的私有密钥才能解密；如果用私有密钥对数据进行加密，那么只有用对应的公开密钥才能解密。因为加密和解密使用的是两个不同的密钥，所以这种算法叫做非对称加密算法。

# Java 实现

## 引入依赖

```xml
<properties>
    <jjwt.version>0.11.2</jjwt.version>
</properties>

<dependencies>
    <!--jjwt，Java对JWT的封装工具依赖-->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>${jjwt.version}</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <version>${jjwt.version}</version>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <version>${jjwt.version}</version>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

## application.yml配置文件

```yaml
zero:
  auth:
    security:
      jwt:
        ttl: 3600000
        issuer: ${zero.domain-name}
        key: accessToken
        rsa:
          secret: HdGkiOiI4ODgiLCJzdWIiOiLlsIdkdusdmnwlsYdfDSkpqlzelqJhdSKDxljHlNKKjds
          private-file: G:\\tmp\\rsa\\rsa.pri
          public-file: G:\\tmp\\rsa\\rsa.pub
```

## JwtProperties

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "zero.auth.security.jwt")
public class JwtProperties {

    /**
     * Token过期时间，单位为毫秒，默认为1个小时
     */
    private Long ttl;

    /**
     * token的签发者
     */
    private String issuer;

    /**
     * token名称；accessToken
     */
    private String key;

}
```

## RsaProperties

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "zero.auth.security.jwt.rsa")
public class RsaProperties {

    /**
     * 加密秘钥信息
     */
    private String secret;
    /**
     * 私钥存放路径
     */
    private String privateFile;
    /**
     * 公钥存放路径
     */
    private String publicFile;

}
```


## RsaUtils 工具类

```java
@Slf4j
@RequiredArgsConstructor
@Component
public class RsaUtils {

    private final RsaProperties rsaProperties;

    /**
     * 默认的密文长度，在默认的密文长度和指定的密文长度之间选择最大的值，即密文长度不可以小于默认的密文长度
     */
    private static final int DEFAULT_KEY_SIZE = 2048;
    /**
     * 默认的加密算法
     */
    private static final String DEFAULT_KEY_ALGORITHM = "RSA";

    /**
     * 私钥对象
     */
    @Getter
    private PrivateKey privateKey;
    /**
     * 公钥对象
     */
    @Getter
    private PublicKey publicKey;

    /**
     * 在创建对象之后生成秘钥和公钥文件
     * 该方法用于初始化公钥和私钥的内容，在对象创建之后加载，之间使用属性缓存起来，这样就不需要每次都进行读取
     */
    @PostConstruct
    public void initRsaKey() throws Exception {
        // 生成私钥和公钥文件
        generateKey(rsaProperties.getPublicFile(), rsaProperties.getPrivateFile(), rsaProperties.getSecret());
        // 读取私钥对象
        if (StringUtils.isNotBlank(rsaProperties.getPrivateFile())) {
            privateKey = getPrivateKey(rsaProperties.getPrivateFile());
        }
        // 读取公钥对象
        if (StringUtils.isNotBlank(rsaProperties.getPublicFile())) {
            publicKey = getPublicKey(rsaProperties.getPublicFile());
        }
    }

    /**
     * 从文件中读取公钥
     *
     * @param filePath 公钥保存路径，相对于classpath
     * @return 公钥对象
     * @throws Exception 抛出异常
     */
    private PublicKey getPublicKey(String filePath) throws Exception {
        byte[] bytes = readFile(filePath);
        bytes = Base64.getDecoder().decode(bytes);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance(DEFAULT_KEY_ALGORITHM);
        return factory.generatePublic(spec);
    }

    /**
     * 从文件中读取密钥
     *
     * @param filePath 私钥保存路径，相对于classpath
     * @return 私钥对象
     * @throws Exception
     */
    private PrivateKey getPrivateKey(String filePath) throws Exception {
        byte[] bytes = readFile(filePath);
        bytes = Base64.getDecoder().decode(bytes);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance(DEFAULT_KEY_ALGORITHM);
        return factory.generatePrivate(spec);
    }

    /**
     * 根据密文，生成 rsa 公钥和私钥，并写入指定文件
     * 重载方法，使用默认的密文长度
     *
     * @param publicKeyFilePath  公钥文件路径
     * @param privateKeyFilePath 私钥文件路径
     * @param secret             生成密钥的密文
     * @throws Exception 抛出异常
     */
    private void generateKey(String publicKeyFilePath, String privateKeyFilePath, String secret) throws Exception {
        generateKey(publicKeyFilePath, privateKeyFilePath, secret, DEFAULT_KEY_SIZE);
    }

    /**
     * 根据密文，生成 rsa 公钥和私钥，并写入指定文件
     *
     * @param publicKeyFilePath  公钥文件路径
     * @param privateKeyFilePath 私钥文件路径
     * @param secret             生成密钥的密文
     * @param keySize            密文文长度
     * @throws Exception 抛出异常
     */
    private void generateKey(String publicKeyFilePath, String privateKeyFilePath, String secret, int keySize) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(secret.getBytes());
        keyPairGenerator.initialize(Math.max(keySize, DEFAULT_KEY_SIZE), secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        // 获取公钥并写出
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        publicKeyBytes = Base64.getEncoder().encode(publicKeyBytes);
        writeFile(publicKeyFilePath, publicKeyBytes);
        // 获取私钥并写出
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        privateKeyBytes = Base64.getEncoder().encode(privateKeyBytes);
        writeFile(privateKeyFilePath, privateKeyBytes);
    }

    /**
     * 读取对应文件的数据，并返回字节数组
     *
     * @param filePath 文件路径
     * @return 文件字节数组
     * @throws Exception 抛出异常
     */
    private byte[] readFile(String filePath) throws Exception {
        return Files.readAllBytes(new File(filePath).toPath());
    }

    /**
     * 将一个字节数组写入到一个文件中
     *
     * @param destPath 写入文件路径
     * @param bytes    字节数组
     * @throws IOException IO异常
     */
    private void writeFile(String destPath, byte[] bytes) throws IOException {
        File dest = new File(destPath);
        File parentFile = dest.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        if (!dest.exists()) {
            dest.createNewFile();
        }
        Files.write(dest.toPath(), bytes);
    }

}
```

## JwtUtils 工具类

```java
@Slf4j
@RequiredArgsConstructor
@Component
public class JwtUtils {

    private final JwtProperties jwtProperties;

    private final RsaUtils rsaUtils;

    /**
     * 创建JWT
     *
     * @param id        JWT的ID
     * @param subject   JWT主体信息，一般以Json格式的数据存储
     * @param issuer    JWT签发者
     * @param ttlMillis JWT的有效时间，单位是毫秒
     * @param claims    创建payload的私有声明，也就是自定义的JWT信息
     * @return 返回JWT
     * @throws Exception 抛出异常
     */
    public String createJWT(String id, String subject, String issuer, Long ttlMillis, Map<String, Object> claims) {
        // 指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS256;

        // 下面就是在为payload添加各种标准声明和私有声明了,这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder();
        // 设置签名使用的签名算法和签名使用的秘钥
        builder.signWith(rsaUtils.getPrivateKey(), signatureAlgorithm);
        // jti：设置JWT的ID，是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击
        builder.setId(id);
        // iat：JWT的签发时间，生成JWT的时间
        builder.setIssuedAt(new Date());
        // iss：jwt签发人
        builder.setIssuer(issuer);
        // sub：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放用户信息的，作为什么用户的唯一标志
        builder.setSubject(subject);
        if (ObjectUtils.allNotNull(claims)) {
            // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
            builder.setClaims(claims);
        }

        // exp：设置过期时间
        if (ttlMillis >= 0) {
            long expiredMillis = System.currentTimeMillis() + ttlMillis;
            Date expiredDate = new Date(expiredMillis);
            builder.setExpiration(expiredDate);
        }
        return builder.compact();
    }

    /**
     * 创建JWT，默认配置了issuer（JWT签发者）
     *
     * @param id        JWT的ID
     * @param subject   JWT主体信息，一般以Json格式的数据存储
     * @param ttlMillis JWT的有效时间，单位是毫秒
     * @param claims    创建payload的私有声明，也就是自定义的JWT信息
     * @return 返回JWT
     * @throws Exception 抛出异常
     */
    public String createJWT(String id, String subject, Long ttlMillis, Map<String, Object> claims) {
        return createJWT(id, subject, jwtProperties.getIssuer(), ttlMillis, claims);
    }

    /**
     * 创建JWT，默认配置了issuer（JWT签发者），ttlMillis（有效时间）
     *
     * @param id      JWT的ID
     * @param subject JWT主体信息，一般以Json格式的数据存储
     * @param claims  创建payload的私有声明，也就是自定义的JWT信息
     * @return 返回JWT
     * @throws Exception 抛出异常
     */
    public String createJWT(String id, String subject, Map<String, Object> claims) {
        return createJWT(id, subject, jwtProperties.getIssuer(), jwtProperties.getTtl(), claims);
    }

    /**
     * 创建JWT，默认配置了issuer（JWT签发者），ttlMillis（有效时间）,默认claims为null
     *
     * @param id      JWT的ID
     * @param subject JWT主体信息，一般以Json格式的数据存储
     * @return 返回JWT
     * @throws Exception 抛出异常
     */
    public String createJWT(String id, String subject) {
        return createJWT(id, subject, jwtProperties.getIssuer(), jwtProperties.getTtl(), null);
    }

    /**
     * 解密JWT，如果秘钥错误，或者JWT被修改过，会直接抛出异常信息
     *
     * @param jwt JWT字符串信息
     * @return 返回JWT载荷信息
     */
    public Claims parseJWT(String jwt) {
        // 得到DefaultJwtParser
        Claims claims = Jwts.parserBuilder()
                // 设置签名的秘钥
                .setSigningKey(rsaUtils.getPublicKey())
                .build()
                // 设置需要解析的jwt
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    /**
     * 判断jwt是否已经过期
     *
     * @param jwt JWT字符串信息
     * @return 如果jwt已经过期，返回true，否则返回false
     */
    public boolean isExpiration(String jwt) {
        Claims claims = parseJWT(jwt);
        return claims.getExpiration().before(new Date());
    }
}
```

# 最后

> 本文**GitHub** [https://github.com/herenpeng/code-learn](https://github.com/herenpeng/code-learn) 已收录，欢迎**Star**。
