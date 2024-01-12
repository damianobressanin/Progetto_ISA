package it.isa.progetto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InventarioTest {

    private Inventario inventario;
    private Prodotto prodotto1;
    private Prodotto prodotto2;

    @BeforeEach
    void setUp() {
        inventario = new Inventario();
        prodotto1 = Prodotto.creaPiantaLavanda();
        prodotto2 = Prodotto.creaCremaMani();
    }

    @Test
    void testAggiungiProdotto() {
        assertTrue(inventario.aggiungiProdotto(prodotto1, 5));
        assertEquals(5, inventario.getQuantitaDisponibile(prodotto1));
    }

    @Test
    void testAggiungiProdottoGiaEsistente() {
        inventario.aggiungiProdotto(prodotto1, 5);
        assertTrue(inventario.aggiungiProdotto(prodotto1, 3));
        assertEquals(8, inventario.getQuantitaDisponibile(prodotto1));
    }

    @Test
    void testAggiungiProdottoConQuantitaNegativa() {
        assertFalse(inventario.aggiungiProdotto(prodotto1, -5));
        assertEquals(0, inventario.getQuantitaDisponibile(prodotto1));
    }

    @Test
    void testRimuoviProdotto() {
        inventario.aggiungiProdotto(prodotto1, 5);
        assertTrue(inventario.rimuoviProdotto(prodotto1, 3));
        assertEquals(2, inventario.getQuantitaDisponibile(prodotto1));
    }

    @Test
    void testRimuoviProdottoConQuantitaEccessiva() {
        inventario.aggiungiProdotto(prodotto1, 2);
        assertTrue(inventario.rimuoviProdotto(prodotto1, 5));
        assertEquals(0, inventario.getQuantitaDisponibile(prodotto1));
    }

    @Test
    void testRimuoviProdottoConQuantitaNegativa() {
        inventario.aggiungiProdotto(prodotto1, 5);
        assertFalse(inventario.rimuoviProdotto(prodotto1, -3));
        assertEquals(5, inventario.getQuantitaDisponibile(prodotto1));
    }

    @Test
    void testAzzeraProdotto() {
        inventario.aggiungiProdotto(prodotto1, 5);
        inventario.aggiungiProdotto(prodotto2, 7);
        inventario.azzeraProdotto(prodotto1);
        assertEquals(0, inventario.getQuantitaDisponibile(prodotto1));
        assertEquals(7, inventario.getQuantitaDisponibile(prodotto2));
    }

    @Test
    void testGetQuantitaDisponibile() {
        inventario.aggiungiProdotto(prodotto1, 5);
        assertEquals(5, inventario.getQuantitaDisponibile(prodotto1));
    }

    @Test
    void testControllaDisponibilita() {
        inventario.aggiungiProdotto(prodotto1, 5);
        assertTrue(inventario.controllaDisponibilita(prodotto1, 3));
    }

    @Test
    void testControllaDisponibilitaNonSufficiente() {
        inventario.aggiungiProdotto(prodotto1, 2);
        assertFalse(inventario.controllaDisponibilita(prodotto1, 3));
    }

    @Test
    void testControllaDisponibilitaNegativa() {
        inventario.aggiungiProdotto(prodotto1, 5);
        assertFalse(inventario.controllaDisponibilita(prodotto1, -3));
    }

    @Test
    void testElencoProdotti() {
        inventario.aggiungiProdotto(prodotto1, 5);
        inventario.aggiungiProdotto(prodotto2, 3);
        String elenco = inventario.elencoProdotti();
        assertTrue(elenco.contains(prodotto1.getNome()));
        assertTrue(elenco.contains(prodotto2.getNome()));
        assertTrue(elenco.contains("5"));
        assertTrue(elenco.contains("3"));
    }
}