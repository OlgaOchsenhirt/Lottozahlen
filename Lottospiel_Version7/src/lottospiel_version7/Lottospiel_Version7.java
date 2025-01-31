package lottospiel_version7;

import java.util.Scanner;
import java.util.Random;

public class Lottospiel_Version7 {

    public static void main(String[] args) {

        // Deklaration und Initialisierung der Variablen
        int zeilen = 0;
        int spalten = 0;
        int[] zufallszahlen = new int[6];
        int woche = 0;
        int monat = 0;
        int jahr = 0;
        boolean gewonnen = false;
        int anzahlRichtige = 0;
        int reihe = 1;
        int gewinn = 0;
        int verlust = 0;
        int dreiRichtige = 0;
        int vierRichtige = 0;
        int fuenfRichtige = 0;

// Erstellung eines neuen Scanner Objektes 
        Scanner eingabe = new Scanner(System.in);

// Erstellung eines neuen Random Objektes
        Random zufall = new Random();

        // Frage nach der Anzahl der Reihen
        System.out.print("Wieviele Reihen wollen Sie spielen? \n");
        zeilen = eingabe.nextInt();
        while (zeilen < 1 || zeilen > 10) {
            System.out.print("Die maximale Anzahl der Reihen beträgt 10. \n");
            System.out.print("Bitte geben Sie die Anzahl der Reihen an: \n");
            zeilen = eingabe.nextInt();
        }

        // Frage nach der Anzahl der Zahlen
        System.out.print("Wieviele Zahlen wollen Sie eingeben? \n");
        spalten = eingabe.nextInt();
        while (spalten < 6 || spalten > 12) {
            System.out.print("Die maximale Anzahl der Zahlen beträgt 12. \n");
            System.out.print("Bitte geben Sie die Anzahl der Zahlen an: \n");
            spalten = eingabe.nextInt();
        }
        int[][] meineZahlen = new int[zeilen][spalten];

// Aufforderung zur Zahleneingabe
        for (int i = 0; i < meineZahlen.length; i++) {
            System.out.print("Die " + reihe + ". Reihe: ");
            reihe++;

            for (int j = 0; j < meineZahlen[i].length; j++) {
                System.out.print("Bitte geben Sie eine Zahl zwischen 1 und 49 ein: \n");
                meineZahlen[i][j] = eingabe.nextInt();

                // Überprüfen, dass die richtigen Zahlen eingegeben wurden
                while (meineZahlen[i][j] < 1 || meineZahlen[i][j] > 49) {
                    System.out.print("Falsche Zahleneingabe. \n");
                    System.out.print("Bitte geben Sie eine Zahl zwischen 1 und 49 ein: \n");
                    meineZahlen[i][j] = eingabe.nextInt();
                }
                // Überprüfung, dass jede Zahl nur einmal eingegeben wurde 
                for (int k = 0; k < j; k++) {
                    while (meineZahlen[i][j] == meineZahlen[i][k]) {
                        System.out.print("Die Zahl haben Sie schon eingegeben. Bitte geben Sie eine andere Zahl ein: \n");
                        meineZahlen[i][j] = eingabe.nextInt();
                    }
                }
            }
        }

        // Ausgabe der eingegebenen Zahlen
        System.out.print("Meine Zahlen sind: ");

        for (int i = 0; i < meineZahlen.length; i++) {

            System.out.println();

            for (int j = 0; j < meineZahlen[i].length; j++) {

                System.out.print(meineZahlen[i][j] + " ");
            }
        }

        System.out.println();

        // Ausgabe der Zufallszahlen
        System.out.print("Die Zufallszahlen sind: ");

        // Solange keine 6 Richtigen dabei sind, werden neue Zufallszahlen generiert
        while (gewonnen == false) {
            anzahlRichtige = 0;
            // Generierung von Zufallszahlen
            for (int i = 0; i < zufallszahlen.length; i++) {
                zufallszahlen[i] = zufall.nextInt(1, 50);

                //Überprüfung, dass jede Zahl nur einmal eingegeben wurde
                for (int j = 0; j < i; j++) {
                    if (zufallszahlen[i] == zufallszahlen[j]) {
                        zufallszahlen[i] = zufall.nextInt(1, 50);
                    }

                }

            }

            System.out.println();

            // Die Zufallszahlen werden mit eingegebenen Zahlen verglichen
            for (int i = 0; i < meineZahlen.length; i++) {

                // Berechnung der Kosten für Lottoscheine
                switch (spalten) {

                    case 6: {
                        verlust += 2;
                        break;
                    }

                    case 7: {
                        verlust += 10;
                        break;
                    }

                    case 8: {
                        verlust += 35;
                        break;
                    }

                    case 9: {
                        verlust += 100;
                        break;
                    }

                    case 10: {
                        verlust += 300;
                    }

                    case 11: {
                        verlust += 600;
                        break;
                    }

                    case 12: {
                        verlust += 1200;
                        break;
                    }
                }

                // Ueberprüfung der Lottoscheine
                switch (anzahlRichtige) {
                    case 6: {
                        gewonnen = true;
                        gewinn += 20000000;
                        break;
                    }
                    case 5: {
                        fuenfRichtige++;
                        gewinn += 5000;
                        break;
                    }
                    case 4: {
                        vierRichtige++;
                        gewinn += 100;
                        break;
                    }
                    case 3: {
                        dreiRichtige++;
                        gewinn += 10;
                        break;
                    }
                }

                // Nach jeder Reihe wird die Anzahl der Richtigen auf 0 zurückgesetzt 
                anzahlRichtige = 0;

                for (int k = 0; k < zufallszahlen.length; k++) {
                    boolean gefunden = false;
                    for (int j = 0; j < meineZahlen[i].length; j++) {
                        if (meineZahlen[i][j] == zufallszahlen[k]) {
                            anzahlRichtige++;
                            gefunden = true;
                            // Ausgabe der richtigen Zahlen in Grün
                            System.out.print("\033[0;32m" + zufallszahlen[k] + " ");

                        }
                    }

                    if (gefunden == false) {
                        // Ausgabe der falschen Zahlen in Rot
                        System.out.print("\033[0;31m" + zufallszahlen[k] + " ");
                    }

// Überprüfung des letzten Lottoscheins
                    switch (anzahlRichtige) {
                        case 6: {
                            gewonnen = true;
                            gewinn += 20000000;
                            break;
                        }
                        case 5: {
                            fuenfRichtige++;
                            gewinn += 5000;
                            break;
                        }
                        case 4: {
                            vierRichtige++;
                            gewinn += 100;
                            break;
                        }
                        case 3: {
                            dreiRichtige++;
                            gewinn += 10;
                            break;
                        }
                        // Sonst wird die Anzahl der Wochen um 1 erhöht 
                        default: {
                            woche++;

                        }
                    }
                    // Bei 4 Wochen wird die Anzahl der Monate erhöht
                    if (woche == 4) {
                        monat++;
                        woche = 0;
                    }
                    // Bei 12 Monaten wird die Anzahl der Jahre erhöht
                    if (monat == 12) {
                        jahr++;
                        monat = 0;
                    }

                }
            }
        }

// Ausgabe des voraussichtlichen Zeitpunkts des Gewinns
        System.out.println();

        System.out.print(
                "Der Spieler gewinnt nach " + jahr + " Jahren, " + monat + " Monaten und " + woche + " Wochen.");

        System.out.println();

        System.out.print(
                "Es kommen " + dreiRichtige + " Mal drei Richtige, " + vierRichtige + " Mal vier Richtige und " + fuenfRichtige + " Mal fünf Richtige vor.");

        System.out.println();

        System.out.print(
                "Der Gewinn beträgt " + gewinn + " Euro. Der Spieler hat " + verlust + " Euro investiert.");

    }

}
