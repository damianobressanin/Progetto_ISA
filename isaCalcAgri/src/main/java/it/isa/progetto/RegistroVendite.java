package it.isa.progetto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class RegistroVendite {
    private Map<String, double[]> venditeAgricolePerIVA;
    private SimpleDateFormat sdf;
    private final double[] ALIQUOTE_IVA = { 4.0, 5.0, 10.0, 22.0 };
    private final String FILENAME = "data/vendite_agricole.txt";

    public RegistroVendite() {
        venditeAgricolePerIVA = new HashMap<>();
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        setUp();
    }

    private void setUp() {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(FILENAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Errore nella creazione del file: " + e.getMessage());
            }
        }
    }

    void registraVendita(Date data, Prodotto prodotto, int quantita) {
        String dataFormattata = sdf.format(data);
        double[] venditeGiornaliere = venditeAgricolePerIVA.getOrDefault(dataFormattata,
                new double[ALIQUOTE_IVA.length]);
        int indexIVA = getIndexIVA(prodotto.getIVA());
        venditeGiornaliere[indexIVA] += prodotto.getPrezzo() * quantita;
        venditeAgricolePerIVA.put(dataFormattata, venditeGiornaliere);
    }

    private int getIndexIVA(double iva) {
        for (int i = 0; i < ALIQUOTE_IVA.length; i++) {
            if (ALIQUOTE_IVA[i] == iva) {
                return i;
            }
        }

        return -1;
    }

    void salvaVendite() {
        try {
            Map<String, double[]> datiEsistenti = caricaDatiEsistenti();

            for (Map.Entry<String, double[]> entry : venditeAgricolePerIVA.entrySet()) {
                double[] venditeEsistenti = datiEsistenti.getOrDefault(entry.getKey(), new double[ALIQUOTE_IVA.length]);
                for (int i = 0; i < ALIQUOTE_IVA.length; i++) {
                    venditeEsistenti[i] += entry.getValue()[i];
                }
                datiEsistenti.put(entry.getKey(), venditeEsistenti);
            }

            scriviDatiAggiornati(datiEsistenti);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, double[]> caricaDatiEsistenti() throws IOException {
        Map<String, double[]> dati = new HashMap<>();
        File file = new File(FILENAME);
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String[] parti = scanner.nextLine().split(" ");
                    String data = parti[0];
                    double[] vendite = new double[ALIQUOTE_IVA.length];
                    for (int i = 0; i < ALIQUOTE_IVA.length; i++) {
                        vendite[i] = Double.parseDouble(parti[i + 1]);
                    }
                    dati.put(data, vendite);
                }
            }
        }
        return dati;
    }

    private void scriviDatiAggiornati(Map<String, double[]> datiAggiornati) throws IOException {
        try (FileWriter writer = new FileWriter(FILENAME, false)) {
            for (Map.Entry<String, double[]> entry : datiAggiornati.entrySet()) {
                writer.write(formattaVenditeGiornaliere(entry.getKey(), entry.getValue()) + "\n");
            }
        }
        // aggiungo il clear per pulire il registro dopo aver salvato le vendite
        // altrimenti se salvo più volte sbaglia
        venditeAgricolePerIVA.clear();
    }

    private String formattaVenditeGiornaliere(String data, double[] vendite) {
        StringBuilder sb = new StringBuilder();
        sb.append(data);
        for (double vendita : vendite) {
            sb.append(" ").append(String.format("%.2f", vendita));
        }
        return sb.toString();
    }

    String stampaVenditePerData(String dataRicerca) {
        StringBuilder sb = new StringBuilder();

        try {
            File file = new File(FILENAME);
            if (!file.exists()) {
                return "Il file non esiste.\n";
            }

            try (Scanner scanner = new Scanner(file)) {
                boolean trovato = false;
                while (scanner.hasNextLine() && !trovato) {
                    String riga = scanner.nextLine();
                    String[] parti = riga.split(" ");
                    if (parti[0].equals(dataRicerca)) {
                        sb.append("Dettagli delle vendite per ").append(dataRicerca).append(":\n");

                        for (int i = 1; i < parti.length; i++) {
                            sb.append("Aliquota IVA ").append(ALIQUOTE_IVA[i - 1]).append("%: €").append(parti[i])
                                    .append("\n");
                        }

                        trovato = true;
                    }
                }

                if (!trovato) {
                    sb.append("Nessuna vendita trovata per la data: ").append(dataRicerca).append("\n");
                }
            }
        } catch (IOException e) {
            sb.append("Errore nella lettura del file: ").append(e.getMessage()).append("\n");
        }

        return sb.toString();

    }

    String visualizzaRegistro() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("\nREGISTRO VENDITE AGRICOLE:\n");

        Map<String, double[]> datiVendite = new HashMap<>();
        datiVendite = caricaDatiEsistenti();

        if (datiVendite.isEmpty()) {
            sb.append("Nessuna vendita presente nel registro.\n");
            return sb.toString();
        }

        List<Map.Entry<String, double[]>> datiOrdinati = new ArrayList<>(datiVendite.entrySet());
        datiOrdinati.sort(Map.Entry.<String, double[]>comparingByKey().reversed());

        for (Map.Entry<String, double[]> entry : datiOrdinati) {
            sb.append("Dettagli delle vendite per ").append(entry.getKey()).append(":\n");
            double[] venditeGiornaliere = entry.getValue();
            for (int i = 0; i < ALIQUOTE_IVA.length; i++) {
                sb.append("Aliquota IVA ").append(ALIQUOTE_IVA[i]).append("%: €")
                        .append(String.format("%.2f", venditeGiornaliere[i])).append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}