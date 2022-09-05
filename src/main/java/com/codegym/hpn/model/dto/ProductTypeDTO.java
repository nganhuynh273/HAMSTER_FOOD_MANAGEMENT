package com.codegym.hpn.model.dto;

import com.codegym.hpn.utils.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ProductTypeDTO implements Validator {
    private String id;
    private String name;

    @Override
    public boolean supports(Class<?> clazz) {

        return ProductTypeDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductTypeDTO productTypeDTO = (ProductTypeDTO) target;
        String productTypeId = productTypeDTO.getId();

        if (productTypeId.equals("") || productTypeId == null) {
            errors.rejectValue("productType","400", ErrorMessage.getNotEmptyMessage("Product type ID"));
            return;
        }

        boolean isProductTypeIdValid = java.util.regex.Pattern.matches("\\d+", productTypeId);
        if (!isProductTypeIdValid) {
            errors.rejectValue("productType","400", ErrorMessage.getNotNumberMessage("Product type ID"));
        }
    }

}
