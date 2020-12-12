package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = scanner.nextLine();
            int moodi = -1;
            
            if (vastaus.endsWith("a")) {
                moodi = 1;
            } else if (vastaus.endsWith("b")) {
                moodi = 2;
            } else if (vastaus.endsWith("c")) {
                moodi = 3;
            } else {
                break;
            }

            System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
            KVSPelitehdas.luoPeli(moodi).pelaa();
        }
    }
}
