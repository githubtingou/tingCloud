package com.ting.security.service.impl;

import com.ting.security.domain.entity.User;
import com.ting.security.mapper.UserMapper;
import com.ting.security.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ting
 * @since 2021-02-19
 */
@Service(value = "userServiceImpl")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
