package com.todo1.store.infrastructure.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo1.store.domain.dto.StockDTO;
import com.todo1.store.infrastructure.entity.Product;
import com.todo1.store.infrastructure.entity.Stock;
import com.todo1.store.infrastructure.entity.User;

@Service
public class StockMapper {
    
    @Autowired
    private ProductMapper productMapper;

    public List<StockDTO> stockToStockDTOs(List<Stock> stocks) {
        return stocks.stream()
            .filter(Objects::nonNull)
            .map(this::stockToStockDTO)
            .collect(Collectors.toList());
    }

    public StockDTO stockToStockDTO(Stock stock) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setId(stock.getId());
        stockDTO.setAmount(stock.getAmount());
        stockDTO.setProductId(stock.getProduct().getId());
        stockDTO.setUpdateDate(stock.getUpdateDate());
        stockDTO.setUpdateUserId(stock.getUpdateUser().getId());
        stockDTO.setProduct(productMapper.productToProductDTO(stock.getProduct()));
        stockDTO.setUpdateUserDescription(stock.getUpdateUser().getFirstName().concat(" ").concat(stock.getUpdateUser().getLastName()));
        return stockDTO;
    }

    public List<Stock> stocksDTOToStocks(List<StockDTO> stock) {
        return stock.stream()
            .filter(Objects::nonNull)
            .map(this::stockDTOToStock)
            .collect(Collectors.toList());
    }

    public Stock stockDTOToStock(StockDTO stockDTO) {
        if (stockDTO == null) {
            return null;
        } else {
            Stock stock = new Stock();
            stock.setId(stockDTO.getId());
            stock.setAmount(stockDTO.getAmount());
            stock.setProduct(new Product());
            stock.getProduct().setId(stockDTO.getId());
            stock.setUpdateDate(stockDTO.getUpdateDate());
            stock.setUpdateUser(new User());
            stock.getUpdateUser().setId(stockDTO.getUpdateUserId());
            return stock;
        }
    }

}
