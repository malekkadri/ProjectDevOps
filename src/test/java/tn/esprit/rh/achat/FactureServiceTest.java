package tn.esprit.rh.achat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.services.FactureServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FactureServiceTest {

    @Mock
    private FactureRepository factureRepository;

    @InjectMocks
    private FactureServiceImpl factureService;

    @Test
    public void testRetrieveAllFacture() {
        ArrayList<Facture> facturesList = new ArrayList<>();
        Mockito.when(factureRepository.findAll()).thenReturn(facturesList);
        List<Facture> actualRetrieveAllProduitsResult = factureService.retrieveAllFactures();
        log.info("List size: " + actualRetrieveAllProduitsResult.size());
        Assertions.assertSame(facturesList, actualRetrieveAllProduitsResult);
        Assertions.assertTrue(actualRetrieveAllProduitsResult.isEmpty());
        Mockito.verify(factureRepository).findAll();
    }

    @Test
    void testAddFacture() {
        Facture facture = new Facture();
        Mockito.when(factureRepository.save(Mockito.any(Facture.class))).thenReturn(facture);
        Facture result = factureService.addFacture(facture);
        Assertions.assertEquals(facture, result);
        Mockito.verify(factureRepository).save(facture);
    }

    @Test
    void testRetrieveFacture() {
        Long factureId = 1L;
        Facture facture = new Facture();
        Mockito.when(factureRepository.findById(factureId)).thenReturn(Optional.of(facture));

        Facture result = factureService.retrieveFacture(factureId);

        Assertions.assertEquals(facture, result);

        Mockito.verify(factureRepository).findById(factureId);
    }

    @Test
    void testCancelFacture() {
        Long factureId = 1L;
        Facture facture = new Facture();
        Mockito.when(factureRepository.findById(factureId)).thenReturn(Optional.of(facture));

        factureService.cancelFacture(factureId);

        Mockito.verify(factureRepository).findById(factureId);

        Assertions.assertTrue(facture.getArchivee());
    }
}
