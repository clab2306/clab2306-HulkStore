package com.todo1.store.domain.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.todo1.store.domain.dto.ProductPurchaseDTO;
import com.todo1.store.domain.dto.PurchaseDTO;
import com.todo1.store.domain.service.impl.PurchaseServiceImpl;
import com.todo1.store.model.entity.Purchase;
import com.todo1.store.model.entity.Stock;
import com.todo1.store.model.mapper.ProductPurchaseMapper;
import com.todo1.store.model.mapper.PurchaseMapper;
import com.todo1.store.model.repository.ProductPurchaseRepository;
import com.todo1.store.model.repository.PurchaseRepository;
import com.todo1.store.model.repository.StockRepository;
import com.todo1.store.util.builder.ProductPurchaseBuilder;
import com.todo1.store.util.builder.PurchaseBuilder;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseServiceImplTest {

    private static final long PURCHASE_ID = 20L;

    private PurchaseServiceImpl purchaseServiceImpl;

    @Mock
    private PurchaseRepository purchaseRepository;
    @Mock
    private ProductPurchaseRepository productPurchaseRepository;
    @Mock
    private StockRepository stockRepository;
    private PurchaseMapper purchaseMapper;
    private ProductPurchaseMapper productPurchaseMapper;
    @Captor
    private ArgumentCaptor<Stock> captorStock;

    private Purchase sale;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        purchaseMapper = new PurchaseMapper();
        productPurchaseMapper = new ProductPurchaseMapper();
        purchaseServiceImpl = new PurchaseServiceImpl(purchaseRepository, purchaseMapper, productPurchaseMapper, productPurchaseRepository,
                stockRepository);
    }

    @Before
    public void initTest() {
        sale = PurchaseBuilder.aPurchase().build();
    }

    @Test
    public void saveSale() throws Exception {
        PurchaseDTO saleDTO = purchaseMapper.purchaseToPurchaseDTO(sale);

        HashSet<ProductPurchaseDTO> products = new HashSet<ProductPurchaseDTO>();
        products.add(productPurchaseMapper.productPurchaseToProductPurchaseDTO(ProductPurchaseBuilder.aProductPurchase().build()));
        saleDTO.setProductPurchases(products);
        Purchase saleDB = PurchaseBuilder.aPurchase().withId(PURCHASE_ID).build();
        when(purchaseRepository.save(any(Purchase.class))).thenReturn(saleDB);
        Stock stock = new Stock();
        stock.amount(10L);
        when(stockRepository.findByProduct_Id(anyLong())).thenReturn(stock);

        PurchaseDTO response = purchaseServiceImpl.save(saleDTO);

        verify(stockRepository).save(captorStock.capture());
        Stock capturedStock = captorStock.getValue();

        assertThat(response.getId()).isEqualTo(PURCHASE_ID);
        assertThat(capturedStock.getAmount()).isEqualTo(20L);
    }

}
