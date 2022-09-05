package com.codegym.hpn.model.dto;

import com.codegym.hpn.model.Material;
import com.codegym.hpn.model.ProductType;
import com.codegym.hpn.utils.ErrorMessage;
import com.codegym.hpn.utils.ParsingValidationUtils;
import com.codegym.hpn.utils.ValidationUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class MaterialDTO implements Validator {
    private Long id;
    private String materialName;
    private String materialContent;
    private String quantity;
    private String pricePerUnit;
    private String productionDate;
    private String expirationDate;
    private String materialUsage;
    private String note;

    @Valid
    private  ProductTypeDTO productType;

    public MaterialDTO(Long id, String materialName, double materialContent, int quantity, BigDecimal pricePerUnit, LocalDate expirationDate) {
        this.id = id;
        this.materialName = materialName;
        this.materialContent = String.valueOf(materialContent);
        this.quantity = String.valueOf(quantity);
        this.pricePerUnit = String.valueOf(pricePerUnit);
        this.expirationDate = expirationDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return MaterialDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        MaterialDTO materialDTO = (MaterialDTO) target;
        String materialName = materialDTO.getMaterialName();
        String materialContent = materialDTO.getMaterialContent();
        String quantity = materialDTO.getQuantity();
        String pricePerUnit = materialDTO.getPricePerUnit();
        String productionDate = materialDTO.getProductionDate();
        String expirationDate = materialDTO.getExpirationDate();

        if (materialName.equals("") || materialName == null) {
            errors.rejectValue("materialName","400", ErrorMessage.getNotEmptyMessage("Material name"));
        }
        else {
            boolean isMaterialNameIdValid = Pattern.matches(ValidationUtils.FULL_NAME_REGEX, materialName);
            if (!isMaterialNameIdValid) {
                errors.rejectValue("materialName","400", ErrorMessage.VALID_MATERIAL_NAME);
            }
            else if (materialName.length() > 100) {
                errors.rejectValue("materialName","400", ErrorMessage.MAX_MATERIAL_NAME_LENGTH);
            }
        }

        if (materialContent.equals("") || materialContent == null) {
            errors.rejectValue("materialContent","400", ErrorMessage.getNotEmptyMessage("Material content"));
        }
        else {
            boolean isMaterialContentValid = Pattern.matches(ValidationUtils.DOUBLE_REGEX, materialContent);
            if (!isMaterialContentValid) {
                errors.rejectValue("materialContent","400", ErrorMessage.getNotNumberMessage("Material content"));
            }
            else {
                double validMaterialContent = Double.parseDouble(materialContent);
                if (validMaterialContent <= 0) {
                    errors.rejectValue("materialContent","400", ErrorMessage.getMinValue("Material content", "0"));
                }
                if (validMaterialContent > 10000000) {
                    errors.rejectValue("materialContent","400", ErrorMessage.getMaxValue("Material content", "10,000,000"));
                }
            }
        }

        if (quantity.equals("") || quantity == null) {
            errors.rejectValue("quantity","400", ErrorMessage.getNotEmptyMessage("Quantity"));
        }
        else {
            boolean isQuantityValid = Pattern.matches(ValidationUtils.INTEGER_REGEX, quantity);
            if (!isQuantityValid) {
                errors.rejectValue("quantity","400", ErrorMessage.getNotNumberMessage("Quantity"));
            }
            else {
                int validQuantity = Integer.parseInt(quantity);
                if (validQuantity <= 0) {
                    errors.rejectValue("quantity","400", ErrorMessage.getMinValue("Quantity", "0"));
                }
                if (validQuantity > 5000000) {
                    errors.rejectValue("quantity","400", ErrorMessage.getMaxValue("Quantity", "5,000,000"));
                }
            }
        }

        if (pricePerUnit.equals("") || pricePerUnit == null) {
            errors.rejectValue("pricePerUnit","400", ErrorMessage.getNotEmptyMessage("Price"));
        }
        else {
            boolean isPricePerUnitValid = Pattern.matches(ValidationUtils.INTEGER_REGEX, pricePerUnit);
            if (!isPricePerUnitValid) {
                errors.rejectValue("pricePerUnit","400", ErrorMessage.getNotNumberMessage("Price"));
            }
            else {
                int validPricePerUnit = Integer.parseInt(pricePerUnit);
                if (validPricePerUnit <= 0) {
                    errors.rejectValue("pricePerUnit","400", ErrorMessage.getMinValue("Price", "0"));
                }
                if (validPricePerUnit > 5000000) {
                    errors.rejectValue("pricePerUnit","400", ErrorMessage.getMaxValue("Price", "5,000,000"));
                }
            }
        }

        if (productionDate.equals("") || productionDate == null) {
            errors.rejectValue("productionDate","400", ErrorMessage.getNotEmptyMessage("Production date"));
        }
        else {
            if (!ParsingValidationUtils.isParsableToLocalDate(productionDate)) {
                errors.rejectValue("productionDate","400", ErrorMessage.getNotEmptyMessage("Production date"));
            }
            else {
                LocalDate validProductionDate = LocalDate.parse(productionDate);
                if (!ValidationUtils.checkProductionDate(validProductionDate)) {
                    errors.rejectValue("productionDate","400", ErrorMessage.getProductionDateLimit(ValidationUtils.minProductionDate));
                }
            }
        }

        if (expirationDate.equals("") || expirationDate == null) {
            errors.rejectValue("expirationDate","400", ErrorMessage.getNotEmptyMessage("Expiration date"));
        }
        else {
            if (!ParsingValidationUtils.isParsableToLocalDate(expirationDate)) {
                errors.rejectValue("expirationDate","400", ErrorMessage.getNotEmptyMessage("Expiration date"));
            }
            else {
                LocalDate validExpirationDate = LocalDate.parse(expirationDate);
                if (!ValidationUtils.checkExpirationDate(validExpirationDate)) {
                    errors.rejectValue("expirationDate","400", ErrorMessage.getExpirationDateLimit(ValidationUtils.maxExpirationDate));
                }
            }
        }

    }

    public Material toMaterial(ProductType productType) {
        return new Material()
                .setId(this.id)
                .setMaterialName(this.materialName.trim())
                .setMaterialContent(Double.parseDouble(this.materialContent))
                .setQuantity(Integer.parseInt(this.quantity))
                .setPricePerUnit(new BigDecimal(this.pricePerUnit))
                .setProductType(productType)
                .setMaterialUsage(this.materialUsage.trim())
                .setProductionDate(LocalDate.parse(this.productionDate))
                .setExpirationDate(LocalDate.parse(this.expirationDate))
                .setNote(this.note.trim());
    }
}
