package com.todo1.store.infrastructure.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo1.store.domain.dto.StockDTO;
import com.todo1.store.domain.service.StockService;
import com.todo1.store.infrastructure.entity.Stock;
import com.todo1.store.infrastructure.mapper.StockMapper;
import com.todo1.store.infrastructure.repository.StockRepository;

import java.util.Optional;

/**
 * Service para manejar Stock.
 */
@Service
@Transactional
public class StockServiceImpl implements StockService {

    private final Logger log = LoggerFactory.getLogger(StockServiceImpl.class);

    private final StockRepository stockRepository;

    private final StockMapper stockMapper;

    public StockServiceImpl(StockRepository stockRepository, StockMapper stockMapper) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }

    /**
     * Save a stock.
     *
     * @param stockDTO entidad a guardar
     * @return stockDTO
     */
    @Override
    public StockDTO save(StockDTO stockDTO) {
        log.debug("Solicitud para guardar Stock : {}", stockDTO);
        Stock stock = stockMapper.stockDTOToStock(stockDTO);
        stock = stockRepository.save(stock);
        return stockMapper.stockToStockDTO(stock);
    }

    /**
     * Obtener todos los registros de stocks.
     *
     * @param pageable
     * @return Lista de stock
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StockDTO> findAll(Pageable pageable) {
        log.debug("Solicitud para obtenet de todos los registros de Stocks");
        return stockRepository.findAll(pageable)
            .map(stockMapper::stockToStockDTO);
    }


    /**
     * Obtener stock by id.
     *
     * @param id id stock
     * @return stock
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StockDTO> findOne(Long id) {
        log.debug("Solicitud para obtener Stock : {}", id);
        return stockRepository.findById(id)
            .map(stockMapper::stockToStockDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public StockDTO findByProductId(Long productId) {
        log.debug("Solicitud para obtener Stock por producto : {}", productId);
        Stock stock = stockRepository.findByProduct_Id(productId);
        return stockMapper.stockToStockDTO(stock);
    }

    @Override
    public void delete(Long id) {
        log.debug("Solicitud para eliminar Stock : {}", id);
        stockRepository.deleteById(id);
    }
}
