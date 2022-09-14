import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;

public class Legesystem {
    private static IndeksertListe<Pasient> listeOverPasienter = new IndeksertListe<Pasient>();
    private static Prioritetskoe<Lege> listeOverLege = new Prioritetskoe<Lege>();
    private static IndeksertListe<Legemiddel> listeOverLegemiddel = new IndeksertListe<Legemiddel>();
    private static IndeksertListe<Resept> reseptListe = new IndeksertListe<Resept>();

    public static void main(String[] args) throws FileNotFoundException, UlovligUtskrift {
        try {
            lesFraFil("legedata.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke filen, sjekk om filen er lagt inn. Fortsetter til Hovedmeny -->");
        }
        try {
            hovedMeny();
        } catch (InputMismatchException x) {
            System.out.println("Du har tastet et tall som det ikke er et valg for");
        }
    }

    // Oppgave E1 Filinnlesing!
    public static void lesFraFil(String filnavn) throws FileNotFoundException, UlovligUtskrift {
        File fil = new File(filnavn);
        try (Scanner lesfil = new Scanner(fil)) {
            String data = "";
            while (lesfil.hasNextLine()) {
                String les_linje = lesfil.nextLine();
                String[] biter = les_linje.trim().split(" ");
                if (biter[0].equals("#")) {
                    if (biter[1].equals("Leger")) {
                        data = "Leger";
                    } else if (biter[1].equals("Legemidler")) {
                        data = "Legemidler";
                    } else if (biter[1].equals("Pasienter")) {
                        data = "Pasienter";
                    } else {
                        data = "Resepter";
                    }
                } else {
                    String[] linje = les_linje.trim().split(",");
                    if (data.equals("Pasienter")) {
                        listeOverPasienter.leggTil(new Pasient(linje[0], linje[1]));
                    }

                    else if (data.equals("Legemidler")) {
                        if (linje[1].equals("narkotisk")) {
                            listeOverLegemiddel.leggTil(new Narkotisk(linje[0], Integer.parseInt(linje[2]),
                                    Double.parseDouble(linje[3]), Integer.parseInt(linje[4])));
                        } else if (linje[1].equals("vanedannende")) {
                            listeOverLegemiddel.leggTil(new Vanedannende(linje[0], Integer.parseInt(linje[2]),
                                    Double.parseDouble(linje[3]), Integer.parseInt(linje[4])));
                        } else {
                            listeOverLegemiddel.leggTil(
                                    new Vanlig(linje[0], Integer.parseInt(linje[2]), Double.parseDouble(linje[3])));
                        }
                    }

                    else if (data.equals("Leger")) {
                        if (linje[1].equals("0")) {
                            listeOverLege.leggTil(new Lege(linje[0]));
                        } else {
                            listeOverLege.leggTil(new Spesialist(linje[0], (linje[1])));
                        }
                    }

                    else {
                        Lege lege = null;
                        Pasient pasient = null;
                        Legemiddel legemiddel = null;
                        for (Lege l : listeOverLege) {
                            if (l.hentNavn().equals(linje[1])) {
                                lege = l;
                            }
                        }
                        for (Pasient p : listeOverPasienter) {
                            if (p.hentid() == Integer.parseInt(linje[2])) {
                                pasient = p;
                            }
                        }
                        for (Legemiddel lm : listeOverLegemiddel) {
                            if (lm.hentId() == Integer.parseInt(linje[0])) {
                                legemiddel = lm;
                            }
                        }

                        if (linje[3].equals("hvit")) {
                            HvitResept hvitRes = lege.skrivHvitResept(legemiddel, pasient, Integer.parseInt(linje[4]));
                            reseptListe.leggTil(hvitRes);
                        } else if (linje[3].equals("blaa")) {
                            BlåResept blåRes = lege.skrivBlaaResept(legemiddel, pasient, Integer.parseInt(linje[4]));
                            reseptListe.leggTil(blåRes);
                        } else if (linje[3].equals("p")) {
                            PResept pResept = lege.skrivPResept(legemiddel, pasient, Integer.parseInt(linje[4]));
                            reseptListe.leggTil(pResept);
                        } else {
                            Millitæresept milRes = lege.skrivMilResept(legemiddel, pasient);
                            reseptListe.leggTil(milRes);
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Fant ikke filen " + filnavn);
        }
    }

    // Oppgave E2 Kommandoløkke for bruker
    public static void hovedMeny() throws InputMismatchException, UlovligUtskrift {
        Scanner lesFraBruk = new Scanner(System.in);
        System.out.println(" ________________");
        System.out.println("|                |");
        System.out.println("| Pasient: " + listeOverPasienter.stoerrelse() + "     |");
        System.out.println("| Lege: " + listeOverLege.stoerrelse() + "        |");
        System.out.println("| Legemiddel: " + listeOverLegemiddel.stoerrelse() + "  |");
        System.out.println("| Resepter: " + reseptListe.stoerrelse() + "    |");
        System.out.println("|________________|");
        System.out.println("\n");
        System.out.println(" ______________________________________________________________________________");
        System.out.println("| 1 - Oversikt over alle pasienter, Leger, Legemidler og resepter i systemet   |");
        System.out.println("| 2 - Opprette og legge til nye elementer i systemet" + "                           |");
        System.out.println("| 3 - Bruke en gitt resept fra listen til en pasient i systemet" + "                |");
        System.out.println("| 4 - Vise statistikk om elementene i systemet" + "                                 |");
        System.out.println("| 5 - Skrive til fil" + "                                                           |");
        System.out.println("| 0 - Avslutter" + "                                                                |");
        System.out.println("|______________________________________________________________________________|");
        System.out.println("Velg input: ");
        int input = lesFraBruk.nextInt();

        while (input != 0) {
            if (input > 5 || input < 0) {
                System.out.println("Du har tastet et tall som det ikke er et valg for");
                System.out.println("Velg en gyldig input: ");
                input = lesFraBruk.nextInt();
            }
            if (input == 1) {
                System.out.println("1 - Skriv ut Pasienter");
                System.out.println("2 - Skriv ut Lege");
                System.out.println("3 - Skriv ut legemiddel");
                System.out.println("4 - Skriv ut resepter");
                System.out.println("0 - Tilbake til Hovedmeny");
                int undermenyInput = lesFraBruk.nextInt();
                while (undermenyInput > 5 && undermenyInput <= 0) {
                    System.out.println("Du har tastet et et tall som det ikke finnes valg for. Prøv på nytt!");
                    undermenyInput = lesFraBruk.nextInt();
                }
                skrivUtOversikt(undermenyInput);
            } else if (input == 2) {
                try {
                    leggTilFraBruker(); // Del E4
                    System.out.println("Oppgi nytt valg: ");
                    input = lesFraBruk.nextInt();
                } catch (NumberFormatException x) {
                    System.out.println("Tastet feil");
                }
            } else if (input == 3) {
                try {
                    brukResept(); // Del E5
                } catch (NumberFormatException x) {
                    System.out.println("Tastet feil");
                }
            } else if (input == 4) {
                underMeny(); // Del E6
                System.out.println("Oppgi nytt valg: ");
                input = lesFraBruk.nextInt();
            } else if (input == 5) {
                try {
                    skrivDataTilFil();
                    System.out.println("Oppgi nytt valg: ");
                    input = lesFraBruk.nextInt();
                } catch (NumberFormatException x) {
                    System.out.println("Tastet feil");
                }
            } else if (input == 0) {
                System.out.println("Programmet avsluttes");
                System.exit(0);
            }
        }
        lesFraBruk.close();
    }
    String s = skrivUtOversikt(1);

    // Oppgave E3 Oversikt
    public static void skrivUtOversikt(int inputFraBruker) throws InputMismatchException, UlovligUtskrift {
        if (inputFraBruker == 1) {
            if (listeOverLege.stoerrelse() == 0) {
                System.out.println("Det er ingen pasienter i lista!");
            } else {
                System.out.println("-----------------PASIENTER------------------");
                for (Pasient pasient : listeOverPasienter) {
                    System.out.println(pasient.toString());
                }
            }
        } else if (inputFraBruker == 2) {
            if (listeOverLege.stoerrelse() == 0) {
                System.out.println("Det er ingen Leger i lista!");
            } else {
                System.out.println("-----------------LEGER------------------");
                for (Lege lege : listeOverLege) {
                    System.out.println(lege.toString());
                }
            }
        } else if (inputFraBruker == 3) {
            if (listeOverLege.stoerrelse() == 0) {
                System.out.println("Det er ingen Legemidler i lista!");
            } else {
                System.out.println("-----------------LEGEMIDLER------------------");
                for (Legemiddel lm : listeOverLegemiddel) {
                    System.out.println(lm.toString());
                }
            }
        } else if (inputFraBruker == 4) {
            if (listeOverLege.stoerrelse() == 0) {
                System.out.println("Det er ingen Resepter i lista!");
            } else {
                System.out.println("-----------------RESEPTER------------------");
                for (Resept r : reseptListe) {
                    System.out.println(r + "\n");
                }
            }
        } else if (inputFraBruker == 0) {
            hovedMeny();
        }
    }

    // Oppgave E4 La bruker legge til ...
    public static void leggTilFraBruker() throws UlovligUtskrift, NumberFormatException {
        System.out.println("1 - Oprette Pasient");
        System.out.println("2 - Oprette Lege");
        System.out.println("3 - Oprette legemiddel");
        System.out.println("4 - Oprette resept");
        System.out.println("0 - Gå tilbake til Hovedmeny");

        int inpTall;
        String inpString;
        Scanner lesTall = new Scanner(System.in);
        Scanner lesString = new Scanner(System.in);
        inpTall = lesTall.nextInt();

        // Oprette Pasient -->
        if (inpTall == 1) {
            System.out.println("Hva er navnet til pasienten: ");
            inpString = lesString.nextLine();
            System.out.println("Hva er fødselsnr. til pasienten; ");
            String fødsnrStr = lesString.nextLine();
            Pasient nyPas = new Pasient(inpString, fødsnrStr);
            listeOverPasienter.leggTil(nyPas);
        } else if (inpTall == 2) {// Lege
            System.out.println("Hva er navnet til Legen: ");
            inpString = lesString.nextLine();
            System.out.println("Er det en 1 - Spesialist eller 2 - Lege: ");
            int svar = lesTall.nextInt();
            if (svar == 1){
                System.out.println("Hva er kontroll IDen til Spesialist: ");
                String inpKontrollID = lesString.nextLine();
                Spesialist nySpes = new Spesialist(inpString, inpKontrollID);
                listeOverLege.leggTil(nySpes);
            }
            // System.out.println("Hva er kontroll IDen til Spesialist (0 om det er lege): ");
            // String inpKontrollID = lesString.nextLine();
            // if (inpKontrollID != "0") { //Legger til Spesialist 
            //     Spesialist nySpes = new Spesialist(inpString, inpKontrollID);
            //     listeOverLege.leggTil(nySpes);
            else {
                Lege nyLege = new Lege(inpString);
                listeOverLege.leggTil(nyLege);
            }
        
        } else if (inpTall == 3) { // Legemiddel
            System.out.println("Navnet til legemidellet: ");
            String inpNavn = lesString.nextLine();
            System.out.println("Hva er prisen til legemidellet: ");
            int inpPris = lesTall.nextInt();
            System.out.println("Hva er virkestoffet i mg: ");
            double inpVirkestoff = lesTall.nextDouble();

            System.out.println("1 - Vanlig, 2 - Narkotisk, 3 - Vanedannende");
            System.out.println("Hva slags type legemiddel er det, velg mellom de som er nevnt over: ");
            int inpTypeLM = lesTall.nextInt();

            if (inpTypeLM == 1) {
                Vanlig nyVanlig = new Vanlig(inpNavn, inpPris, inpVirkestoff);
                listeOverLegemiddel.leggTil(nyVanlig);
                return;
            } else if (inpTypeLM == 2) {
                System.out.println("Hva er styrken på narkotiske legemiddelet: ");
                int inpStyrke = lesTall.nextInt();
                Narkotisk nyNark = new Narkotisk(inpNavn, inpPris, inpVirkestoff, inpStyrke);
                listeOverLegemiddel.leggTil(nyNark);
                return;
            } else if (inpTypeLM == 3) {
                System.out.println("Hva er styrken på det vanedannende legemiddelet: ");
                int inpStyrke = lesTall.nextInt();
                Vanedannende nyVanedannende = new Vanedannende(inpNavn, inpPris, inpVirkestoff, inpStyrke);
                listeOverLegemiddel.leggTil(nyVanedannende);
                return;
            }
            System.out.println("Du har tastet feil tall, ikke noe valg for " + inpTypeLM);
        } else if (inpTall == 4) {// Resept
            skrivUtOversikt(1);
            System.out.println("Hva er IDen til pasient: ");
            int inpPasID = lesTall.nextInt();
            System.out.println("Hva er reit til pasient: ");
            int inpReit = lesTall.nextInt();

            skrivUtOversikt(2);
            System.out.println("Hva er navnet til legen: ");
            String inpLegeNavn = lesString.nextLine();

            skrivUtOversikt(3);
            System.out.println("Hva er legemiddel IDen: ");
            int inpLmID = lesTall.nextInt();

            // Finner de overnevnte i lista.
            Pasient pasient = null;
            for (Pasient nyPas : listeOverPasienter) {
                if (nyPas.hentid() == inpPasID) {
                    pasient = nyPas;
                }
            }

            Lege lege = null;
            for (Lege nyLege : listeOverLege) {
                if (nyLege.hentNavn().equals(inpLegeNavn)) {
                    lege = nyLege;
                }
            }

            Legemiddel legemiddel = null;
            for (Legemiddel nyLm : listeOverLegemiddel) {
                if (nyLm.hentId() == inpLmID) {
                    legemiddel = nyLm;
                }
            }

            System.out.println("1 - P-Resept, 2 - BlåResept, 3 - MillitærResept, 4 -  HvitResepet");
            System.out.println("Hvilken type resept er det nye reseptet, velg mellom de over: ");
            int inpResept = lesTall.nextInt();

            if (inpResept == 1 && pasient != null && lege != null && legemiddel != null) {
                PResept pResept = lege.skrivPResept(legemiddel, pasient, inpReit);
                reseptListe.leggTil(pResept);
                return;
            } else if (inpResept == 2 && pasient != null && lege != null && legemiddel != null) {
                BlåResept blåRes = lege.skrivBlaaResept(legemiddel, pasient, inpReit);
                reseptListe.leggTil(blåRes);
                return;
            } else if (inpResept == 3 && pasient != null && lege != null && legemiddel != null) {
                Millitæresept milRes = lege.skrivMilResept(legemiddel, pasient);
                reseptListe.leggTil(milRes);
                return;
            } else if (inpResept == 4 && pasient != null && lege != null && legemiddel != null) {
                HvitResept hvitRes = lege.skrivHvitResept(legemiddel, pasient, inpReit);
                reseptListe.leggTil(hvitRes);
                return;
            }
            System.out.println("Du har tastet feil, du kan velge mellom 1-4");
        } else if (inpTall == 0) {
            hovedMeny();
        } else {
            System.out.println("Du har tastet feil, du kan velge mellom 1-4");
        }
    }

    // Oppgave E5 Bruke resept til pasient
    public static void brukResept() throws NullPointerException, InputMismatchException, UlovligUtskrift {
        boolean resBrukt = false;
        int inpTall;
        Scanner scTall = new Scanner(System.in);
        skrivUtOversikt(1);
        System.out.println("\nHvilken pasient vil du se resepter for? ");
        System.out.println("0 - For å komme tilbake til hovedmeny.");
        // System.out.println(listeOverPasienter);

        inpTall = scTall.nextInt();

        if (inpTall == 0) {
            hovedMeny();
        }
        Pasient valgtPas = null;
        for (Pasient pas : listeOverPasienter) {
            if (pas.hentid() == inpTall) {
                valgtPas = pas;
                System.out.println(
                        "Du har valgt pasient: " + valgtPas.hentnavn() + " ( " + valgtPas.hentfodselsnummer() + " )");

                System.out.println(pas.hentresepter());

                for (Resept res : valgtPas.hentresepter()) {
                    System.out.println("Reseptens ID: " + res.hentId() + ":" + res.hentLegemiddel().hentNavn()
                            + "(Har igjen på resept: "
                            + res.hentReit() + " )");
                }
            }
        }

        System.out.println("\nHvilken resept vil du bruke?");

        Resept valgtRes = null;
        inpTall = scTall.nextInt();
        for (Resept res : valgtPas.hentresepter()) {

            if (res.hentId() == inpTall) {

                valgtRes = res;
            }
        }
        resBrukt = valgtRes.bruk(); // valgtRes er null ??
        if (resBrukt != false) {
            System.out.println("Resept brukt på legemiddelet: " + valgtRes.hentLegemiddel().hentNavn()
                    + ". Gjenværende ganger på resepten: " + valgtRes.hentReit());
        } else {
            System.out.println("Resepten kunne brukes. (Tomt for reit)");
        }
        scTall.close();
    }

    // Oppg. E6 Statistisk informasjon (undermeny).
    public static void underMeny() throws InputMismatchException, UlovligUtskrift {
        IndeksertListe<Lege> narkLege = new IndeksertListe<Lege>();
        IndeksertListe<Pasient> narkpas = new IndeksertListe<Pasient>();

        int antNarkotiskRes = 0;
        int antVanedanRes = 0;
        for (Resept res : reseptListe) {
            if (res.hentLegemiddel() instanceof Vanedannende) {
                antVanedanRes += 1;
            } else if (res.hentLegemiddel() instanceof Narkotisk) {
                antNarkotiskRes += 1;
            }
        }

        for (Pasient pas : listeOverPasienter) {
            Stabel<Resept> l = pas.hentresepter();
            boolean sett = false;
            for (Resept res : l) {
                if (res.hentLegemiddel() instanceof Narkotisk && res.hentReit() > 0 && sett != true) {
                    narkpas.leggTil(pas);
                    sett = true;
                }
            }
        }

        for (Lege lege : listeOverLege) {
            Lenkeliste<Resept> l = lege.hentResepter();
            boolean sett = false;
            for (Resept res : l) {
                if (res.hentLegemiddel() instanceof Narkotisk && res.hentReit() > 0 && sett != true) {
                    narkLege.leggTil(lege);
                    sett = true;
                }
            }
        }
        System.out.println("Totalt antall utskrevne resepter på vanedannende legemidler: " + antVanedanRes +
                "\nTotalt antall utskrevne resepter på narkotiske legemidler: " + antNarkotiskRes + "\n");

        System.out.println("Navnene til pasientene som har gyldig resept på narkotiske legemidler: ");
        for (Pasient pas : narkpas) {
            System.out.println(pas.hentnavn() + "\n");
        }

        System.out.println("Navnene til alle leger som har skrevet ut resept på narkotisk legemiddel: ");
        for (Lege lege : narkLege) {
            System.out.println(lege.hentNavn() + "\n");
        }

    }

    // Oppg. E7 Skrive info fra systemet til fil.
    public static void skrivDataTilFil() throws InputMismatchException, UlovligUtskrift {
        PrintWriter pW = null;
        // Scanner sc = new Scanner(System.in);
        // System.out.println("Hva skal den nye filen hete?");
        // String inp = sc.nextLine();

        try {
            pW = new PrintWriter("NyLegesystem.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Kan ikke skrive ny fil");
            System.exit(1);
        }

        // Skriver inn data om pasienter
        pW.println("# Pasienter (navn, fnr)");
        for (Pasient p : listeOverPasienter) {
            pW.println(p.hentnavn() + ", " + p.hentfodselsnummer());
        }
        // Skriver inn data om legemidler
        pW.println("# Legemidler (navn,type,pris,virkestoff,[styrke])");
        for (Legemiddel l : listeOverLegemiddel) {
            pW.println(l.hentNavn() + ", " + l.getClass() + ", " + l.hentPris() + ", " + l.hentVirkestoff() + ", " + l.hentStyrke());
        }

        // Skriver inn data om leger
        pW.println("# Leger (navn,kontrollid / 0 hvis vanlig lege)");
        for (Lege l : listeOverLege) {
            pW.println(l.hentNavn() + ", " + l.hentKontrollID() );
        }

        // Skriver inn data om resepter
        pW.println("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])");
        for (Resept r : reseptListe) {
            pW.println(r.hentLegemiddel().hentId() + ", " + r.hentLege().hentNavn() + ", " + r.hentPasient().hentid()
                    + ", " + r.getClass() + ", " + r.hentReit());
        }
        pW.close();
        System.out.println("Nytt legesystem er skrevet med data fra systemet, til filen NyLegesystem.txt\n");
        System.out.println("Tilbake i hovedmeny\n");
        // sc.close();
    }
}
