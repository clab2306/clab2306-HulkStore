package com.todo1.store.infrastructure.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.todo1.store.domain.dto.SaleDTO;
import com.todo1.store.infrastructure.entity.Sale;
import com.todo1.store.infrastructure.entity.User;

@Service
public class SaleMapper {

    public List<SaleDTO> saleToSaleDTOs(List<Sale> sales) {
        return sales.stream()
            .filter(Objects::nonNull)
            .map(this::saleToSaleDTO)
            .collect(Collectors.toList());
    }

    public SaleDTO saleToSaleDTO(Sale sale) {
        SaleDTO purchaseDTO = new SaleDTO();
        purchaseDTO.setId(sale.getId());
        purchaseDTO.setSaleDate(sale.getSaleDate());
        purchaseDTO.setTotal(sale.getTotal());
        purchaseDTO.setUserId(sale.getUser().getId());
        return purchaseDTO;
    }

    public List<Sale> salesDTOToSales(List<SaleDTO> saleDTOs) {
        return saleDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::saleDTOToSale)
            .collect(Collectors.toList());
    }

    public Sale saleDTOToSale(SaleDTO saleDTO) {
        if (saleDTO == null) {
            return null;
        } else {
            Sale sale = new Sale();
            sale.setId(saleDTO.getId());
            sale.setSaleDate(saleDTO.getSaleDate());
            sale.setTotal(saleDTO.getTotal());
            sale.setUser(new User());
            sale.getUser().setId(saleDTO.getUserId());
            return sale;
        }
    }

}
