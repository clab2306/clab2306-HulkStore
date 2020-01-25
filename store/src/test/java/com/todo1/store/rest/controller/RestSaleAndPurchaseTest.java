package com.todo1.store.rest.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.todo1.store.domain.dto.PurchaseDTO;
import com.todo1.store.domain.dto.SaleDTO;
import com.todo1.store.domain.service.PurchaseService;
import com.todo1.store.domain.service.SaleService;
import com.todo1.store.infrastructure.entity.Purchase;
import com.todo1.store.infrastructure.entity.Sale;
import com.todo1.store.infrastructure.mapper.PurchaseMapper;
import com.todo1.store.infrastructure.mapper.SaleMapper;
import com.todo1.store.util.TestUtil;
import com.todo1.store.util.builder.PurchaseBuilder;
import com.todo1.store.util.builder.SaleBuilder;

@RunWith(SpringRunner.class)
@WebMvcTest({ RestSale.class, RestPurchase.class, SaleMapper.class, PurchaseMapper.class })
public class RestSaleAndPurchaseTest {

    @MockBean
    private SaleService saleService;
    @Autowired
    private SaleMapper saleMapper;
    @MockBean
    private PurchaseService purchaseService;
    @Autowired
    private PurchaseMapper purchaseMapper;
    @Autowired
    private MockMvc mockMvc;

    private Sale sale;
    private Purchase purchase;

    @Test
    public void createSale() throws Exception {
        sale = SaleBuilder.aSale().build();
        SaleDTO saleDTO = saleMapper.saleToSaleDTO(sale);
        saleDTO.setId(null);

        SaleDTO response = saleMapper.saleToSaleDTO(sale);
        when(saleService.save(any(SaleDTO.class))).thenReturn(response);
        mockMvc.perform(post("/api/sales").contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(saleDTO))).andExpect(status().isCreated())
                .andExpect(content().string(containsString("\"id\":1,")));
    }

    @Test
    public void createPurchase() throws Exception {
        purchase = PurchaseBuilder.aPurchase().withId(10L).build();
        PurchaseDTO purchaseDTO = purchaseMapper.purchaseToPurchaseDTO(purchase);
        purchaseDTO.setId(null);

        PurchaseDTO response = purchaseMapper.purchaseToPurchaseDTO(purchase);
        when(purchaseService.save(any(PurchaseDTO.class))).thenReturn(response);
        mockMvc.perform(post("/api/purchases").contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(purchaseDTO))).andExpect(status().isCreated())
                .andExpect(content().string(containsString("\"id\":10,")));
    }
}
