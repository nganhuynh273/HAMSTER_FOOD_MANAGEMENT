package com.codegym.hpn.model;

import com.codegym.hpn.model.dto.ProductTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name="product_types")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    public ProductTypeDTO toProductTypeDTO() {
        return new ProductTypeDTO()
                .setId(String.valueOf(this.id))
                .setName(this.name);
    }
}
