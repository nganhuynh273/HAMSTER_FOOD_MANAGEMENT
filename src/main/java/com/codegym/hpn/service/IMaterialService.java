package com.codegym.hpn.service;

import com.codegym.hpn.model.Material;
import com.codegym.hpn.model.dto.MaterialDTO;

import java.util.List;
import java.util.Optional;

public interface IMaterialService extends IGeneralService<Material>{
    boolean isMaterialExisted(Material material);

    List<MaterialDTO> getAllMaterialDTO();

    Optional<Material> findByIdAndDeletedFalse(Long id);

    boolean isMaterialUpdatedExisted(Material material);
}
