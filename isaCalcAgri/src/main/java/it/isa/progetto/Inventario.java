package it.isa.progetto;

import java.util.HashMap;
import java.util.Map;

public class Inventario {
    private Map<Prodotto, Integer> prodottiDisponibili;

    public Inventario() {
        prodottiDisponibili = new HashMap<>();
    }

    boolean aggiungiProdotto(Prodotto prodotto, int quantita) {
        // se l'utente inserisce un numero negativo
        // allora viene ignorato
        if (quantita > 0) {
            prodottiDisponibili.put(prodotto, prodottiDisponibili.getOrDefault(prodotto, 0) + quantita);
            return true;
        } else {
            return false;
        }
    }

    boolean rimuoviProdotto(Prodotto prodotto, int quantita) {
        // se l'utente inserisce un numero negativo
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

    void azzeraProdotto(Prodotto prodotto) {
        // prodottiDisponibili.put(prodotto, 0);
        prodottiDisponibili.put(prodotto, 1); // introduco errore per testare pipeline
    }

    int getQuantitaDisponibile(Prodotto prodotto) {
        return prodottiDisponibili.getOrDefault(prodotto, 0);
    }

    boolean controllaDisponibilita(Prodotto prodotto, int quantita) {
        if (quantita > 0) {
            return (getQuantitaDisponibile(prodotto) >= quantita);
        } else {
            return false;
        }
    }

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

    boolean aggiungiQuantitaTuttoInventario(int quantita) {
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
            return true;
        } else {
            return false;
        }
    }

}