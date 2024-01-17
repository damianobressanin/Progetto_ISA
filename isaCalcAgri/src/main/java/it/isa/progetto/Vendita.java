package it.isa.progetto;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;

public class Vendita {
    private Map<Prodotto, Integer> carrello;
    private Inventario inventario;
    private RegistroVendite registroVendite;

    public Vendita(Inventario inventario, RegistroVendite registroVendite) {
        this.carrello = new HashMap<>();
        this.inventario = inventario;
        this.registroVendite = registroVendite;
    }

    boolean aggiungiProdottoCarrello(Prodotto prodotto, int quantita) {
        if (quantita <= 0) {
            return false;
        }
        // Controllo disponibilità.
        // Se l'utente vuole una quantità maggiore di quella disponibile nell'inventario
        // allora aggiungi tutta la quantità disponibile nel carrello.
        int quantitaCorrente = carrello.getOrDefault(prodotto, 0);
        if (inventario.controllaDisponibilita(prodotto, (quantita + quantitaCorrente)) == false) {
            int quantitaDisponibile = inventario.getQuantitaDisponibile(prodotto);
            if (quantitaDisponibile == 0) {
                return false;
            } else {
                carrello.put(prodotto, quantitaDisponibile);
                return true;
            }

        }
        // Aggiorno il carrello
        carrello.put(prodotto, quantitaCorrente + quantita);
        return true;
    }

    int getQuantitaProdotto(Prodotto prodotto) {
        return carrello.getOrDefault(prodotto, 0);
    }

    void azzeraProdotto(Prodotto prodotto) {
        carrello.put(prodotto, 0);
    }

    boolean rimuoviProdotto(Prodotto prodotto, int quantita) {
        // se l'utente inserisce un numero negativo
        // oppure non c'è il prodotto sul carrello
        // allora non modificare la quantità
        int quantitaCarrello = getQuantitaProdotto(prodotto);
        if (quantita > 0 && quantitaCarrello > 0) {
            // se la quantità nel carrello è maggiore o uguale alla quantità da rimuovere
            // allora sottraila
            if (quantitaCarrello >= quantita) {
                carrello.put(prodotto, quantitaCarrello - quantita);
            } else {
                // altrimenti azzera
                azzeraProdotto(prodotto);
            }
            return true;
        } else {
            return false;
        }
    }

    String riepilogoVendita() {
        StringBuilder sb = new StringBuilder();
        sb.append("Totale: €").append(String.format("%.2f", calcolaTotale())).append("\n\n");
        sb.append("Totale scontrino fiscale immediato: €")
                .append(String.format("%.2f", calcolaTotaleCommerciale() + calcolaTotaleTrasformazione()))
                .append("\n\n");
        sb.append("Totale Agricolo: €").append(String.format("%.2f", calcolaTotaleAgricolo())).append("\n");
        sb.append("Totale Commerciale: €").append(String.format("%.2f", calcolaTotaleCommerciale())).append("\n");
        sb.append("Totale Trasformazione: €").append(String.format("%.2f", calcolaTotaleTrasformazione()))
                .append("\n\n");
        sb.append(visualizzaCarrello()).append("\n");
        return sb.toString();
    }

    double calcolaTotale() {
        double totale = 0.0;
        for (Map.Entry<Prodotto, Integer> entry : carrello.entrySet()) {
            totale += entry.getKey().getPrezzo() * entry.getValue();
        }
        return totale;
    }

    private double calcolaTotaleAgricolo() {
        double totale = 0.0;
        for (Map.Entry<Prodotto, Integer> entry : carrello.entrySet()) {
            if (entry.getKey().getTipo() == TipoProdotto.AGRICOLO) {
                totale += entry.getKey().getPrezzo() * entry.getValue();
            }
        }
        return totale;
    }

    private double calcolaTotaleCommerciale() {
        double totale = 0.0;
        for (Map.Entry<Prodotto, Integer> entry : carrello.entrySet()) {
            if (entry.getKey().getTipo() == TipoProdotto.COMMERCIALE) {
                totale += entry.getKey().getPrezzo() * entry.getValue();
            }
        }
        return totale;
    }

    private double calcolaTotaleTrasformazione() {
        double totale = 0.0;
        for (Map.Entry<Prodotto, Integer> entry : carrello.entrySet()) {
            if (entry.getKey().getTipo() == TipoProdotto.TRASFORMAZIONE) {
                totale += entry.getKey().getPrezzo() * entry.getValue();
            }
        }
        return totale;
    }

    String visualizzaCarrello() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nCARRELLO:\n");
        for (Map.Entry<Prodotto, Integer> entry : carrello.entrySet()) {
            sb.append(entry.getKey().getNome());
            sb.append(" - Quantità: ");
            sb.append(entry.getValue());
            sb.append(" - Prezzo unitario: €");
            sb.append(String.format("%.2f", entry.getKey().getPrezzo()));
            sb.append("\n");
        }
        return sb.toString();
    }

    void svuotaCarrello() {
        carrello.clear();
    }

    void finalizzaVendita() {
        // aggiungo al registroVendite solo i prodotti agricoli venduti
        for (Map.Entry<Prodotto, Integer> entry : carrello.entrySet()) {
            if (entry.getKey().getTipo() == TipoProdotto.AGRICOLO) {
                registroVendite.registraVendita(new Date(), entry.getKey(), entry.getValue());
            }
        }
        // Rimuovo dall'inventario tutti i prodotti acquistati dal cliente
        for (Map.Entry<Prodotto, Integer> entry : carrello.entrySet()) {
            inventario.rimuoviProdotto(entry.getKey(), entry.getValue());
        }
        // Svuota il carrello dopo aver completato la vendita
        svuotaCarrello();
    }
}