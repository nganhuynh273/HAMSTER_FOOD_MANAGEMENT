package com.codegym.hpn.service.impl;

import com.codegym.hpn.model.ProductType;
import com.codegym.hpn.repository.ProductTypeRepository;
import com.codegym.hpn.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductTypeService implements IProductTypeService {
    @Autowired
    ProductTypeRepository productTypeRepository;

    @Override
    public Iterable<ProductType> findAll() {
        return productTypeRepository.findAll();
    }

    @Override
    public Optional<ProductType> findById(Long id) {
        return productTypeRepository.findById(id);
    }

    @Override
    public ProductType getById(Long id) {
        return null;
    }

    @Override
    public ProductType save(ProductType productType) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
