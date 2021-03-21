package com.herenpeng;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.herenpeng.entity.User;
import com.herenpeng.mapper.UserMapper;
import com.herenpeng.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author herenpeng
 * @since 2021-03-21 22:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyBatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void test01() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test02() {
        List<User> users = userService.list();
        for (User user : users) {
            System.out.println(user);
        }
    }

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

    @Test
    public void test04() {
        userMapper.deleteById(23);
    }

}
