package com.codegym.hpn.service.impl;

import com.codegym.hpn.model.Material;
import com.codegym.hpn.model.ProductType;
import com.codegym.hpn.model.dto.MaterialDTO;
import com.codegym.hpn.repository.MaterialRepository;
import com.codegym.hpn.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MaterialService implements IMaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public Iterable<Material> findAll() {
        return null;
    }

    @Override
    public List<MaterialDTO> getAllMaterialDTO() {
        return materialRepository.getAllMaterialDTO();
    }

    @Override
    public Optional<Material> findByIdAndDeletedFalse(Long id) {
        return materialRepository.findByIdAndDeletedFalse(id);
    }


    @Override
    public Optional<Material> findById(Long id) {
        return materialRepository.findById(id);
    }

    @Override
    public Material getById(Long id) {
        return null;
    }

    @Override
    public Material save(Material material) {
        return materialRepository.save(material);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public boolean isMaterialExisted(Material material) {
        String materialName = material.getMaterialName();
        double materialContent = material.getMaterialContent();
        BigDecimal pricePerUnit = material.getPricePerUnit();
        ProductType productType = material.getProductType();
        LocalDate productionDate = material.getProductionDate();
        LocalDate expirationDate = material.getExpirationDate();
        return materialRepository.existsByMaterialNameAndMaterialContentAndPricePerUnitAndProductTypeAndProductionDateAndExpirationDateAndDeletedFalse(materialName, materialContent, pricePerUnit, productType, productionDate, expirationDate);
    }

    public boolean isMaterialUpdatedExisted(Material material) {
        long id = material.getId();
        String materialName = material.getMaterialName();
        double materialContent = material.getMaterialContent();
        BigDecimal pricePerUnit = material.getPricePerUnit();
        ProductType productType = material.getProductType();
        LocalDate productionDate = material.getProductionDate();
        LocalDate expirationDate = material.getExpirationDate();
        return materialRepository.existsByMaterialNameAndMaterialContentAndPricePerUnitAndProductTypeAndProductionDateAndExpirationDateAndDeletedFalseAndIdIsNot(materialName, materialContent, pricePerUnit, productType, productionDate, expirationDate, id);
    }


}
