package com.example.repo.service;

import com.example.dto.ProductDto;
import com.example.response.ObjectResponse;

public interface IRepoService {
    ObjectResponse decreaseRepo(ProductDto productDto);
}
