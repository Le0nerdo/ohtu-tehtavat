package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
	}

	@Test
	public void constructorMakesStatisticsWithPlayer() {
		assertEquals("Semenko", stats.search("Semenko").getName());
	}

	@Test
	public void notPresentPLayerCanNotBeFound() {
		assertNull("Was not null", stats.search("name"));
	}

	@Test
	public void teamReturnsRightAmountOfPlayers() {
		assertEquals(3, stats.team("EDM").size());
	}

	@Test
	public void returnsTopScorer() {
		assertEquals("Gretzky", stats.topScorers(1).get(0).getName());
	}
}
