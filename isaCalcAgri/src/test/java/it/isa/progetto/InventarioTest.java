package it.isa.progetto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;

class InventarioTest {

    private Inventario inventario;
    private static Prodotto prodotto1;
    private static Prodotto prodotto2;
    private static Prodotto prodotto3;

    @BeforeAll
    static void setUpProdotti() {
        prodotto1 = Prodotto.creaOlioIbrida();
        prodotto2 = Prodotto.creaSacchettino();
        prodotto3 = Prodotto.creaPiantaLavanda();
    }

    @BeforeEach
    void setUp() {
        inventario = new Inventario();
    }

    @Test
    void testAggiungiProdotto() {
        assertTrue(inventario.aggiungiProdotto(prodotto1, 5));
        assertEquals(5, inventario.getQuantitaDisponibile(prodotto1));
    }

    @Test
    void testAggiungiProdottoGiaEsistente() {
        assertTrue(inventario.aggiungiProdotto(prodotto1, 5));
        assertTrue(inventario.aggiungiProdotto(prodotto1, 3));
        assertEquals(8, inventario.getQuantitaDisponibile(prodotto1));
    }

    @Test
    void testAggiungiProdottoQuantitaNegativa() {
        assertFalse(inventario.aggiungiProdotto(prodotto1, -5));
        assertEquals(0, inventario.getQuantitaDisponibile(prodotto1));
    }

    @Test
    void testAggiungiProdottoConQuantitaZero() {
        assertFalse(inventario.aggiungiProdotto(prodotto1, 0));
        assertEquals(0, inventario.getQuantitaDisponibile(prodotto1));
    }

    @Test
    void testAggiungiTantiProdottiInventario() {
        assertTrue(inventario.aggiungiProdotto(prodotto1, 15));
        assertTrue(inventario.aggiungiProdotto(prodotto2, 20));
        assertTrue(inventario.aggiungiProdotto(prodotto3, 58));

        assertTrue(inventario.aggiungiProdotto(prodotto1, 10));
        assertTrue(inventario.aggiungiProdotto(prodotto3, 2));

        assertEquals(25, inventario.getQuantitaDisponibile(prodotto1));
        assertEquals(20, inventario.getQuantitaDisponibile(prodotto2));
        assertEquals(60, inventario.getQuantitaDisponibile(prodotto3));
    }

    @Test
    void testRimuoviProdotto() {
        inventario.aggiungiProdotto(prodotto1, 5);
        assertTrue(inventario.rimuoviProdotto(prodotto1, 3));
        assertEquals(2, inventario.getQuantitaDisponibile(prodotto1));
    }

    @Test
    void testRimuoviTroppoProdotto() {
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
    void testRimuoviProdottoConQuantitaZero() {
        inventario.aggiungiProdotto(prodotto1, 5);
        assertFalse(inventario.rimuoviProdotto(prodotto1, 0));
        assertEquals(5, inventario.getQuantitaDisponibile(prodotto1));
    }

    @Test
    void testRimuoviStessoProdotto() {
        inventario.aggiungiProdotto(prodotto1, 6);
        inventario.aggiungiProdotto(prodotto2, 7);
        inventario.aggiungiProdotto(prodotto3, 10);

        assertTrue(inventario.rimuoviProdotto(prodotto1, 3));
        assertTrue(inventario.rimuoviProdotto(prodotto1, 2));

        assertEquals(1, inventario.getQuantitaDisponibile(prodotto1));
        assertEquals(7, inventario.getQuantitaDisponibile(prodotto2));
        assertEquals(10, inventario.getQuantitaDisponibile(prodotto3));

    }

    @Test
    void testRimuoviTantiProdotti() {
        inventario.aggiungiProdotto(prodotto1, 10);
        inventario.aggiungiProdotto(prodotto2, 15);
        inventario.aggiungiProdotto(prodotto3, 20);

        assertTrue(inventario.rimuoviProdotto(prodotto1, 2));
        assertTrue(inventario.rimuoviProdotto(prodotto2, 5));

        assertEquals(8, inventario.getQuantitaDisponibile(prodotto1));
        assertEquals(10, inventario.getQuantitaDisponibile(prodotto2));
        assertEquals(20, inventario.getQuantitaDisponibile(prodotto3));
    }

    @Test
    void testRimuoviProdottoNonEsistente() {
        inventario.aggiungiProdotto(prodotto1, 10);
        inventario.aggiungiProdotto(prodotto2, 20);

        assertFalse(inventario.rimuoviProdotto(prodotto3, 3));

        assertEquals(10, inventario.getQuantitaDisponibile(prodotto1));
        assertEquals(20, inventario.getQuantitaDisponibile(prodotto2));
        assertEquals(0, inventario.getQuantitaDisponibile(prodotto3));
    }

    @Test
    void testRimuoviEsattamenteProdotto() {
        inventario.aggiungiProdotto(prodotto1, 10);
        inventario.aggiungiProdotto(prodotto2, 17);

        assertTrue(inventario.rimuoviProdotto(prodotto1, 10));
        assertEquals(0, inventario.getQuantitaDisponibile(prodotto1));
        assertEquals(17, inventario.getQuantitaDisponibile(prodotto2));
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
    void testAzzeraProdottoNonEsistente() {
        inventario.aggiungiProdotto(prodotto1, 5);
        inventario.aggiungiProdotto(prodotto2, 7);
        inventario.azzeraProdotto(prodotto3);

        assertEquals(5, inventario.getQuantitaDisponibile(prodotto1));
        assertEquals(7, inventario.getQuantitaDisponibile(prodotto2));
        assertEquals(0, inventario.getQuantitaDisponibile(prodotto3));
    }

    @Test
    void testControllaDisponibilita() {
        inventario.aggiungiProdotto(prodotto1, 5);
        assertTrue(inventario.controllaDisponibilita(prodotto1, 3));
    }

    @Test
    void testControllaDisponibilitaNonSufficiente() {
        inventario.aggiungiProdotto(prodotto1, 2);
        assertFalse(inventario.controllaDisponibilita(prodotto1, 10));
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