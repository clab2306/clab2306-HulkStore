package com.todo1.store.infrastructure.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.todo1.store.domain.dto.ProductPurchaseDTO;
import com.todo1.store.infrastructure.entity.Product;
import com.todo1.store.infrastructure.entity.ProductPurchase;
import com.todo1.store.infrastructure.entity.Purchase;

@Service
public class ProductPurchaseMapper {

    public List<ProductPurchaseDTO> productsPurchaseToProductPurchaseDTOs(List<ProductPurchase> productsPurchase) {
        return productsPurchase.stream()
            .filter(Objects::nonNull)
            .map(this::productPurchaseToProductPurchaseDTO)
            .collect(Collectors.toList());
    }

    public ProductPurchaseDTO productPurchaseToProductPurchaseDTO(ProductPurchase productPurchase) {
        ProductPurchaseDTO productPurchaseDTO = new ProductPurchaseDTO();
        productPurchaseDTO.setId(productPurchase.getId());
        productPurchaseDTO.setProductId(productPurchase.getProduct().getId());
        productPurchaseDTO.setPurchaseId(productPurchase.getProduct().getId());
        productPurchaseDTO.setAmount(productPurchase.getAmount());
        return productPurchaseDTO;
    }

    public List<ProductPurchase> productsDTOToProducts(List<ProductPurchaseDTO> productsPurchaseDTO) {
        return productsPurchaseDTO.stream()
            .filter(Objects::nonNull)
            .map(this::productPurchaseDTOToProductPurchase)
            .collect(Collectors.toList());
    }

    public ProductPurchase productPurchaseDTOToProductPurchase(ProductPurchaseDTO productPurchaseDTO) {
        if (productPurchaseDTO == null) {
            return null;
        } else {
            ProductPurchase productPurchase = new ProductPurchase();
            productPurchase.setId(productPurchaseDTO.getId());
            productPurchase.setProduct(new Product());
            productPurchase.getProduct().setId(productPurchaseDTO.getProductId());
            productPurchase.setPurchase(new Purchase());
            productPurchase.getPurchase().setId(productPurchaseDTO.getPurchaseId());
            productPurchase.setAmount(productPurchaseDTO.getAmount());
            return productPurchase;
        }
    }

}
