package it.isa.progetto;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProdottoTest {

    @Test
    void testCreaPiantaLavanda() {
        Prodotto piantaLavanda = Prodotto.creaPiantaLavanda();
        assertNotNull(piantaLavanda);
        assertEquals("Pianta lavanda", piantaLavanda.getNome());
        assertEquals(8.50, piantaLavanda.getPrezzo());
        assertEquals(TipoProdotto.AGRICOLO, piantaLavanda.getTipo());
        assertEquals(10.0, piantaLavanda.getIVA());
    }

    @Test
    void testCreaPiantaSalvia() {
        Prodotto piantaSalvia = Prodotto.creaPiantaSalvia();
        assertNotNull(piantaSalvia);
        assertEquals("Pianta salvia", piantaSalvia.getNome());
        assertEquals(5.0, piantaSalvia.getPrezzo());
        assertEquals(TipoProdotto.AGRICOLO, piantaSalvia.getTipo());
        assertEquals(5.0, piantaSalvia.getIVA());
    }

    @Test
    void testCreaPiantaRosmarino() {
        Prodotto piantaRosmarino = Prodotto.creaPiantaRosmarino();
        assertNotNull(piantaRosmarino);
        assertEquals("Pianta rosmarino", piantaRosmarino.getNome());
        assertEquals(4.5, piantaRosmarino.getPrezzo());
        assertEquals(TipoProdotto.AGRICOLO, piantaRosmarino.getTipo());
        assertEquals(5.0, piantaRosmarino.getIVA());
    }

    @Test
    void testCreaMazzo() {
        Prodotto mazzo = Prodotto.creaMazzo();
        assertNotNull(mazzo);
        assertEquals("Mazzo", mazzo.getNome());
        assertEquals(8.0, mazzo.getPrezzo());
        assertEquals(TipoProdotto.AGRICOLO, mazzo.getTipo());
        assertEquals(4.0, mazzo.getIVA());
    }

    @Test
    void testCreaSacchettino() {
        Prodotto sacchettino = Prodotto.creaSacchettino();
        assertNotNull(sacchettino);
        assertEquals("Sacchettino", sacchettino.getNome());
        assertEquals(5.0, sacchettino.getPrezzo());
        assertEquals(TipoProdotto.AGRICOLO, sacchettino.getTipo());
        assertEquals(4.0, sacchettino.getIVA());
    }

    @Test
    void testCreaGufetto() {
        Prodotto gufetto = Prodotto.creaGufetto();
        assertNotNull(gufetto);
        assertEquals("Gufetto", gufetto.getNome());
        assertEquals(10.0, gufetto.getPrezzo());
        assertEquals(TipoProdotto.AGRICOLO, gufetto.getTipo());
        assertEquals(22.0, gufetto.getIVA());
    }

    @Test
    void testCreaCuore() {
        Prodotto cuore = Prodotto.creaCuore();
        assertNotNull(cuore);
        assertEquals("Cuore", cuore.getNome());
        assertEquals(8.0, cuore.getPrezzo());
        assertEquals(TipoProdotto.AGRICOLO, cuore.getTipo());
        assertEquals(22.0, cuore.getIVA());
    }

    @Test
    void testCreaSottoCuscino() {
        Prodotto sottoCuscino = Prodotto.creaSottoCuscino();
        assertNotNull(sottoCuscino);
        assertEquals("Sotto cuscino", sottoCuscino.getNome());
        assertEquals(6.0, sottoCuscino.getPrezzo());
        assertEquals(TipoProdotto.AGRICOLO, sottoCuscino.getTipo());
        assertEquals(4.0, sottoCuscino.getIVA());
    }

    @Test
    void testCreaCremaMani() {
        Prodotto cremaMani = Prodotto.creaCremaMani();
        assertNotNull(cremaMani);
        assertEquals("Crema mani", cremaMani.getNome());
        assertEquals(8.90, cremaMani.getPrezzo());
        assertEquals(TipoProdotto.COMMERCIALE, cremaMani.getTipo());
        assertEquals(22.0, cremaMani.getIVA());
    }

    @Test
    void testCreaBagnodoccia() {
        Prodotto bagnodoccia = Prodotto.creaBagnodoccia();
        assertNotNull(bagnodoccia);
        assertEquals("Bagnodoccia", bagnodoccia.getNome());
        assertEquals(7.90, bagnodoccia.getPrezzo());
        assertEquals(TipoProdotto.COMMERCIALE, bagnodoccia.getTipo());
        assertEquals(22.0, bagnodoccia.getIVA());
    }

    @Test
    void testCreaSaponetta() {
        Prodotto saponetta = Prodotto.creaSaponetta();
        assertNotNull(saponetta);
        assertEquals("Saponetta", saponetta.getNome());
        assertEquals(4.90, saponetta.getPrezzo());
        assertEquals(TipoProdotto.COMMERCIALE, saponetta.getTipo());
        assertEquals(22.0, saponetta.getIVA());
    }

    @Test
    void testCreaOlioAngustifolia() {
        Prodotto olioAngustifolia = Prodotto.creaOlioAngustifolia();
        assertNotNull(olioAngustifolia);
        assertEquals("Olio essenziale angustifolia", olioAngustifolia.getNome());
        assertEquals(11.0, olioAngustifolia.getPrezzo());
        assertEquals(TipoProdotto.TRASFORMAZIONE, olioAngustifolia.getTipo());
        assertEquals(22.0, olioAngustifolia.getIVA());
    }

    @Test
    void testCreaOlioIbrida() {
        Prodotto olioIbrida = Prodotto.creaOlioIbrida();
        assertNotNull(olioIbrida);
        assertEquals("Olio essenziale ibrida", olioIbrida.getNome());
        assertEquals(11.0, olioIbrida.getPrezzo());
        assertEquals(TipoProdotto.TRASFORMAZIONE, olioIbrida.getTipo());
        assertEquals(22.0, olioIbrida.getIVA());
    }
}