package it.isa.progetto;

import java.util.Objects;

public class Prodotto {
    private final String nome;
    private final double prezzo;
    private final TipoProdotto tipo;
    private final double IVA;

    private Prodotto(String nome, double prezzo, TipoProdotto tipo, double IVA) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.tipo = tipo;
        this.IVA = IVA;
    }

    public static Prodotto creaPiantaLavanda() {
        return new Prodotto("Pianta lavanda", 8.50, TipoProdotto.AGRICOLO, 10.0);
    }

    public static Prodotto creaPiantaSalvia() {
        return new Prodotto("Pianta salvia", 5.0, TipoProdotto.AGRICOLO, 5.0);
    }

    public static Prodotto creaPiantaRosmarino() {
        return new Prodotto("Pianta rosmarino", 4.5, TipoProdotto.AGRICOLO, 5.0);
    }

    public static Prodotto creaMazzo() {
        return new Prodotto("Mazzo", 8.0, TipoProdotto.AGRICOLO, 4.0);
    }

    public static Prodotto creaSacchettino() {
        return new Prodotto("Sacchettino", 5.0, TipoProdotto.AGRICOLO, 4.0);
    }

    public static Prodotto creaGufetto() {
        return new Prodotto("Gufetto", 10.0, TipoProdotto.AGRICOLO, 22.0);
    }

    public static Prodotto creaCuore() {
        return new Prodotto("Cuore", 8.0, TipoProdotto.AGRICOLO, 22.0);
    }

    public static Prodotto creaSottoCuscino() {
        return new Prodotto("Sotto cuscino", 6.0, TipoProdotto.AGRICOLO, 4.0);
    }

    public static Prodotto creaCremaMani() {
        return new Prodotto("Crema mani", 8.90, TipoProdotto.COMMERCIALE, 22.0);
    }

    public static Prodotto creaBagnodoccia() {
        return new Prodotto("Bagnodoccia", 7.90, TipoProdotto.COMMERCIALE, 22.0);
    }

    public static Prodotto creaSaponetta() {
        return new Prodotto("Saponetta", 4.90, TipoProdotto.COMMERCIALE, 22.0);
    }

    public static Prodotto creaOlioAngustifolia() {
        return new Prodotto("Olio essenziale angustifolia", 11.0, TipoProdotto.TRASFORMAZIONE, 22.0);
    }

    public static Prodotto creaOlioIbrida() {
        return new Prodotto("Olio essenziale ibrida", 11.0, TipoProdotto.TRASFORMAZIONE, 22.0);
    }

    public String getNome() {
        return nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public TipoProdotto getTipo() {
        return tipo;
    }

    public double getIVA() {
        return IVA;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Prodotto{");
        sb.append("nome='").append(nome).append('\'');
        sb.append(", prezzo=").append(prezzo);
        sb.append(", tipo=").append(tipo);
        sb.append(", IVA=").append(IVA);
        sb.append('}');
        return sb.toString();
    }

    /*
     * Override di equals e di hashCode per manipolare meglio gli oggetti con le
     * HashMap
     */
    @Override
    public boolean equals(Object o) {
        // due prodotti sono uguali se hanno lo stesso indirizzo di memoria
        if (this == o) {
            return true;
        }
        // se è null oppure sono classi diverse allora non sono uguali
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        // casting per poter accedere ai campi
        Prodotto prodotto = (Prodotto) o;
        return Objects.equals(this.getNome(), prodotto.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getNome());
    }
}