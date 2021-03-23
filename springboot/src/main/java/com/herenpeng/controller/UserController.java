package com.herenpeng.controller;

import com.herenpeng.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author herenpeng
 * @since 2021-03-23 21:25
 */
@Api(value = "用户操作接口", tags = "UserController")
@RestController
@RequestMapping("user")
public class UserController {

    @ApiOperation(value = "分页查询用户数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户主键", dataTypeClass = Integer.class, required = true)
    })
    @GetMapping("{id}")
    public User get(@PathVariable("id") Integer id) {
        User user = new User();
        user.setId(id);
        user.setUsername("Hello");
        user.setPassword("111111");
        return user;
    }

}
