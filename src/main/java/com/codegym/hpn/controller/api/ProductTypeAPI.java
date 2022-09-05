package com.codegym.hpn.controller.api;

import com.codegym.hpn.model.ProductType;
import com.codegym.hpn.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productTypes")
public class ProductTypeAPI {
    @Autowired
    IProductTypeService productTypeService;

    @GetMapping
    public ResponseEntity<?> getAllProductTypes () {
        Iterable<ProductType> productTypeList = productTypeService.findAll();
        return new ResponseEntity<>(productTypeList, HttpStatus.OK);
    }
}
