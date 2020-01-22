package com.todo1.store.infrastructure.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.todo1.store.domain.dto.ProductDTO;
import com.todo1.store.infrastructure.entity.Product;
import com.todo1.store.infrastructure.entity.ProductType;
import com.todo1.store.infrastructure.entity.User;

@Service
public class ProductMapper {

    public List<ProductDTO> usersToUserDTOs(List<Product> products) {
        return products.stream()
            .filter(Objects::nonNull)
            .map(this::productToProductDTO)
            .collect(Collectors.toList());
    }

    public ProductDTO productToProductDTO(Product product) {
    	ProductDTO productDTO = new ProductDTO();
    	productDTO.setId(product.getId());
    	productDTO.setDescription(product.getDescription());
    	productDTO.setPrice(product.getPrice());
    	productDTO.setUserId(product.getUser().getId());
    	productDTO.setProductTypeId(product.getProductType().getId());
        return productDTO;
    }

    public List<Product> productsDTOToProducts(List<ProductDTO> productsDTO) {
        return productsDTO.stream()
            .filter(Objects::nonNull)
            .map(this::productDTOToProduct)
            .collect(Collectors.toList());
    }

    public Product productDTOToProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        } else {
        	Product product = new Product();
        	product.setId(productDTO.getId());
        	product.setDescription(productDTO.getDescription());
        	product.setPrice(productDTO.getPrice());
        	product.setUser(new User());
        	product.getUser().setId(productDTO.getUserId());
            product.setProductType(new ProductType());
            product.getProductType().setId(productDTO.getProductTypeId());
            return product;
        }
    }

    public User userFromId(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}
