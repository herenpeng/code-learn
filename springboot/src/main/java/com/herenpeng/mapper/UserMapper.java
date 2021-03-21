package com.herenpeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.herenpeng.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author herenpeng
 * @since 2021-03-21 22:57
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
