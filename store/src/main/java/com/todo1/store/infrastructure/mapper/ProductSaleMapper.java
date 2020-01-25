package com.todo1.store.infrastructure.mapper;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.todo1.store.domain.dto.ProductSaleDTO;
import com.todo1.store.infrastructure.entity.Product;
import com.todo1.store.infrastructure.entity.ProductSale;
import com.todo1.store.infrastructure.entity.Sale;

@Service
public class ProductSaleMapper {

    public List<ProductSaleDTO> productsSaleToProductSaleDTOs(List<ProductSale> productsSale) {
        return productsSale.stream()
            .filter(Objects::nonNull)
            .map(this::productSaleToProductSaleDTO)
            .collect(Collectors.toList());
    }

    public ProductSaleDTO productSaleToProductSaleDTO(ProductSale productSale) {
        ProductSaleDTO productSaleDTO = new ProductSaleDTO();
    	productSaleDTO.setId(productSale.getId());
    	productSaleDTO.setProductId(productSale.getProduct().getId());
    	productSaleDTO.setSaleId(productSale.getSale().getId());
    	productSaleDTO.setAmount(productSale.getAmount());
        return productSaleDTO;
    }

    public Set<ProductSale> productsSaleDTOToProductsSale(Set<ProductSaleDTO> productsSaleDTO) {
        return productsSaleDTO.stream()
            .filter(Objects::nonNull)
            .map(this::productSaleDTOToProductSale)
            .collect(Collectors.toSet());
    }

    public ProductSale productSaleDTOToProductSale(ProductSaleDTO productSaleDTO) {
        if (productSaleDTO == null) {
            return null;
        } else {
            ProductSale productSale = new ProductSale();
            productSale.setId(productSaleDTO.getId());
            productSale.setAmount(productSaleDTO.getAmount());
            productSale.setProduct(new Product());
            productSale.getProduct().setId(productSaleDTO.getProductId());
            productSale.setSale(new Sale());
            productSale.getSale().setId(productSaleDTO.getSaleId());
            return productSale;
        }
    }

}
