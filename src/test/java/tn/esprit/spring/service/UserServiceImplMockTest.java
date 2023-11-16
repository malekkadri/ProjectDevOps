package tn.esprit.spring.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.services.IProduitService;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceImplMockTest {

    @Mock
    ProduitRepository produitRepository;

    @InjectMocks
    IProduitService produitService;

    Produit produit=new Produit();

    @Test
    public void testRetriveProduit(){
        Produit produit = new Produit();
        produit.setIdProduit(1L);
        produit.setCodeProduit("f1");
        produit.setLibelleProduit("desc");
        produit.setPrix(11);
        produit.setDateCreation(new Date());
        produit.setDateDerniereModification(new Date());
        produit.setStock(null);
        produit.setDetailFacture(null);
        produit.setCategorieProduit(null);

        Mockito.when(produitRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(produit));

        Produit retrievedProduit = produitService.retrieveProduit(1L);


        assertEquals(produit, retrievedProduit);

        Produit produit1=produitService.retrieveProduit(1L);
        Assertions.assertNotNull(produit1);
    }
}
