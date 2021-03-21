# MyBatisPlus

MyBatis-Plus （简称 MP）是一个 MyBatis 的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。

官方网站：[https://mp.baomidou.com](https://mp.baomidou.com)

在 MyBatisPlus 的官方网站上，已经对 MyBatisPlus 做了很多的详细介绍，在本篇文章中就不对 MyBatisPlus 做过多的介绍，有想要详细了解 MyBatisPlus 框架的同学可以访问 MyBatisPlus 官网。


## 引入依赖

> 本篇文章中 SpringBoot 版本为 2.3.8.RELEASE , MyBatisPlus 版本为 3.4.0 。

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.3.8.RELEASE</version>
    <relativePath/>
</parent>
<dependencies>
    <!--SpringMVC的启动器-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!--Spring单元测试的启动器-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <!--lombok的依赖-->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
    <!--MySQL数据库驱动-->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    <!--MyBatis-Plus的依赖-->
    <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.4.0</version>
    </dependency>
</dependencies>
```

## MyBatisPlus 相关配置

```yaml
server:
  # 项目启动端口
  port: 8080
  # 项目请求前缀
  servlet:
    context-path: /api
  # tomcat字符编码
  tomcat:
    uri-encoding: UTF-8
spring:
  application:
    # Spring应用名称
    name: zero-admin
  # Jackson相关配置
  jackson:
    # 常用，全局设置实体类或被@JsonInclude注解的属性的序列化方式
    default-property-inclusion: ALWAYS
    # 当地时区
    locale: zh
    # 设置全局时区
    time-zone: GMT+8
    # 全局设置@JsonFormat的格式pattern
    date-format: yyyy-MM-dd HH:mm:ss
  # 数据库连接信息
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/zero-admin?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: admin
# MyBatisPlus的相关配置
mybatis-plus:
  # Mapper文件路径，如果Mapper.xml配置文件路径和Mapper接口路径一致，可以不配置
  mapper-locations: classpath:/com/herenpeng/**/*Mapper.xml
  configuration:
    # 下划线转驼峰命名
    map-underscore-to-camel-case: true
    # 打印sql语句，将mybatis-plus自带的SQL打印注释，使用logback记录日志
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
  # 配置逻辑删除字段
  global-config:
    db-config:
      # 删除状态为1(true)
      logic-delete-value: true
      # 没有删除状态为0(false)
      logic-not-delete-value: false
```


## MyBatisPlus 实体类注解

- @TableName：表名称注解，value 属性为实体类对应的表名

- @TableId：表主键注解，value 属性为主键对应的数据库字段名，type 属性为主键的生成策略

- @TableField：表字段注解，value 属性为主键对应的数据库字段名

- @TableLogic：MyBatisPlus 逻辑删除字段标识注解

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("auth_user")
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "enabled")
    private Boolean enabled;

    @TableField(value = "locked")
    private Boolean locked;

    @TableField(value = "account_expire")
    private Boolean accountExpire;

    @TableField(value = "password_expire")
    private Boolean passwordExpire;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private Integer createUserId;

    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(value = "update_user_id", fill = FieldFill.INSERT_UPDATE)
    private Integer updateUserId;

    @TableLogic
    private Boolean deleted;
}
```

## CRUD 通用接口

MyBatisPlus 提供了一个 BaseMapper 接口，实体类 Mapper 层接口只要继承该 BaseMapper 接口，MyBatisPlus 即可为 Mapper 层接口生成简单的 CRUD 操作

```java
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
```

- 相关操作

```java
@Autowired
private UserMapper userMapper;

@Test
public void test01() {
    List<User> users = userMapper.selectList(null);
    for (User user : users) {
        System.out.println(user);
    }
}
```


BaseMapper 接口是持久层的通用方法接口，MyBatisPlus 还提供了 Service 层的接口和具体实现

```java
public interface UserService extends IService<User> {
}
```

```java
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
```

- 相关操作

```java
@Autowired
private UserService userService;

@Test
public void test02() {
    List<User> users = userService.list();
    for (User user : users) {
        System.out.println(user);
    }
}
```


## 分页插件

MyBatisPlus 内置了分页功能，只需要通过配置即可实现基于物理分页的分页功能。

- 分页配置

```java
@Configuration
public class MyBatisPlusConfig {

    /**
     * 高版本的MyBatisPlus分页插件
     *
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
```

相关操作

```java
@Autowired
private UserMapper userMapper;

@Test
public void test03() {
    IPage<User> page = new Page<>(1, 10);
    IPage<User> pageInfo = userMapper.selectPage(page, null);
    // 总条数
    System.out.println(pageInfo.getTotal());
    // 当前分页总页数
    System.out.println(pageInfo.getPages());
    // 当前页
    System.out.println(pageInfo.getCurrent());
    // 每页显示条数
    System.out.println(pageInfo.getSize());
    // 分页记录数据内容
    for (User user : pageInfo.getRecords()) {
        System.out.println(user);
    }
}
```

## 逻辑删除

MyBatisPlus 支持逻辑删除功能，如果使用了逻辑删除功能，凡是使用 BaseMapper 或者 IService 生成的删除操作，均为逻辑删除，如果是自己写的 SQL ，则还是需要开发者手动进行逻辑删除操作。


逻辑删除配置

```yaml
# MyBatisPlus的相关配置
mybatis-plus:
  # 配置逻辑删除字段
  global-config:
    db-config:
      # 删除状态为1(true)
      logic-delete-value: true
      # 没有删除状态为0(false)
      logic-not-delete-value: false
```

逻辑删除字段标识注解

```java
@TableLogic
private Boolean deleted;
```

逻辑删除配置

在 MyBatisPlus v3.1.1 版本之后，MyBatisPlus 默认支持逻辑删除，无需进行其他配置，如果低于该版本，需要进行如下配置：

```java
@Bean
public ISqlInjector sqlInjector() {
    return new LogicSqlInjector();
}
```

相关操作

```java
@Autowired
private UserMapper userMapper;

@Test
public void test04() {
    userMapper.deleteById(23);
}
```

> 使用该删除方法之后，只是将被 @TableLogic 注解的属性值修改为了 true （在数据库中表现为字段值由 0 改为了 1）



# 最后
 
> 本文**Github** [https://github.com/herenpeng/code-learn](https://github.com/herenpeng/code-learn) 已收录，欢迎**Star**。