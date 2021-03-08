package com.example.repo.service.impl;

import com.example.constants.ResCode;
import com.example.dto.ProductDto;
import com.example.repo.dal.mappers.RepoMapper;
import com.example.repo.service.IRepoService;
import com.example.response.ObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Service
public class RepoServiceImpl implements IRepoService {

    @Autowired
    private RepoMapper repoMapper;

    @Override
    public ObjectResponse decreaseRepo(ProductDto productDto) {
        ObjectResponse response = new ObjectResponse();
        try {
            int repo = repoMapper.decreaseRepo(productDto.getProductCode(), productDto.getCount());
            if (repo > 0) {
                response.setMsg(ResCode.SUCCESS.getMessage());
                response.setCode(ResCode.SUCCESS.getCode());
                return response;
            }
            response.setMsg(ResCode.FAILED.getMessage());
            response.setCode(ResCode.FAILED.getCode());
        }catch (Exception e) {
            log.error("decreaseRepo Order: {} Occur Exception: ", productDto, e);
            response.setCode(ResCode.SYSTEM_EXCEPTION.getCode());
            response.setMsg(ResCode.SYSTEM_EXCEPTION.getMessage() + " - " + ExceptionUtils.getFullStackTrace(e));
        }
        return response;
    }
}
