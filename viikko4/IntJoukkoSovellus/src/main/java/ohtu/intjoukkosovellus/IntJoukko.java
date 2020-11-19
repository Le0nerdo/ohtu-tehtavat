
package ohtu.intjoukkosovellus;

import java.util.Arrays;
import java.util.stream.IntStream;

public class IntJoukko {

    public final static int OLETUSALKUKAPASITEETTI = 5,
                            OLETUSKASVATUSKOKO = 5; 
    private int kasvatuskoko;
    private int[] luvut;
    private int alkioidenMaara;

    public IntJoukko() {
        this(IntJoukko.OLETUSALKUKAPASITEETTI, IntJoukko.OLETUSKASVATUSKOKO);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, IntJoukko.OLETUSKASVATUSKOKO);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti ei saa olla negatiivinen.");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoko ei saa olla negatiivinen.");
        }
        luvut = new int[kapasiteetti];
        for (int i = 0; i < luvut.length; i++) {
            luvut[i] = 0;
        }
        alkioidenMaara = 0;
        this.kasvatuskoko = kasvatuskoko;

    }

    private void kasvataTaulukkoa() {
        int[] vanhaTaulukko = luvut;
        luvut = new int[alkioidenMaara + kasvatuskoko];
        kopioiTaulukko(vanhaTaulukko, luvut);
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) return false;

        luvut[alkioidenMaara] = luku;
        alkioidenMaara++;
        if (alkioidenMaara == luvut.length) this.kasvataTaulukkoa();
        return true;
    }

    public boolean kuuluu(int luku) {
        int luvunIndeksi = haeLuvunIndeksi(luku);
        if (luvunIndeksi == -1) return false;
        return true;
    }

    private int haeLuvunIndeksi(int luku) {
        for (int i = 0; i < alkioidenMaara; i++) {
            if (luku == luvut[i]) return i;
        }
        return -1;
    }

    public boolean poista(int luku) {
        int luvunIndeksi = haeLuvunIndeksi(luku);
        int apu;
        if (luvunIndeksi == -1) return false;
        luvut[luvunIndeksi] = 0;
        for (int i = luvunIndeksi; i < alkioidenMaara - 1; i++) {
            apu = luvut[i];
            luvut[i] = luvut[i + 1];
            luvut[i + 1] = apu;
        }
        alkioidenMaara--;
        return true;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < Math.min(vanha.length, uusi.length); i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenMaara;
    }


    @Override
    public String toString() {
        if (alkioidenMaara == 0) {
            return "{}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenMaara - 1; i++) {
                tuotos += luvut[i];
                tuotos += ", ";
            }
            tuotos += luvut[alkioidenMaara - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] uusiTaulukko = new int[alkioidenMaara];
        kopioiTaulukko(luvut, uusiTaulukko);
        return uusiTaulukko;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko uusiJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            uusiJoukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            uusiJoukko.lisaa(bTaulu[i]);
        }
        return uusiJoukko;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko uusiJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            if (b.kuuluu(aTaulu[i])) uusiJoukko.lisaa(aTaulu[i]);
        }
        return uusiJoukko;
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko uusiJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            if (b.kuuluu(aTaulu[i])) continue;
            uusiJoukko.lisaa(aTaulu[i]);
        }
 
        return uusiJoukko;
    }   
}
