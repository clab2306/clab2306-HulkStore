package com.todo1.store.infrastructure.service.impl;

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

import com.todo1.store.domain.dto.ProductSaleDTO;
import com.todo1.store.domain.dto.SaleDTO;
import com.todo1.store.infrastructure.entity.Sale;
import com.todo1.store.infrastructure.entity.Stock;
import com.todo1.store.infrastructure.mapper.ProductSaleMapper;
import com.todo1.store.infrastructure.mapper.SaleMapper;
import com.todo1.store.infrastructure.repository.ProductSaleRepository;
import com.todo1.store.infrastructure.repository.SaleRepository;
import com.todo1.store.infrastructure.repository.StockRepository;
import com.todo1.store.util.builder.ProductSaleBuilder;
import com.todo1.store.util.builder.SaleBuilder;

@RunWith(MockitoJUnitRunner.class)
public class SaleServiceImplTest {

    private static final long SALE_ID = 20L;

    private SaleServiceImpl saleServiceImpl;

    @Mock
    private SaleRepository saleRepository;
    @Mock
    private ProductSaleRepository productSaleRepository;
    @Mock
    private StockRepository stockRepository;
    private SaleMapper saleMapper;
    private ProductSaleMapper productSaleMapper;
    @Captor
    private ArgumentCaptor<Stock> captorStock;

    private Sale sale;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        saleMapper = new SaleMapper();
        productSaleMapper = new ProductSaleMapper();
        saleServiceImpl = new SaleServiceImpl(saleRepository, saleMapper, productSaleMapper, productSaleRepository,
                stockRepository);
    }

    @Before
    public void initTest() {
        sale = SaleBuilder.aSale().build();
    }

    @Test
    public void saveSale() throws Exception {
        SaleDTO saleDTO = saleMapper.saleToSaleDTO(sale);

        HashSet<ProductSaleDTO> products = new HashSet<ProductSaleDTO>();
        products.add(productSaleMapper.productSaleToProductSaleDTO(ProductSaleBuilder.aProductSale().build()));
        saleDTO.setProductSales(products);
        Sale saleDB = SaleBuilder.aSale().withId(SALE_ID).build();
        when(saleRepository.save(any(Sale.class))).thenReturn(saleDB);
        Stock stock = new Stock();
        stock.amount(10L);
        when(stockRepository.findByProduct_Id(anyLong())).thenReturn(stock);

        SaleDTO response = saleServiceImpl.save(saleDTO);

        verify(stockRepository).save(captorStock.capture());
        Stock capturedStock = captorStock.getValue();

        assertThat(response.getId()).isEqualTo(SALE_ID);
        assertThat(capturedStock.getAmount()).isEqualTo(0L);
    }

}
