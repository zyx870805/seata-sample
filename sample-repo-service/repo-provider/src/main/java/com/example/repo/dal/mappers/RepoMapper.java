package com.example.repo.dal.mappers;

import org.apache.ibatis.annotations.Param;

public interface RepoMapper {
    int decreaseRepo(@Param("productCode") String productCode, @Param("count") Integer count);
}
