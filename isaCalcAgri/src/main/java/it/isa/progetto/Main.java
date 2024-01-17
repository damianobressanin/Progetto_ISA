package it.isa.progetto;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventario inventario = new Inventario();
        RegistroVendite registroVendite = new RegistroVendite();
        Vendita vendita = new Vendita(inventario, registroVendite);

        System.out.println("\nBenvenuto nel programma di gestione delle vendite!\n");

        gestioneInventario(scanner, inventario);

        boolean continua = true;

        while (continua) {
            System.out.println("\n");
            System.out.println("MENÙ PRINCIPALE:");
            System.out.println("0. Esci");
            System.out.println("1. Gestione Inventario");
            System.out.println("2. Nuova Vendita");
            System.out.println("3. Visualizza vendite agricole per data");
            System.out.println("4. Visualizza tutto registro vendite agricole");
            System.out.println("\n");

            int scelta = getInteroPositivoRange(scanner, 0, 4);

            switch (scelta) {
                case 0:
                    continua = false;
                    scanner.close();
                    System.exit(0);
                    break;
                case 1:
                    gestioneInventario(scanner, inventario);
                    break;
                case 2:
                    gestioneNuoveVendite(scanner, vendita, inventario, registroVendite);
                    break;
                case 3:
                    System.out.println("Inserisci la data nel formato yyyy-MM-dd: ");
                    String data = scanner.nextLine();
                    try {
                        System.out.println(registroVendite.stampaVenditePerData(data));
                    } catch (Exception e) {
                        System.out.println("Errore nella visualizzazione delle vendite per data");
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    try {
                        System.out.println(registroVendite.visualizzaRegistro());
                    } catch (IOException e) {
                        System.out.println("Errore nella visualizzazione del registro");
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Errore");
                    break;
            }
        }

    }

    // per inserire le quantità nell'inventario o nel carrello
    private static int getInteroPositivo(Scanner scanner, String messaggio) {
        int inserito = 0;
        while (true) {
            try {
                System.out.println(messaggio);
                inserito = Integer.parseInt(scanner.nextLine());

                if (inserito < 0) {
                    System.out.println("Errore: inserisci un numero intero positivo");
                } else {
                    return inserito;
                }
            } catch (NumberFormatException e) {
                System.out.println("Errore");
            }
        }
    }

    // per le scelte nel menù
    private static int getInteroPositivoRange(Scanner scanner, int min, int max) {
        if (min > max || min < 0 || max < 0) {
            return -1;
        }

        int inserito = 0;
        while (true) {
            System.out.print("Inserisci un numero intero compreso tra: " + min + " e " + max + " (inclusi): ");
            inserito = getInteroPositivo(scanner, "");

            if (inserito < min || inserito > max) {
                System.out.println("Errore: rispetta il range");
            } else {
                return inserito;
            }

        }
    }

    private static void gestioneInventario(Scanner scanner, Inventario inventario) {
        System.out.println("Sei nella GESTIONE DELL'INVENTARIO");
        boolean continua = true;
        Prodotto prodotto;
        int quantita = 0;

        while (continua) {
            System.out.println("\n");
            System.out.println("MENÙ INVENTARIO:");
            System.out.println("0. Esci");
            System.out.println("1. Aggiungi prodotto");
            System.out.println("2. Rimuovi prodotto");
            System.out.println("3. Azzera prodotto");
            System.out.println("4. Aggiungi quantità a tutto l'inventario");
            System.out.println("5. Visualizza inventario");
            System.out.println("\n");

            int scelta = getInteroPositivoRange(scanner, 0, 5);

            switch (scelta) {
                case 0:
                    continua = false;
                    break;
                case 1:
                    prodotto = getProdottoDaInput(scanner);
                    quantita = getInteroPositivo(scanner,
                            "Inserisci la quantità del prodotto da aggiungere all'inventario: ");
                    inventario.aggiungiProdotto(prodotto, quantita);
                    break;
                case 2:
                    prodotto = getProdottoDaInput(scanner);
                    quantita = getInteroPositivo(scanner,
                            "Inserisci la quantità del prodotto da rimuovere dall'inventario: ");
                    inventario.rimuoviProdotto(prodotto, quantita);
                    break;
                case 3:
                    System.out.println("Inserisci il prodotto da azzerare: ");
                    prodotto = getProdottoDaInput(scanner);
                    inventario.azzeraProdotto(prodotto);
                    break;
                case 4:
                    quantita = getInteroPositivo(scanner,
                            "Inserisci la quantità da aggiungere a tutto l'inventario: ");
                    aggiungiQuantitaTuttoInventario(inventario, quantita);
                    break;
                case 5:
                    System.out.println(inventario.elencoProdotti());
                    break;
                default:
                    System.out.println("Errore");
                    break;
            }
        }
    }

    private static void stampaLegendaProdotti() {
        System.out.println("\nLEGENDA PRODOTTI:");
        System.out.println("1.  Pianta Lavanda");
        System.out.println("2.  Pianta Salvia");
        System.out.println("3.  Pianta Rosmarino");
        System.out.println("4.  Mazzo Lavanda");
        System.out.println("5.  Sacchettino Lavanda");
        System.out.println("6.  Gufetto Lavanda");
        System.out.println("7.  Cuore Lavanda");
        System.out.println("8.  Sotto Cuscino Lavanda");
        System.out.println("9.  Crema Mani Lavanda");
        System.out.println("10. Bagnodoccia Lavanda");
        System.out.println("11. Saponetta Lavanda");
        System.out.println("12. Olio Essenziale Lavanda Angustifolia");
        System.out.println("13. Olio Essenziale Lavanda Ibrida");
        System.out.println("\n");
    }

    private static Prodotto getProdottoDaCodice(int codice) {
        switch (codice) {
            case 1:
                return Prodotto.creaPiantaLavanda();
            case 2:
                return Prodotto.creaPiantaSalvia();
            case 3:
                return Prodotto.creaPiantaRosmarino();
            case 4:
                return Prodotto.creaMazzo();
            case 5:
                return Prodotto.creaSacchettino();
            case 6:
                return Prodotto.creaGufetto();
            case 7:
                return Prodotto.creaCuore();
            case 8:
                return Prodotto.creaSottoCuscino();
            case 9:
                return Prodotto.creaCremaMani();
            case 10:
                return Prodotto.creaBagnodoccia();
            case 11:
                return Prodotto.creaSaponetta();
            case 12:
                return Prodotto.creaOlioAngustifolia();
            case 13:
                return Prodotto.creaOlioIbrida();
            default:
                return null;
        }
    }

    private static Prodotto getProdottoDaInput(Scanner scanner) {
        stampaLegendaProdotti();
        int codice = getInteroPositivoRange(scanner, 1, 13);
        return getProdottoDaCodice(codice);
    }

    private static void aggiungiQuantitaTuttoInventario(Inventario inventario, int quantita) {
        inventario.aggiungiProdotto(Prodotto.creaPiantaLavanda(), quantita);
        inventario.aggiungiProdotto(Prodotto.creaPiantaSalvia(), quantita);
        inventario.aggiungiProdotto(Prodotto.creaPiantaRosmarino(), quantita);
        inventario.aggiungiProdotto(Prodotto.creaMazzo(), quantita);
        inventario.aggiungiProdotto(Prodotto.creaSacchettino(), quantita);
        inventario.aggiungiProdotto(Prodotto.creaGufetto(), quantita);
        inventario.aggiungiProdotto(Prodotto.creaCuore(), quantita);
        inventario.aggiungiProdotto(Prodotto.creaSottoCuscino(), quantita);
        inventario.aggiungiProdotto(Prodotto.creaCremaMani(), quantita);
        inventario.aggiungiProdotto(Prodotto.creaBagnodoccia(), quantita);
        inventario.aggiungiProdotto(Prodotto.creaSaponetta(), quantita);
        inventario.aggiungiProdotto(Prodotto.creaOlioAngustifolia(), quantita);
        inventario.aggiungiProdotto(Prodotto.creaOlioIbrida(), quantita);
    }

    private static void gestioneNuoveVendite(Scanner scanner, Vendita vendita, Inventario inventario,
            RegistroVendite registroVendite) {
        System.out.println("\nSei nella GESTIONE DELLE NUOVE VENDITE:");
        boolean continua = true;
        Prodotto prodotto;
        int quantita = 0;

        while (continua) {
            System.out.println("\nMENÙ NUOVA VENDITA:");
            System.out.println("0. Esci");
            System.out.println("1. Aggiungi prodotto al carrello");
            System.out.println("2. Rimuovi prodotto dal carrello");
            System.out.println("3. Azzera prodotto dal carrello");
            System.out.println("4. Svuota carrello");
            System.out.println("5. Visualizza carrello attuale");
            System.out.println("6. Visualizza prodotti disponibili (inventario)");
            System.out.println("7. Visualizza totali e riepilogo");
            System.out.println("8. Finalizza vendita");
            System.out.println("\n");

            int scelta = getInteroPositivoRange(scanner, 0, 8);

            switch (scelta) {
                case 0:
                    continua = false;
                    vendita.svuotaCarrello();
                    break;
                case 1:
                    prodotto = getProdottoDaInput(scanner);
                    quantita = getInteroPositivo(scanner,
                            "Inserisci la quantità del prodotto da aggiungere al carrello: ");
                    vendita.aggiungiProdottoCarrello(prodotto, quantita);
                    break;
                case 2:
                    prodotto = getProdottoDaInput(scanner);
                    quantita = getInteroPositivo(scanner,
                            "Inserisci la quantità del prodotto da rimuovere dal carrello: ");
                    vendita.rimuoviProdotto(prodotto, quantita);
                    break;
                case 3:
                    System.out.println("Inserisci il prodotto da rimuovere completamente dal carrello (azzerare): ");
                    prodotto = getProdottoDaInput(scanner);
                    vendita.azzeraProdotto(prodotto);
                    break;
                case 4:
                    vendita.svuotaCarrello();
                    System.out.println("Carrello svuotato\n");
                    break;
                case 5:
                    System.out.println(vendita.visualizzaCarrello());
                    break;
                case 6:
                    System.out.println(inventario.elencoProdotti());
                    break;
                case 7:
                    System.out.println("RIEPILOGO VENDITA:");
                    System.out.println(vendita.riepilogoVendita());
                    break;
                case 8:
                    vendita.finalizzaVendita();
                    registroVendite.salvaVendite();
                    System.out.println("Vendita registrata con successo!\n");
                    break;
                default:
                    System.out.println("Errore");
                    break;
            }
        }
    }
}