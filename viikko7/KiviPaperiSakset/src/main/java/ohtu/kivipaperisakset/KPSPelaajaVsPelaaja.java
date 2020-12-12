package ohtu.kivipaperisakset;

public class KPSPelaajaVsPelaaja extends KiviPaperiSakset {

    @Override
    protected String toisenSiirto() {
        System.out.print("Toisen pelaajan siirto: ");
        return super.scanner.nextLine();
    }
}
