package com.herenpeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.herenpeng.entity.User;
import com.herenpeng.mapper.UserMapper;
import com.herenpeng.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author herenpeng
 * @since 2021-03-21 23:12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
