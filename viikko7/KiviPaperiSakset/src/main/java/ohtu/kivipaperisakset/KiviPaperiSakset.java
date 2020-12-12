package ohtu.kivipaperisakset;
import java.util.Scanner;

public abstract class KiviPaperiSakset {
	protected final Scanner scanner = new Scanner(System.in);
	protected String ekanSiirto = "";

    public void pelaa() {
		Tuomari tuomari = new Tuomari();

		String[] siirrot = molemmatSiirrot();
        while (onkoOkSiirto(siirrot[0]) && onkoOkSiirto(siirrot[1])) {
			tuomari.kirjaaSiirto(siirrot[0], siirrot[1]);
            System.out.println(tuomari);
            System.out.println();

			siirrot = molemmatSiirrot();
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
	}

	private String[] molemmatSiirrot() {
		String ekaSiirto = ensimmaisenSiirto();
		String tokaSiirto = toisenSiirto();
		String[] siirrot = {ekaSiirto, tokaSiirto};
		return siirrot;
	}

    protected String ensimmaisenSiirto() {
        System.out.print("Ensimmäisen pelaajan siirto: ");
		String siirto = scanner.nextLine();
		ekanSiirto = siirto;
		return siirto;
    }

    // tämä on abstrakti metodi sillä sen toteutus vaihtelee eri pelityypeissä
    abstract protected String toisenSiirto();
    
    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}
