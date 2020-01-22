package com.todo1.store.infrastructure.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo1.store.domain.dto.SaleDTO;
import com.todo1.store.domain.service.SaleService;
import com.todo1.store.infrastructure.entity.ProductSale;
import com.todo1.store.infrastructure.entity.Sale;
import com.todo1.store.infrastructure.entity.Stock;
import com.todo1.store.infrastructure.mapper.ProductSaleMapper;
import com.todo1.store.infrastructure.mapper.SaleMapper;
import com.todo1.store.infrastructure.repository.ProductSaleRepository;
import com.todo1.store.infrastructure.repository.SaleRepository;
import com.todo1.store.infrastructure.repository.StockRepository;

/**
 * Implementación de Service para el manejo de Sale.
 */
@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final Logger log = LoggerFactory.getLogger(SaleServiceImpl.class);

    private final SaleRepository saleRepository;

    private final ProductSaleRepository productSaleRepository;

    private final StockRepository stockRepository;

    private final SaleMapper saleMapper;

    private final ProductSaleMapper productSaleMapper;

    public SaleServiceImpl(SaleRepository saleRepository, SaleMapper saleMapper, ProductSaleMapper productSaleMapper,
            ProductSaleRepository productSaleRepository, StockRepository stockRepository) {
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
        this.productSaleMapper = productSaleMapper;
        this.productSaleRepository = productSaleRepository;
        this.stockRepository = stockRepository;
    }

    /**
     * Save a sale.
     *
     * @param saleDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SaleDTO save(SaleDTO saleDTO) {
        log.debug("Solicitud para guardar Sale : {}", saleDTO);
        Sale sale = saleMapper.saleDTOToSale(saleDTO);
        sale.setProductSales(productSaleMapper.productsSaleDTOToProductsSale(saleDTO.getProducts()));
        sale = saleRepository.save(sale);
        
        for(ProductSale productSale : sale.getProductSales()) {
            productSale.setSale(sale);
            productSaleRepository.save(productSale);
            
            Stock stock = stockRepository.findByProduct_Id(productSale.getProduct().getId());
            stock.setAmount(calculateAmount(stock.getAmount(), productSale.getAmount()));
            
            stockRepository.save(stock);
        }

        return saleMapper.saleToSaleDTO(sale);
    }

    private Long calculateAmount(Long amountStock, Long amountProduct) {
        return amountStock - amountProduct;
    }

    /**
     * Obtener todos los registros de sales.
     *
     * @return lista de sale
     */
    @Override
    @Transactional(readOnly = true)
    public List<SaleDTO> findAll() {
        log.debug("Solicitud para obtener todos Sales");
        return saleRepository.findAll().stream().map(saleMapper::saleToSaleDTO)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Obtener sale por id.
     *
     * @param id id de Sale
     * @return Sale
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SaleDTO> findOne(Long id) {
        log.debug("Solicitud para obtener Sale : {}", id);
        return saleRepository.findById(id).map(saleMapper::saleToSaleDTO);
    }

    /**
     * Eliminar sale por id.
     *
     * @param id id de Sale
     */
    @Override
    public void delete(Long id) {
        log.debug("Solicitud para eliminar Sale : {}", id);
        saleRepository.deleteById(id);
    }
}
