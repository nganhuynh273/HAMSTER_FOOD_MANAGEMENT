package com.codegym.hpn.controller.api;

import com.codegym.hpn.model.Material;
import com.codegym.hpn.model.ProductType;
import com.codegym.hpn.model.dto.MaterialDTO;
import com.codegym.hpn.model.dto.ProductTypeDTO;
import com.codegym.hpn.service.IMaterialService;
import com.codegym.hpn.service.IProductTypeService;
import com.codegym.hpn.utils.AppUtils;
import com.codegym.hpn.utils.ErrorMessage;
import com.codegym.hpn.utils.ParsingValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/materials")
public class MaterialAPI {
    @Autowired
    private AppUtils appUtils;

    @Autowired
    private IProductTypeService productTypeService;

    @Autowired
    private IMaterialService materialService;

    @GetMapping
    public ResponseEntity<?> getAllMaterials() {
        try {
            List<MaterialDTO> materialDTOList = materialService.getAllMaterialDTO();

            if (materialDTOList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(materialDTOList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findMaterial/{materialId}")
    public ResponseEntity<?> findMaterialById(@PathVariable String materialId) {
        if (ParsingValidationUtils.isLongParsable(materialId)) {
            long validId = Long.parseLong(materialId);
            Optional<Material> material = materialService.findByIdAndDeletedFalse(validId);

            if (material.isPresent()) {
                return new ResponseEntity<>(material.get().toMaterialDTOForUpdating(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Thực phẩm không tồn tại.", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewMaterial(@RequestBody MaterialDTO materialDTO, BindingResult bindingResult) {
        return createOrUpdateMaterial("create", materialDTO, bindingResult);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateMaterial(@RequestBody MaterialDTO materialDTO, BindingResult bindingResult) {
        return createOrUpdateMaterial("update", materialDTO, bindingResult);
    }

    private ResponseEntity<?> createOrUpdateMaterial(String action, MaterialDTO materialDTO, BindingResult bindingResult) {
        new MaterialDTO().validate(materialDTO, bindingResult);
        new ProductTypeDTO().validate(materialDTO.getProductType(), bindingResult);

        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        long productTypeId = Long.parseLong(materialDTO.getProductType().getId());
        Optional<ProductType> productType = productTypeService.findById(productTypeId);

        if (!productType.isPresent()) {
            return new ResponseEntity<>(ErrorMessage.PRODUCT_TYPE_NOT_EXIST, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        ProductType productTypeObject = productType.get();
        Material newMaterial = materialDTO.toMaterial(productTypeObject);

        switch (action) {
            case "create": {
                if (materialService.isMaterialExisted(newMaterial)) {
                    return new ResponseEntity<>(ErrorMessage.MATERIAL_EXISTS, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                break;
            }
            case "update": {
                if (materialService.isMaterialUpdatedExisted(newMaterial)) {
                    return new ResponseEntity<>(ErrorMessage.MATERIAL_EXISTS, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                break;
            }
        }

        try {
            Material newMaterialSaved = materialService.save(newMaterial);
            return new ResponseEntity<>(newMaterialSaved.toMaterialDTOForShowing(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ErrorMessage.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remove/{materialId}")
    public ResponseEntity<?> removeMaterial(@PathVariable Long materialId) {

        Optional<Material> material = materialService.findByIdAndDeletedFalse(materialId);
        if (material.isPresent()) {

            try {
                material.get().setDeleted(true);
                materialService.save(material.get());

                return new ResponseEntity<>(HttpStatus.OK);
            } catch (DataIntegrityViolationException e) {
                return new ResponseEntity<>(ErrorMessage.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>("Thức ăn này không tồn tại.", HttpStatus.NOT_FOUND);
    }

}
