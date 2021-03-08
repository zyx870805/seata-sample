package com.example.account.service;

import com.example.dto.AccountDto;
import com.example.response.ObjectResponse;

public interface IAccountService {

    ObjectResponse decreaseAccount(AccountDto accountDto);
}
