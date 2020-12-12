package ohtu.kivipaperisakset;

public class KVSPelitehdas {
	public static KiviPaperiSakset luoPeli(int moodi) {
		switch (moodi) {
			case 1:
				return new KPSPelaajaVsPelaaja();
			case 2:
				return new KPSTekoaly();
			case 3:
				return new KPSParempiTekoaly();
		}
		return null;
	}
	
}
