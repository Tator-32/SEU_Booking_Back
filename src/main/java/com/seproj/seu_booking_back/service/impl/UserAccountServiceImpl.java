package com.seproj.seu_booking_back.service.impl;

import com.seproj.seu_booking_back.service.IUserAccountService;
import com.seproj.seu_booking_back.entity.UserAccount;
import com.seproj.seu_booking_back.mapper.UserAccountMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Tator
 * @since 2023-05-24
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements IUserAccountService {

}
