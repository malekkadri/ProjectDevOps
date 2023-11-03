
package tn.esprit.rh.achat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.rh.achat.controllers.StockRestController;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.UUID;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith( SpringRunner.class)
@ContextConfiguration(classes = {StockServiceImpl.class})
public class MockitoTest {

    private StockServiceImpl service;
    private StockRepository repository;
//    private MockMvc mockMvc;
//    ObjectMapper objectMapper = new ObjectMapper();
//    ObjectWriter objectWriter = objectMapper.writer();
//@Mock
// private StockRepository stockRepository;
//@InjectMocks
//    private StockRestController stockRestController;
//
//    long id1 = java.util.UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
//    long id2 = java.util.UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
//    long id3 = java.util.UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
//    Stock RECORD_1 = new Stock(id1,"alimentaire",50,5);
//    Stock RECORD_2 = new Stock(id2,"électro",100,20);
//    Stock RECORD_3 = new Stock(id3,"vestimentaire",150,10);
//@Before
//    public void setUp(){
//    MockitoAnnotations.initMocks(this);
//    this.mockMvc = MockMvcBuilders.standaloneSetup(stockRestController).build();
//
//   }
//@Test
////    public void getAllRecords_success()throws Exception{
//
//    List<Stock> records = new ArrayList<>(Arrays.asList(RECORD_1,RECORD_2,RECORD_3));
//    Mockito.when(stockRepository.findAll()).thenReturn(records);
//    mockMvc.perform(MockMvcRequestBuilders
//            .get("/retrieve-all-stocks")
//            .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk())
//            .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(3)))
//            .andExpect((ResultMatcher) jsonPath("$[2].libelleStock", is("alimentaire")));
//}

    @Test
    public void getStockTest(){
        System.out.println(" get test stock");
        long id = java.util.UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        long id2 = java.util.UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

        repository = mock(StockRepository.class);
        service = new StockServiceImpl(repository);

        List<Stock> stockList = new ArrayList<>();
        stockList.add(new Stock(id,"alimentaire",100,5));
        stockList.add(new Stock(id2,"vest",50,10));
        when(repository.findAll()).thenReturn(stockList);

    }

}

