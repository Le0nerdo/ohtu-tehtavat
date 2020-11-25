package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Komento {
	protected TextField tuloskentta;
	protected TextField syotekentta;
	protected Button nollaa;
    protected Button undo;
	protected Sovelluslogiikka sovellus;

	protected Komento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
		this.tuloskentta = tuloskentta;
		this.syotekentta = syotekentta;
		this.nollaa = nollaa;
		this.undo = undo;
		this.sovellus = sovellus;
	}

	public abstract void suorita();

	public abstract void peru();

	protected void paivita() {
		int laskunTulos = this.sovellus.tulos();
		this.syotekentta.setText("");
		this.tuloskentta.setText("" + laskunTulos);

		if (laskunTulos == 0) {
			this.nollaa.disableProperty().set(true);
		} else {
			this.nollaa.disableProperty().set(false);
		}
	}
}
