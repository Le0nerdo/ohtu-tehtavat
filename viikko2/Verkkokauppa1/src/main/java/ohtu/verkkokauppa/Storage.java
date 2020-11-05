package ohtu.verkkokauppa;

public interface Storage {
	Tuote haeTuote(int id);
	void palautaVarastoon(Tuote t);
	int saldo(int id);
	void otaVarastosta(Tuote t);
}
