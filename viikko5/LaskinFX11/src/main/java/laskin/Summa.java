package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {
	private int arvo = 0;

	public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
		super(tuloskentta, syotekentta, nollaa, undo, sovellus);
	}

	public void suorita() {
		try {
			this.arvo = Integer.parseInt(syotekentta.getText());
		} catch (Exception e) {
		}

		this.sovellus.plus(arvo);
		this.undo.disableProperty().set(false);
		this.paivita();
	}

	public void peru() {
		this.sovellus.miinus(arvo);
		this.undo.disableProperty().set(true);
		this.paivita();
	}
}
