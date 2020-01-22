package com.todo1.store.infrastructure.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.todo1.store.domain.dto.ProductTypeDTO;
import com.todo1.store.infrastructure.entity.ProductType;

@Service
public class ProductTypeMapper {

    public List<ProductTypeDTO> productsTypeToProductTypeDTOs(List<ProductType> productsType) {
        return productsType.stream()
            .filter(Objects::nonNull)
            .map(this::productTypeToProductTypeDTO)
            .collect(Collectors.toList());
    }

    public ProductTypeDTO productTypeToProductTypeDTO(ProductType productType) {
        ProductTypeDTO productTypeDTO = new ProductTypeDTO();
        productTypeDTO.setId(productType.getId());
        productTypeDTO.setDescription(productType.getDescription());
        return productTypeDTO;
    }

    public List<ProductType> productsTypeDTOToProducts(List<ProductTypeDTO> productsTypeDTO) {
        return productsTypeDTO.stream()
            .filter(Objects::nonNull)
            .map(this::productTypeDTOToProductType)
            .collect(Collectors.toList());
    }

    public ProductType productTypeDTOToProductType(ProductTypeDTO productTypeDTO) {
        if (productTypeDTO == null) {
            return null;
        } else {
            ProductType productType = new ProductType();
            productType.setId(productTypeDTO.getId());
            productType.setDescription(productTypeDTO.getDescription());
            return productType;
        }
    }

}
