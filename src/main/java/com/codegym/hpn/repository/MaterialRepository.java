package com.codegym.hpn.repository;

import com.codegym.hpn.model.Material;
import com.codegym.hpn.model.ProductType;
import com.codegym.hpn.model.dto.MaterialDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    boolean existsByMaterialNameAndMaterialContentAndPricePerUnitAndProductTypeAndProductionDateAndExpirationDateAndDeletedFalse(String materialName, double materialContent, BigDecimal pricePerUnit, ProductType productType, LocalDate productionDate, LocalDate expirationDate);

    boolean existsByMaterialNameAndMaterialContentAndPricePerUnitAndProductTypeAndProductionDateAndExpirationDateAndDeletedFalseAndIdIsNot(String materialName, double materialContent, BigDecimal pricePerUnit, ProductType productType, LocalDate productionDate, LocalDate expirationDate, Long id);

    @Query("SELECT NEW com.codegym.hpn.model.dto.MaterialDTO (" +
            "d.id, " +
            "d.materialName, " +
            "d.materialContent, " +
            "d.quantity, " +
            "d.pricePerUnit, " +
            "d.expirationDate " +
            ")" +
            "FROM Material AS d " +
            "WHERE d.deleted = FALSE ")
    List<MaterialDTO> getAllMaterialDTO();

    Optional<Material> findByIdAndDeletedFalse(Long id);
}
