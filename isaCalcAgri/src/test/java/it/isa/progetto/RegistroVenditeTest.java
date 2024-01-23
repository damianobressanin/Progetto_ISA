package it.isa.progetto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class RegistroVenditeTest {
    private RegistroVendite registroVendite;
    private static Prodotto prodottoAgricolo1;
    private static Prodotto prodottoAgricolo2;
    private static Prodotto prodottoAgricolo3;
    private static Prodotto prodottoAgricolo4;
    private static Prodotto prodottoAgricolo5;
    private final String FILENAME = "data/vendite_agricole.txt";

    @BeforeAll
    static void setUpProdotti() {
        // i prodotti hanno i campi final, quindi li metto nel BeforeAll
        prodottoAgricolo1 = Prodotto.creaPiantaLavanda(); // iva al 10%
        prodottoAgricolo2 = Prodotto.creaGufetto(); // iva al 22%
        prodottoAgricolo3 = Prodotto.creaCuore(); // iva al 22%
        prodottoAgricolo4 = Prodotto.creaPiantaSalvia(); // iva al 5%
        prodottoAgricolo5 = Prodotto.creaMazzo(); // iva al 4%

        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    @BeforeEach
    void setUp() throws IOException {
        registroVendite = new RegistroVendite();

        File file = new File(FILENAME);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Errore nella creazione del file: " + e.getMessage());
        }
    }

    @AfterEach
    void pulizia() {
        File file = new File(FILENAME);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testRegistraVendita() throws IOException {
        Date data = new Date();
        registroVendite.registraVendita(data, prodottoAgricolo1, 5);
        registroVendite.salvaVendite();

        BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
        String firstLine = reader.readLine();
        reader.close();

        assertNotNull(firstLine);

        String[] parti = firstLine.split(" ");

        assertEquals(parti[0], new SimpleDateFormat("yyyy-MM-dd").format(data));
        assertEquals(parti[1], (String.format("%.2f", 0.00)));
        assertEquals(parti[2], (String.format("%.2f", 0.00)));
        assertEquals(parti[3], (String.format("%.2f", (prodottoAgricolo1.getPrezzo() * 5))));
        assertEquals(parti[4], (String.format("%.2f", 0.00)));

    }

    @Test
    void testRegistraVenditaIvaDiversa() throws IOException {
        Date data = new Date();
        registroVendite.registraVendita(data, prodottoAgricolo1, 5);
        registroVendite.registraVendita(data, prodottoAgricolo2, 1);
        registroVendite.registraVendita(data, prodottoAgricolo5, 1);
        registroVendite.salvaVendite();

        BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
        String firstLine = reader.readLine();
        reader.close();

        assertNotNull(firstLine);

        String[] parti = firstLine.split(" ");

        assertEquals(parti[0], new SimpleDateFormat("yyyy-MM-dd").format(data));
        assertEquals(parti[1], (String.format("%.2f", (prodottoAgricolo5.getPrezzo() * 1))));
        assertEquals(parti[2], (String.format("%.2f", 0.00)));
        assertEquals(parti[3], (String.format("%.2f", (prodottoAgricolo1.getPrezzo() * 5))));
        assertEquals(parti[4], (String.format("%.2f", (prodottoAgricolo2.getPrezzo() * 1))));

    }

    @Test
    void testRegistraVenditaIvaUguale() throws IOException {
        Date data = new Date();

        registroVendite.registraVendita(data, prodottoAgricolo1, 1);
        registroVendite.registraVendita(data, prodottoAgricolo2, 2);
        registroVendite.registraVendita(data, prodottoAgricolo3, 7);
        registroVendite.registraVendita(data, prodottoAgricolo5, 3);
        registroVendite.salvaVendite();

        BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
        String firstLine = reader.readLine();
        reader.close();

        assertNotNull(firstLine);

        String[] parti = firstLine.split(" ");

        assertEquals(parti[0], new SimpleDateFormat("yyyy-MM-dd").format(data));
        assertEquals(parti[1], (String.format("%.2f", (prodottoAgricolo5.getPrezzo() * 3))));
        assertEquals(parti[2], (String.format("%.2f", 0.00)));
        assertEquals(parti[3], (String.format("%.2f", (prodottoAgricolo1.getPrezzo() * 1))));
        assertEquals(parti[4],
                (String.format("%.2f", (prodottoAgricolo2.getPrezzo() * 2 + prodottoAgricolo3.getPrezzo() * 7))));
    }

    @Test
    void testSalvaVendite() throws IOException {
        Date data = new Date();
        /*
         * prodottoAgricolo1 = iva al 10%
         * prodottoAgricolo2 = iva al 22%
         * prodottoAgricolo3 = iva al 22%
         * prodottoAgricolo4 = iva al 5%
         */
        registroVendite.registraVendita(data, prodottoAgricolo1, 5);
        registroVendite.registraVendita(data, prodottoAgricolo2, 2);
        registroVendite.registraVendita(data, prodottoAgricolo4, 1);
        registroVendite.salvaVendite();

        BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
        String firstLine = reader.readLine();
        reader.close();
        assertNotNull(firstLine);
        String[] prima = firstLine.split(" ");

        registroVendite.registraVendita(data, prodottoAgricolo1, 1);
        registroVendite.registraVendita(data, prodottoAgricolo3, 1);
        registroVendite.salvaVendite();

        reader = new BufferedReader(new FileReader(FILENAME));
        firstLine = reader.readLine();
        reader.close();
        String[] dopo = firstLine.split(" ");
        assertNotNull(firstLine);

        double totale3 = Double.parseDouble(prima[3]) + prodottoAgricolo1.getPrezzo() * 1;
        double totale4 = Double.parseDouble(prima[4]) + prodottoAgricolo3.getPrezzo() * 1;

        // data
        assertEquals(prima[0], dopo[0]);
        // iva 4%
        assertEquals(prima[1], dopo[1]);
        assertEquals(dopo[1], "0.00");
        // iva 5%
        assertEquals(prima[2], dopo[2]);
        assertEquals(dopo[2], String.format("%.2f", prodottoAgricolo4.getPrezzo() * 1));
        // iva 10%
        assertEquals(String.format("%.2f", totale3), dopo[3]);
        // iva 22%
        assertEquals(String.format("%.2f", totale4), dopo[4]);
    }

    @Test
    void testStampaVenditePerData() throws IOException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date data1 = sdf.parse("2023-05-01");
        Date data2 = sdf.parse("2023-05-07");

        registroVendite.registraVendita(data1, prodottoAgricolo1, 5);
        registroVendite.registraVendita(data2, prodottoAgricolo2, 10);

        registroVendite.salvaVendite();

        String dataRicerca = "2023-05-01";
        String risultato = registroVendite.stampaVenditePerData(dataRicerca);

        assertTrue(risultato.contains("Dettagli delle vendite"));
        assertTrue(risultato.contains(String.format("%.2f", (prodottoAgricolo1.getPrezzo() * 5))));

        String risultatoNessunaVendita = registroVendite.stampaVenditePerData("9999-01-01");

        assertTrue(risultatoNessunaVendita.contains("Nessuna vendita trovata"));
    }

}