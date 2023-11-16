package tn.esprit.spring.service;

import org.junit.Before;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.services.IProduitService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JunitServiceImpl {
 private IProduitService produitService;

    @Before
    public void setUp() {
        // Créer un mock pour IProduitService avant chaque test
        produitService = mock(IProduitService.class);
    }

    @Test
    public void testRetrieveAllProduits() {
        // Définir le comportement attendu lors de l'appel à retrieveAllProduits
        List<Produit> produits = new ArrayList<>();
        produits.add(new Produit());
        when(produitService.retrieveAllProduits()).thenReturn(produits);

        // Appeler la méthode et vérifier le résultat
        assertEquals(1, produitService.retrieveAllProduits().size());
    }

    @Test
    public void testAddProduit() {
        // Créer un produit de test
        Produit produit = new Produit();
        produit.setIdProduit(1L);

        // Définir le comportement attendu lors de l'appel à addProduit
        when(produitService.addProduit(produit)).thenReturn(produit);

        // Appeler la méthode et vérifier le résultat
        assertEquals(1L, produitService.addProduit(produit).getIdProduit().longValue());
    }

    @Test
    public void testDeleteProduit() {
        // Appeler la méthode et vérifier le comportement
        produitService.deleteProduit(1L);

        // Vérifier que la méthode a été appelée avec l'ID spécifié
        verify(produitService).deleteProduit(1L);
    }

    @Test
    public void testUpdateProduit() {
        // Créer un produit de test
        Produit produit = new Produit();
        produit.setIdProduit(1L);

        // Définir le comportement attendu lors de l'appel à updateProduit
        when(produitService.updateProduit(produit)).thenReturn(produit);

        // Appeler la méthode et vérifier le résultat
        assertEquals(1L, produitService.updateProduit(produit).getIdProduit().longValue());
    }

    @Test
    public void testRetrieveProduit() {
        // Créer un produit de test
        Produit produit = new Produit();
        produit.setIdProduit(1L);

        // Définir le comportement attendu lors de l'appel à retrieveProduit
        when(produitService.retrieveProduit(1L)).thenReturn(produit);

        // Appeler la méthode et vérifier le résultat
        assertEquals(1L, produitService.retrieveProduit(1L).getIdProduit().longValue());
    }

    @Test
    public void testAssignProduitToStock() {
        // Appeler la méthode et vérifier le comportement
        produitService.assignProduitToStock(1L, 2L);

        // Vérifier que la méthode a été appelée avec les IDs spécifiés
        verify(produitService).assignProduitToStock(1L, 2L);
    }



}
