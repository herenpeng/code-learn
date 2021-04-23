> 作为一个前后端分离项目，必须要和前端规定统一的数据交互结构，这样可以更加清晰地进行前后端数据的通信。

# 统一响应结构体

在项目中，应该对响应给前端的 JSON 数据的格式进行统一的规定，在很多响应结构中，一般会包含 code、message、data 三个属性。

其中 code 代表状态码，这个不是 HTTP 的响应状态码，而是后端系统的业务状态码，代表了后端响应给前端的业务状态。

message 代表了后端给前端的响应信息，如果发生异常，或者系统错误，会将错误信息存储在 message 中，响应给前端。

data 代表了后端响应给前端的数据，如果请求获取了数据，会将数据放入 data 域中。



## JsonUtils

> JsonUtils 是对 Json 格式化工具的封装，这样封装的好处在于，一个是可以使 API 更加直观，另外则是可以在封装的工具类中添加系统自身的操作，尽可能地兼容本身的系统业务。


```java
@Slf4j
@RequiredArgsConstructor
@Component
public class JsonUtils {

    private final ObjectMapper objectMapper;

    /**
     * 将对象格式化为 JSON 格式的字符串
     *
     * @param object 对象
     * @return JSON 格式的字符串
     */
    public String toJson(final Object object) {
        if (ObjectUtils.isEmpty(object)) {
            log.info("[Json工具类]对象{}为空", object);
            return null;
        }
        if (object.getClass() == String.class) {
            return object.toString();
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("[Json工具类]将对象{}格式化为JSON格式的字符串失败", object);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将 JSON 格式的字符串转换为 Java 对象
     *
     * @param json      JSON 格式的字符串
     * @param classType Java 字节码对象
     * @param <T>       Java 对象的泛型
     * @return Java 对象
     */
    public <T> T toObject(final String json, final Class<T> classType) {
        if (StringUtils.isBlank(json)) {
            log.info("[Json工具类]JSON字符串{}为空", json);
            return null;
        }
        try {
            return objectMapper.readValue(json, classType);
        } catch (JsonProcessingException e) {
            log.error("[Json工具类]将JSON格式的字符串{}格式化为{}类型的Java对象失败", json, classType);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将指定输入流解析为指定类型 Java 对象
     *
     * @param request HttpServletRequest对象
     * @param classType   Java 字节码对象
     * @return Java 对象的泛型
     */
    public <T> T toObject(final HttpServletRequest request, final Class<T> classType) {
        try {
            return objectMapper.readValue(request.getInputStream(), classType);
        } catch (IOException e) {
            log.error("[Json工具类]将输入流{}格式化为{}类型的Java对象失败", request, classType);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将指定输入流解析为指定类型 Java 对象
     *
     * @param inputStream 输入流对象
     * @param classType   Java 字节码对象
     * @return Java 对象的泛型
     */
    public <T> T toObject(final InputStream inputStream, final Class<T> classType) {
        try {
            return objectMapper.readValue(inputStream, classType);
        } catch (IOException e) {
            log.error("[Json工具类]将输入流{}格式化为{}类型的Java对象失败", inputStream, classType);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将 JSON 格式的字符串转换为 Map 类型的对象
     *
     * @param json JSON 格式的字符串
     * @return Map 类型的对象
     */
    public Map toMap(final String json) {
        if (StringUtils.isBlank(json)) {
            log.info("[Json工具类]JSON字符串{}为空", json);
            return null;
        }
        try {
            return objectMapper.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            log.error("[Json工具类]将JSON格式的字符串{}格式化为Map类型的Java对象失败", json);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将一个 Java 对象转换为 Map 类型的对象
     *
     * @param object Java 对象
     * @return Map 类型的对象
     */
    public Map toMap(final Object object) {
        if (ObjectUtils.isEmpty(object)) {
            log.info("[Json工具类]对象{}为空", object);
            return null;
        }
        String json = toJson(object);
        return toMap(json);
    }
    
}
```

## ResponseUtils

> ResponseUtils 工具类是对响应过程的封装，可以将一个 Java 对象转换为一个 JSON 格式的字符串响应给前端。

