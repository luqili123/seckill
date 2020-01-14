package com.edu.nju.seckill.service.impl;

import com.edu.nju.seckill.dao.AddressMapper;
import com.edu.nju.seckill.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lql
 * @date 2020/1/11 20:20
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;
}
