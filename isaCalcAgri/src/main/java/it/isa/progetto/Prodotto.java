package it.isa.progetto;

public class Prodotto {
    private String nome;
    private double prezzo;
    private TipoProdotto tipo;
    private double IVA;

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

    public String toString() {
        return "Prodotto{" +
                "nome='" + nome + '\'' +
                ", prezzo=" + prezzo +
                ", tipo=" + tipo +
                ", IVA=" + IVA +
                '}';
    }
}