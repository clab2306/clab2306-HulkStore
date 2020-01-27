package com.todo1.store.model.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.todo1.store.domain.dto.PurchaseDTO;
import com.todo1.store.model.entity.Purchase;
import com.todo1.store.model.entity.User;

@Service
public class PurchaseMapper {

    public List<PurchaseDTO> purchaseToPurchaseDTOs(List<Purchase> productsPurchase) {
        return productsPurchase.stream()
            .filter(Objects::nonNull)
            .map(this::purchaseToPurchaseDTO)
            .collect(Collectors.toList());
    }

    public PurchaseDTO purchaseToPurchaseDTO(Purchase purchase) {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setId(purchase.getId());
        purchaseDTO.setPurchaseDate(purchase.getPurchaseDate());
        purchaseDTO.setTotal(purchase.getTotal());
        purchaseDTO.setUserId(purchase.getUser().getId());
        return purchaseDTO;
    }

    public List<Purchase> purchasesDTOToPurchases(List<PurchaseDTO> purchaseDTO) {
        return purchaseDTO.stream()
            .filter(Objects::nonNull)
            .map(this::purchaseDTOToPurchase)
            .collect(Collectors.toList());
    }

    public Purchase purchaseDTOToPurchase(PurchaseDTO purchaseDTO) {
        if (purchaseDTO == null) {
            return null;
        } else {
            Purchase purchase = new Purchase();
            purchase.setId(purchaseDTO.getId());
            purchase.setPurchaseDate(purchaseDTO.getPurchaseDate());
            purchase.setTotal(purchaseDTO.getTotal());
            purchase.setUser(new User());
            purchase.getUser().setId(purchaseDTO.getUserId());
            return purchase;
        }
    }

}
