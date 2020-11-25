package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {
	private int arvo = 0;

	public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
		super(tuloskentta, syotekentta, nollaa, undo, sovellus);
	}

	public void suorita() {
		this.arvo = this.sovellus.tulos();
		this.sovellus.nollaa();
		this.undo.disableProperty().set(false);
		this.paivita();
	}

	public void peru() {
		this.sovellus.plus(arvo);
		this.undo.disableProperty().set(true);
		this.paivita();
	}
}
