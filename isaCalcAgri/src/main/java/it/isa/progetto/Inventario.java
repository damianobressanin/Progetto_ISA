package it.isa.progetto;

import java.util.HashMap;
import java.util.Map;

public class Inventario {
    private Map<Prodotto, Integer> prodottiDisponibili;

    public Inventario() {
        prodottiDisponibili = new HashMap<>();
    }

    // per aggiungere una certa quantità di un prodotto all'inventario
    boolean aggiungiProdotto(Prodotto prodotto, int quantita) {
        // se l'utente inserisce un numero <=0
        // allora viene ignorato
        if (quantita > 0) {
            prodottiDisponibili.put(prodotto, prodottiDisponibili.getOrDefault(prodotto, 0) + quantita);
            return true;
        } else {
            return false;
        }
    }

    // per rimuovere una certa quantità di un prodotto all'inventario
    boolean rimuoviProdotto(Prodotto prodotto, int quantita) {
        // se l'utente inserisce un numero <= 0
        // oppure se l'inventario non contiene il prodotto
        // allora non modificare la quantità

        int quantitaDisponibile = getQuantitaDisponibile(prodotto);
        if (quantita > 0 && quantitaDisponibile > 0) {
            // se la quantità disponibile è maggiore o uguale alla quantità da rimuovere
            // allora rimuovila
            if (quantitaDisponibile >= quantita) {
                prodottiDisponibili.put(prodotto, quantitaDisponibile - quantita);
            } else {
                // altrimenti azzera
                azzeraProdotto(prodotto);
            }
            return true;
        } else {
            return false;
        }
    }

    // imposta la quantità disponibile di un prodotto a 0
    void azzeraProdotto(Prodotto prodotto) {
        prodottiDisponibili.put(prodotto, 0);
    }

    // restituisce la quantità disponibile di un prodotto
    int getQuantitaDisponibile(Prodotto prodotto) {
        return prodottiDisponibili.getOrDefault(prodotto, 0);
    }

    // controlla se la quantità disponibile di un prodotto
    // è maggiore o uguale alla quantità richiesta
    boolean controllaDisponibilita(Prodotto prodotto, int quantita) {
        if (quantita > 0) {
            return (getQuantitaDisponibile(prodotto) >= quantita);
        } else {
            return false;
        }
    }

    // per creare una stringa che rappresenta l'inventario e il suo contenuto
    String elencoProdotti() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nINVENTARIO:\n");
        for (Map.Entry<Prodotto, Integer> entry : prodottiDisponibili.entrySet()) {
            sb.append(entry.getKey().getNome());
            sb.append(" - Quantità: ");
            sb.append(entry.getValue());
            sb.append(" - Prezzo unitario: €");
            sb.append(String.format("%.2f", entry.getKey().getPrezzo()));
            sb.append("\n");
        }
        return sb.toString();
    }

    // per aggiungere una certa quantità di tutti i prodotti nell'inventario
    // usato per testare meglio il programma da terminale
    void aggiungiQuantitaTuttoInventario(int quantita) {
        if (quantita > 0) {
            aggiungiProdotto(Prodotto.creaPiantaLavanda(), quantita);
            aggiungiProdotto(Prodotto.creaPiantaSalvia(), quantita);
            aggiungiProdotto(Prodotto.creaPiantaRosmarino(), quantita);
            aggiungiProdotto(Prodotto.creaMazzo(), quantita);
            aggiungiProdotto(Prodotto.creaSacchettino(), quantita);
            aggiungiProdotto(Prodotto.creaGufetto(), quantita);
            aggiungiProdotto(Prodotto.creaCuore(), quantita);
            aggiungiProdotto(Prodotto.creaSottoCuscino(), quantita);
            aggiungiProdotto(Prodotto.creaCremaMani(), quantita);
            aggiungiProdotto(Prodotto.creaBagnodoccia(), quantita);
            aggiungiProdotto(Prodotto.creaSaponetta(), quantita);
            aggiungiProdotto(Prodotto.creaOlioAngustifolia(), quantita);
            aggiungiProdotto(Prodotto.creaOlioIbrida(), quantita);
        }
    }
}