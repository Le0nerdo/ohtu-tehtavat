package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

	Pankki pankki;
	Viitegeneraattori viite;
	Varasto varasto;
	Kauppa k;

	@Before
	public void setUp() {
		pankki = mock(Pankki.class);
		viite = mock(Viitegeneraattori.class);

        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
		when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
		when(varasto.saldo(2)).thenReturn(10); 
		when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "maito v2", 7));
		when(varasto.saldo(3)).thenReturn(0); 
		when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "maito v3", 10));

        // sitten testattava kauppa 
        k = new Kauppa(varasto, pankki, viite);     
	}

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {              
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
		// toistaiseksi ei välitetty kutsussa käytetyistä parametreista
	}
	
	@Test
    public void tilisiirtoLahteeOikeallaAsiakkaallaTilinumerollaJaSummallaKunYksiOstos() {         
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));   
		// toistaiseksi ei välitetty kutsussa käytetyistä parametreista
	}
	
	@Test
    public void tilisiirtoLahteeOikeallaAsiakkaallaTilinumerollaJaSummallaKunKaksiEriOstosta() {         
        // tehdään ostokset
        k.aloitaAsiointi();
		k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
		k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(12));   
		// toistaiseksi ei välitetty kutsussa käytetyistä parametreista
	}

	@Test
    public void tilisiirtoLahteeOikeallaAsiakkaallaTilinumerollaJaSummallaKunKaksiSamaaOstosta() {         
        // tehdään ostokset
        k.aloitaAsiointi();
		k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
		k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(10));   
		// toistaiseksi ei välitetty kutsussa käytetyistä parametreista
	}
	
	@Test
    public void tilisiirtoLahteeOikeallaAsiakkaallaTilinumerollaJaSummallaKunKaksiSamaaOstostaJoistaToinenLoppu() {         
        // tehdään ostokset
        k.aloitaAsiointi();
		k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
		k.lisaaKoriin(3);
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));   
		// toistaiseksi ei välitetty kutsussa käytetyistä parametreista
	}
	
	@Test
	public void uusiAsiointiEiSisallaVanhanAsioinninHintaa() {
		k.aloitaAsiointi();
		k.lisaaKoriin(1);
		k.aloitaAsiointi();
		k.tilimaksu("pekka", "12345");

		verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(0));
	}

	@Test
	public void pyydetaanUusiViiteJokaiseenTilimaksuun() {
		k.aloitaAsiointi();
		k.tilimaksu("pekka", "12345");

		verify(viite, times(1)).uusi();

		k.aloitaAsiointi();
		k.tilimaksu("pekka", "12345");

		verify(viite, times(2)).uusi();

		k.aloitaAsiointi();
		k.tilimaksu("pekka", "12345");

		verify(viite, times(3)).uusi();
	}

	@Test
	public void koristaPoistettuTuoteEiVaikutaHintaan() {
		k.aloitaAsiointi();
		k.lisaaKoriin(1);
		k.poistaKorista(1);
		k.tilimaksu("pekka", "12345");

		verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(0));
	}
}
