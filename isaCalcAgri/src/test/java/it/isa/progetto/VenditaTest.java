package it.isa.progetto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VenditaTest {
    private Vendita vendita;
    private Inventario inventario;
    private RegistroVendite registroVendite;
    private static Prodotto prodotto1;
    private static Prodotto prodotto2;
    private static Prodotto prodotto3;
    private static Prodotto prodotto4;
    private static Prodotto prodotto5;

    @BeforeAll
    static void setUpProdotti() {
        // i prodotti hanno i campi final, quindi li metto nel BeforeAll
        prodotto1 = Prodotto.creaMazzo();
        prodotto2 = Prodotto.creaPiantaSalvia();
        prodotto3 = Prodotto.creaPiantaLavanda();
        prodotto4 = Prodotto.creaCremaMani();
        prodotto5 = Prodotto.creaOlioAngustifolia();
    }

    @BeforeEach
    void setUp() {
        inventario = new Inventario();
        inventario.aggiungiProdotto(prodotto1, 10);
        inventario.aggiungiProdotto(prodotto2, 10);
        inventario.aggiungiProdotto(prodotto3, 10);
        inventario.aggiungiProdotto(prodotto4, 10);
        inventario.aggiungiProdotto(prodotto5, 10);

        registroVendite = new RegistroVendite();

        vendita = new Vendita(inventario, registroVendite);
    }

    @Test
    void testAggiungiProdottoCarrello() {
        assertTrue(vendita.aggiungiProdottoCarrello(prodotto1, 5));
        assertEquals(5, vendita.getQuantitaProdotto(prodotto1));
    }

    @Test
    void testAggiungiProdottoCarrelloGiaEsistente() {
        assertTrue(vendita.aggiungiProdottoCarrello(prodotto1, 2));
        assertTrue(vendita.aggiungiProdottoCarrello(prodotto1, 7));

        assertEquals(9, vendita.getQuantitaProdotto(prodotto1));
    }

    @Test
    void testAggiungiTantiProdottiCarrello() {
        assertTrue(vendita.aggiungiProdottoCarrello(prodotto1, 1));
        assertTrue(vendita.aggiungiProdottoCarrello(prodotto2, 3));
        assertTrue(vendita.aggiungiProdottoCarrello(prodotto3, 8));
        assertTrue(vendita.aggiungiProdottoCarrello(prodotto4, 7));
        assertTrue(vendita.aggiungiProdottoCarrello(prodotto5, 5));

        assertTrue(vendita.aggiungiProdottoCarrello(prodotto1, 5));
        assertTrue(vendita.aggiungiProdottoCarrello(prodotto2, 1));

        assertEquals(6, vendita.getQuantitaProdotto(prodotto1));
        assertEquals(4, vendita.getQuantitaProdotto(prodotto2));
        assertEquals(8, vendita.getQuantitaProdotto(prodotto3));
        assertEquals(7, vendita.getQuantitaProdotto(prodotto4));
        assertEquals(5, vendita.getQuantitaProdotto(prodotto5));
    }

    @Test
    void testAggiungiProdottoCarrelloQuantitaNegativa() {
        assertFalse(vendita.aggiungiProdottoCarrello(prodotto1, -5));
        assertEquals(0, vendita.getQuantitaProdotto(prodotto1));
    }

    @Test
    void testAggiungiProdottoCarrelloQuantitaZero() {
        assertFalse(vendita.aggiungiProdottoCarrello(prodotto1, 0));
        assertEquals(0, vendita.getQuantitaProdotto(prodotto1));
    }

    @Test
    void testAggiungiProdottoCarrelloTuttaQuantitaDisponibile() {
        int quantitaDisponibile = inventario.getQuantitaDisponibile(prodotto1);

        assertTrue(vendita.aggiungiProdottoCarrello(prodotto1, quantitaDisponibile));
        assertEquals(quantitaDisponibile, vendita.getQuantitaProdotto(prodotto1));
    }

    @Test
    void testAggiungiProdottoCarrelloTroppaQuantita() {
        int quantitaDisponibile = inventario.getQuantitaDisponibile(prodotto1);

        assertTrue(vendita.aggiungiProdottoCarrello(prodotto1, quantitaDisponibile * 2));
        assertEquals(quantitaDisponibile, vendita.getQuantitaProdotto(prodotto1));
    }

    @Test
    void testRimuoviProdotto() {
        vendita.aggiungiProdottoCarrello(prodotto1, 7);

        assertTrue(vendita.rimuoviProdotto(prodotto1, 2));
        assertEquals(5, vendita.getQuantitaProdotto(prodotto1));
    }

    @Test
    void testRimuoviStessoProdotto() {
        vendita.aggiungiProdottoCarrello(prodotto1, 7);

        assertTrue(vendita.rimuoviProdotto(prodotto1, 2));
        assertTrue(vendita.rimuoviProdotto(prodotto1, 2));
        assertEquals(3, vendita.getQuantitaProdotto(prodotto1));
    }

    @Test
    void testRimuoviTantiProdotti() {
        vendita.aggiungiProdottoCarrello(prodotto1, 7);
        vendita.aggiungiProdottoCarrello(prodotto2, 3);
        vendita.aggiungiProdottoCarrello(prodotto3, 9);
        vendita.aggiungiProdottoCarrello(prodotto4, 4);
        vendita.aggiungiProdottoCarrello(prodotto5, 5);

        assertTrue(vendita.rimuoviProdotto(prodotto1, 5));
        assertTrue(vendita.rimuoviProdotto(prodotto2, 2));

        assertEquals(2, vendita.getQuantitaProdotto(prodotto1));
        assertEquals(1, vendita.getQuantitaProdotto(prodotto2));
        assertEquals(9, vendita.getQuantitaProdotto(prodotto3));
        assertEquals(4, vendita.getQuantitaProdotto(prodotto4));
        assertEquals(5, vendita.getQuantitaProdotto(prodotto5));
    }

    @Test
    void testRimuoviProdottoQuantitaNegativa() {
        vendita.aggiungiProdottoCarrello(prodotto1, 7);

        assertFalse(vendita.rimuoviProdotto(prodotto1, -2));
        assertEquals(7, vendita.getQuantitaProdotto(prodotto1));
    }

    @Test
    void testRimuoviProdottoQuantitaZero() {
        vendita.aggiungiProdottoCarrello(prodotto1, 5);

        assertFalse(vendita.rimuoviProdotto(prodotto1, 0));
        assertEquals(5, vendita.getQuantitaProdotto(prodotto1));
    }

    @Test
    void testRimuoviProdottoQuantitaCarrello() {
        vendita.aggiungiProdottoCarrello(prodotto1, 5);

        assertTrue(vendita.rimuoviProdotto(prodotto1, 5));
        assertEquals(0, vendita.getQuantitaProdotto(prodotto1));
    }

    @Test
    void testRimuoviTroppoProdotto() {
        vendita.aggiungiProdottoCarrello(prodotto1, 5);

        assertTrue(vendita.rimuoviProdotto(prodotto1, 100));
        assertEquals(0, vendita.getQuantitaProdotto(prodotto1));
    }

    @Test
    void testRimuoviProdottoNonNelCarrello() {
        assertFalse(vendita.rimuoviProdotto(prodotto1, 5));
        assertEquals(0, vendita.getQuantitaProdotto(prodotto1));
    }

    @Test
    void testTotale() {
        vendita.aggiungiProdottoCarrello(prodotto1, 5);
        vendita.aggiungiProdottoCarrello(prodotto2, 3);
        vendita.aggiungiProdottoCarrello(prodotto3, 9);
        vendita.aggiungiProdottoCarrello(prodotto4, 4);
        vendita.aggiungiProdottoCarrello(prodotto5, 0);

        double totale = vendita.getQuantitaProdotto(prodotto1) * prodotto1.getPrezzo()
                + vendita.getQuantitaProdotto(prodotto2) * prodotto2.getPrezzo()
                + vendita.getQuantitaProdotto(prodotto3) * prodotto3.getPrezzo()
                + vendita.getQuantitaProdotto(prodotto4) * prodotto4.getPrezzo()
                + vendita.getQuantitaProdotto(prodotto5) * prodotto5.getPrezzo();
        assertEquals(totale, vendita.calcolaTotale());
    }

    @Test
    void testTotaleCarrelloVuoto() {
        assertEquals(0, vendita.calcolaTotale());
    }

    @Test
    void testFinalizzaVendita() {
        vendita.aggiungiProdottoCarrello(prodotto1, 5);
        vendita.aggiungiProdottoCarrello(prodotto1, 1);
        vendita.aggiungiProdottoCarrello(prodotto2, -3);
        vendita.aggiungiProdottoCarrello(prodotto3, 11);
        vendita.aggiungiProdottoCarrello(prodotto4, 4);
        vendita.rimuoviProdotto(prodotto4, 1);
        vendita.aggiungiProdottoCarrello(prodotto5, 0);

        vendita.finalizzaVendita();

        assertEquals(4, inventario.getQuantitaDisponibile(prodotto1));
        assertEquals(10, inventario.getQuantitaDisponibile(prodotto2));
        assertEquals(0, inventario.getQuantitaDisponibile(prodotto3));
        assertEquals(7, inventario.getQuantitaDisponibile(prodotto4));
        assertEquals(10, inventario.getQuantitaDisponibile(prodotto5));
    }
}