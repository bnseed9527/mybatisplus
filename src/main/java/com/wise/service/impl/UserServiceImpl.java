package com.wise.service.impl;

import com.wise.entity.User;
import com.wise.mapper.UserMapper;
import com.wise.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YsCy丶
 * @since 2018-11-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
