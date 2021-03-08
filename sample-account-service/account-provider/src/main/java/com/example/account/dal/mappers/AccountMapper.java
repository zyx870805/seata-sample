package com.example.account.dal.mappers;

import org.apache.ibatis.annotations.Param;

public interface AccountMapper {
    int decreaseAccount(@Param("userId") String userId, @Param("balance") Double balance);
}
