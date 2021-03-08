package com.example.account.service.impl;

import com.example.account.service.IAccountService;
import com.example.constants.ResCode;
import com.example.dto.AccountDto;
import com.example.account.dal.mappers.AccountMapper;
import com.example.response.ObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountMapper accountMapper ;

    @Override
    public ObjectResponse decreaseAccount(AccountDto accountDto) {
        ObjectResponse response = new ObjectResponse();
        try {
            int rs = accountMapper.decreaseAccount(accountDto.getUserId(), accountDto.getBalance().doubleValue());
            if (rs > 0) {
                response.setMsg(ResCode.SUCCESS.getMessage());
                response.setCode(ResCode.SUCCESS.getCode());
                return response;
            }
            response.setMsg(ResCode.FAILED.getMessage());
            response.setCode(ResCode.FAILED.getCode());
        } catch (Exception e) {
            log.error("decreaseAccount Occur Exception: ", e);
            response.setCode(ResCode.SYSTEM_EXCEPTION.getCode());
            response.setMsg(ResCode.SYSTEM_EXCEPTION.getMessage());
        }
        return response;
    }
}