```java
@Slf4j
@RequiredArgsConstructor
@Component
public class ResponseUtils {

    private final JsonUtils jsonUtils;

    /**
     * response对象返回json数据给前端的封装方法，将object参数转换为json数据，并返回给前台
     *
     * @param response HttpServletResponse对象
     * @param object   需要返回给前端的对象，方法会将该对象转换为json数据返回给前端
     * @throws IOException IO异常
     */
    public void responseJson(HttpServletResponse response, Object object) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(EncodingEnums.UTF_8.getValue());
        PrintWriter writer = response.getWriter();
        writer.write(jsonUtils.toJson(object));
        writer.flush();
        writer.close();
    }

}
```

## 响应状态码

```java
@Getter
@AllArgsConstructor
public enum CodeEnum {
    /**
     * code为20000，代表正常返回数据
     */
    OK(20000);

    private final Integer value;
}
```

## 响应结构体

```java
@ApiModel(value = "响应数据实体类")
@Data
public class ResponseData<T> implements Serializable {

    /**
     * 业务状态码，区别于HTTP协议状态码，
     */
    @ApiModelProperty(value = "业务状态码")
    private Integer code;

    /**
     * 业务提示消息，一般在增删改操作的时候返回
     */
    @ApiModelProperty(value = "业务提示消息")
    private String message;

    /**
     * 返回前端的交互数据
     */
    @ApiModelProperty(value = "业务响应数据")
    private T data;

    private static ResponseData responseData;

    /**
     * 私有构造方法，这样就只能够通过静态方法code()和ok()创建ResponseData对象
     */
    private ResponseData() {
    }

    /**
     * 静态方法
     *
     * @param code 业务状态码
     * @param <T>  返回数据的泛型
     * @return 返回一个业务状态为code的ResponseData对象
     */
    public static <T> ResponseData<T> code(Integer code) {
        responseData = new ResponseData();
        responseData.setCode(code);
        return responseData;
    }

    /**
     * 非静态方法，设置响应体的业务提示消息
     *
     * @param message 业务提示消息
     * @param <T>     返回数据的泛型
     * @return 返回一个返回数据为data的ResponseData对象
     */
    public <T> ResponseData<T> message(String message) {
        responseData.setMessage(message);
        return responseData;
    }

    /**
     * 非静态方法
     *
     * @param data 返回数据
     * @param <T>  返回数据的泛型
     * @return 返回一个返回数据为data的ResponseData对象
     */
    public <T> ResponseData<T> data(T data) {
        responseData.setData(data);
        return responseData;
    }

    /**
     * 静态方法
     *
     * @param <T> 返回数据的泛型
     * @return 返回一个业务状态为20000，返回数据为null的ResponseData对象
     */
    public static <T> ResponseData<T> ok() {
        return code(CodeEnum.OK.getValue());
    }

    /**
     * 静态方法
     *
     * @param data 返回数据
     * @param <T>  返回数据的泛型
     * @return 返回一个业务状态为20000，返回数据为data的ResponseData对象
     */
    public static <T> ResponseData<T> ok(T data) {
        return ok().data(data);
    }

}
```

## 统一响应结构体代码示例

```java
@GetMapping("page/{currentPage}")
public ResponseData<IPage<User>> page(
        @PathVariable("currentPage") Integer currentPage,
        @RequestParam(value = "size", defaultValue = "10") Integer size,
        User queryUser) throws Exception {
    IPage<User> page = baseService.page(currentPage, size, queryUser);
    return ResponseData.ok(page);
}
```

```java
@PutMapping("enabled/{id}")
public ResponseData<Void> enabled(
        @PathVariable("id") Integer id,
        @RequestParam("enabled") Boolean enabled) throws Exception {
    baseService.enabled(id, enabled);
    return ResponseData.ok().message("更新用户状态成功");
}
```


# 最后
 
> 本文**GitHub** [https://github.com/herenpeng/code-learn](https://github.com/herenpeng/code-learn) 已收录，欢迎**Star**。