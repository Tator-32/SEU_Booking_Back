package com.seproj.seu_booking_back.service.impl;

import com.seproj.seu_booking_back.service.IUserOrderService;
import com.seproj.seu_booking_back.entity.UserOrder;
import com.seproj.seu_booking_back.mapper.UserOrderMapper;
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
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements IUserOrderService {

}
