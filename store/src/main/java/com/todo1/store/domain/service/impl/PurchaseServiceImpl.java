package com.todo1.store.domain.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo1.store.domain.dto.PurchaseDTO;
import com.todo1.store.domain.dto.SaleDTO;
import com.todo1.store.domain.service.PurchaseService;
import com.todo1.store.model.entity.ProductPurchase;
import com.todo1.store.model.entity.ProductSale;
import com.todo1.store.model.entity.Purchase;
import com.todo1.store.model.entity.Sale;
import com.todo1.store.model.entity.Stock;
import com.todo1.store.model.mapper.ProductPurchaseMapper;
import com.todo1.store.model.mapper.PurchaseMapper;
import com.todo1.store.model.repository.ProductPurchaseRepository;
import com.todo1.store.model.repository.PurchaseRepository;
import com.todo1.store.model.repository.StockRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Purchase.
 */
@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {

    private final Logger log = LoggerFactory.getLogger(PurchaseServiceImpl.class);

    private final PurchaseRepository purchaseRepository;

    private final PurchaseMapper purchaseMapper;

    private final ProductPurchaseMapper productPurchaseMapper;

    private final ProductPurchaseRepository productPurchaseRepository;

    private final StockRepository stockRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, PurchaseMapper purchaseMapper,
            ProductPurchaseMapper productPurchaseMapper, ProductPurchaseRepository productPurchaseRepository,StockRepository stockRepository) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseMapper = purchaseMapper;
        this.productPurchaseMapper = productPurchaseMapper;
        this.productPurchaseRepository = productPurchaseRepository;
        this.stockRepository = stockRepository;
    }

    /**
     * Guardar purchase.
     *
     * @param purchaseDTO entidad a guardar
     * @return Entidad guardada
     */
    @Override
    public PurchaseDTO save(PurchaseDTO purchaseDTO) {
        log.debug("Solicitud para guardad Purchase : {}", purchaseDTO);
        Purchase purchase = purchaseMapper.purchaseDTOToPurchase(purchaseDTO);
        purchase.setProductPurchases(productPurchaseMapper.productsPurchaseDTOToProductsPurchase(purchaseDTO.getProductPurchases()));
        purchase = purchaseRepository.save(purchase);
        
        for (ProductPurchase productPurchase : purchase.getProductPurchases()) {
            productPurchase.setPurchase(purchase);
            productPurchaseRepository.save(productPurchase);

            Stock stock = stockRepository.findByProduct_Id(productPurchase.getProduct().getId());
            stock.setAmount(calculateAmount(stock.getAmount(), productPurchase.getAmount()));

            stockRepository.save(stock);
        }
        return purchaseMapper.purchaseToPurchaseDTO(purchase);
    }
    private Long calculateAmount(Long amountStock, Long amountProduct) {
        return amountStock + amountProduct;
    }

    /**
     * Obtenet todos purchases.
     *
     * @return Lista de Purchase
     */
    @Override
    @Transactional(readOnly = true)
    public List<PurchaseDTO> findAll() {
        log.debug("Request to get all Purchases");
        return purchaseRepository.findAll().stream()
            .map(purchaseMapper::purchaseToPurchaseDTO)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Obtener un purchase por id.
     *
     * @param id id de Purchase
     * @return Purchase
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PurchaseDTO> findOne(Long id) {
        log.debug("Solicitud para obtenet Purchase : {}", id);
        return purchaseRepository.findById(id)
            .map(purchaseMapper::purchaseToPurchaseDTO);
    }

    /**
     * Eliminar purchase por id.
     *
     * @param id id de Purchase
     */
    @Override
    public void delete(Long id) {
        log.debug("Solicitud para eliminar Purchase : {}", id);
        purchaseRepository.deleteById(id);
    }
}
