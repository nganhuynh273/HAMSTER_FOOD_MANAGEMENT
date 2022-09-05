package com.codegym.hpn.model;

import com.codegym.hpn.model.dto.MaterialDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="materials")
@Accessors(chain = true)
public class Material extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String materialName;

    @Column(nullable = false, scale = 1)
    private double materialContent;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "productType_id", referencedColumnName = "id")
    private ProductType productType;

    private String materialUsage;

    @Column(nullable = false, precision = 12)
    private BigDecimal pricePerUnit;

    @Column(nullable = false)
    private LocalDate productionDate;

    @Column(nullable = false)
    private LocalDate expirationDate;

    private String note;

    public MaterialDTO toMaterialDTOForShowing() {
        return new MaterialDTO()
                .setId(this.id)
                .setMaterialName(this.materialName)
                .setMaterialContent(String.valueOf(this.materialContent))
                .setQuantity(String.valueOf(this.quantity))
                .setPricePerUnit(String.valueOf(this.pricePerUnit))
                .setExpirationDate(this.expirationDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    public MaterialDTO toMaterialDTOForUpdating() {
        return new MaterialDTO()
                .setId(this.id)
                .setMaterialName(this.materialName)
                .setMaterialContent(String.valueOf(this.materialContent))
                .setQuantity(String.valueOf(this.quantity))
                .setPricePerUnit(String.valueOf(this.pricePerUnit))
                .setProductType(this.productType.toProductTypeDTO())
                .setMaterialUsage(this.materialUsage)
                .setProductionDate(this.productionDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .setExpirationDate(this.expirationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .setNote(this.note);
    }

}
