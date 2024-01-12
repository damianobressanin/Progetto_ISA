package it.isa.progetto;

import java.util.HashMap;
import java.util.Map;

public class Inventario {
    private Map<Prodotto, Integer> prodottiDisponibili;

    public Inventario() {
        prodottiDisponibili = new HashMap<>();
    }

    public boolean aggiungiProdotto(Prodotto prodotto, int quantita) {
        // se l'utente inserisce un numero negativo
        // allora viene ignorato
        if (quantita > 0) {
            prodottiDisponibili.put(prodotto, prodottiDisponibili.getOrDefault(prodotto, 0) + quantita);
            return true;
        } else {
            return false;
        }
    }

    public boolean rimuoviProdotto(Prodotto prodotto, int quantita) {
        // se l'utente inserisce un numero negativo
        // allora non modificare la quantità
        if (quantita > 0) {
            int quantitaDisponibile = getQuantitaDisponibile(prodotto);
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

    public void azzeraProdotto(Prodotto prodotto) {
        prodottiDisponibili.put(prodotto, 0);
    }

    public int getQuantitaDisponibile(Prodotto prodotto) {
        return prodottiDisponibili.getOrDefault(prodotto, 0);
    }

    public boolean controllaDisponibilita(Prodotto prodotto, int quantita) {
        if (quantita > 0) {
            return (getQuantitaDisponibile(prodotto) >= quantita);
        } else {
            return false;
        }
    }

    public String elencoProdotti() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Prodotto, Integer> entry : prodottiDisponibili.entrySet()) {
            sb.append(entry.getKey());
            sb.append(" - Quantità: ");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }
}