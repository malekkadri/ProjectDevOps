package tn.esprit.rh.achat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)

public class StockTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveStock() {
        Long stockId = 1L;
        Stock mockStock = new Stock(stockId, "TestStock", 15, 5);
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(mockStock));
        Stock result = stockService.retrieveStock(stockId);
        verify(stockRepository, times(1)).findById(stockId);
        assertEquals(mockStock, result);
    }

    @Test
    void testUpdateStock() {
        Stock stockToUpdate = new Stock(1L, "TestStock", 15, 5);
        when(stockRepository.save(stockToUpdate)).thenReturn(stockToUpdate);
        Stock result = stockService.updateStock(stockToUpdate);
        verify(stockRepository, times(1)).save(stockToUpdate);
        assertEquals(stockToUpdate, result);
    }

    @Test
    void testDeleteStock() {
        Long stockIdToDelete = 1L;
        stockService.deleteStock(stockIdToDelete);
        verify(stockRepository, times(1)).deleteById(stockIdToDelete);
    }

    @Test
    void testRetrieveAllStocks() {
        List<Stock> mockStockList = Arrays.asList(
                new Stock(1L, "Stock1", 10, 5),
                new Stock(2L, "Stock2", 20, 8)
        );
        when(stockRepository.findAll()).thenReturn(mockStockList);
        List<Stock> result = stockService.retrieveAllStocks();
        verify(stockRepository, times(1)).findAll();
        assertEquals(mockStockList, result);
    }
}
