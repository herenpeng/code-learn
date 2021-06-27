# 什么是基类？

在 Web 项目中，基于实体类衍生出来的 Mapper 接口，Service 业务层， Controller 层等等，在一些功能上都会有一些基础的通用的代码。这些代码通用性强，重复率高，在每个类中写这些代码的意义不大。

而基类就是在这些通用代码的基础上，进行重复代码的抽取，从而实现了代码的复用，其它 Java 类只需要继承基类，就可以拥有这些通用代码的功能。


# 基类的抽取原则

- 重复性和通用性高的代码

- 对业务没有太大影响的代码

# Java 代码实现

## 实体基类

```java
@Data
public class BaseEntity implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 数据库数据创建时间
     */
    @JsonIgnore
    @ApiModelProperty(value = "数据库数据创建时间")
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 数据库数据插入用户主键，由MyBatisPlus拦截器处理，在数据插入时更新
     */
    @JsonIgnore
    @ApiModelProperty(value = "数据库数据插入用户主键")
    @TableField(value = "create_user_id", fill = FieldFill.INSERT)
    private Integer createUserId;

    /**
     * 数据库数据更新时间
     */
    @JsonIgnore
    @ApiModelProperty(value = "数据库数据更新时间")
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 数据库数据更新用户主键，由MyBatisPlus拦截器处理，在数据更新时更新
     */
    @JsonIgnore
    @ApiModelProperty(value = "数据库数据更新用户主键")
    @TableField(value = "update_user_id", fill = FieldFill.INSERT_UPDATE)
    private Integer updateUserId;

    /**
     * 数据库数据标识逻辑删除字段，0(false)为正常，1(true)为逻辑删除，默认为0(false)
     */
    @JsonIgnore
    @ApiModelProperty(value = "数据库数据标识逻辑删除字段")
    @TableLogic
    private Boolean deleted;

}
```

## Mapper 接口基类

> 本篇博客中的基类抽取本身就是基于 MyBatisPlus，在 MyBatisPlus 中 Mapper 层接口有一个通用基类：`com.baomidou.mybatisplus.core.mapper.BaseMapper`。
>
>本篇博客以该 Mapper 基类为主，不进行二次包装。


## Service 基类

在 MyBatisPlus 中有一个 Service 层的基类`com.baomidou.mybatisplus.extension.service.IService`，本篇博客对该基类进行了一个二次包装，嵌入了部分项目相关的代码。

#### BaseService

```java
public interface BaseService<T> extends IService<T> {
}
```

#### BaseServiceImpl

```java
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

    @Autowired
    protected HttpServletRequest request;

}
```

## Controller 基类

```java
public class BaseController<S extends BaseService<E>, E> {

    @Autowired
    protected S baseService;

    @LogOperation
    @ApiOperation(value = "[通用方法]通过主键获取一条对应实体类的数据库记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "实体主键", dataTypeClass = Integer.class, required = true)
    })
    @GetMapping("{id}")
    public ResponseData<E> getById(@PathVariable("id") Integer id) throws Exception {
        E entity = baseService.getById(id);
        return ResponseData.ok(entity);
    }

    @LogOperation
    @ApiOperation(value = "[通用方法]插入一条对应实体类的数据库记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entity", value = "实体对象", dataTypeClass = Object.class, required = true)
    })
    @PostMapping
    public ResponseData<Void> save(@RequestBody E entity) throws Exception {
        baseService.save(entity);
        return ResponseData.ok().message("添加成功");
    }


    @LogOperation
    @ApiOperation(value = "[通用方法]更新一条对应实体类的数据库记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entity", value = "实体对象", dataTypeClass = Object.class, required = true)
    })
    @PutMapping
    public ResponseData<Void> updateById(@RequestBody E entity) throws Exception {
        baseService.updateById(entity);
        return ResponseData.ok().message("更新成功");
    }


    @LogOperation
    @ApiOperation(value = "[通用方法]通过主键删除一条对应实体类的数据库记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "实体主键", dataTypeClass = Integer.class, required = true)
    })
    @DeleteMapping("{id}")
    public ResponseData<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
        baseService.removeById(id);
        return ResponseData.ok().message("删除成功");
    }

}
```

## 基类的使用

在这些基类中，抽取了一些通用的代码，我们在写一个类的时候，只需要简单的继承对应的基类，就可以自动基础这些通用的代码和 API 接口，可以省下这些简单代码的编写时间。

例如：

```java
@Api(value = "用户操作接口", tags = "UserController")
@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserService, User> {
}
```

```java
public interface UserService extends BaseService<User> {
}
```

```java
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
}
```

```java
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
}
```

```java
@ApiModel(value = "用户信息实体类")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@TableName("auth_user")
public class User extends BaseEntity {
}
```

# 最后

> 本文**GitHub** [https://github.com/herenpeng/code-learn](https://github.com/herenpeng/code-learn) 已收录，欢迎**Star**。