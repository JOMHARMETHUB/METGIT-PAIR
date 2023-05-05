package com.sportingevents.tournament;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TournamentEntityTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void whenInitialized_returnTournamentEntity() {
        TournamentEntity expected = new TournamentEntity();
        TournamentEntity actual = getTournamentEntity();
        expected.setTournamentId(1);
        expected.setTournamentName("test");
        expected.setTournamentStyle("test");
        expected.setSportsCategory("test");
        expected.setActive(true);
        Assert.assertEquals(expected.getTournamentId(), actual.getTournamentId());
        Assert.assertEquals(expected.getTournamentName(), actual.getTournamentName());
        Assert.assertEquals(expected.getTournamentStyle(), actual.getTournamentStyle());
        Assert.assertEquals(expected.getSportsCategory(), actual.getSportsCategory());
        Assert.assertEquals(expected.getActive(), actual.getActive());
    }
//Why?
    @Test(expected = NullPointerException.class)
    public void whenIdNull_returnException() {
//        ExpectedException exception = ExpectedException.none();
 //       exception.expect(NullPointerException.class);
        TournamentEntity tournament = new TournamentEntity();
        tournament.setTournamentId(null);
    }

    @Test
    public void whenTournamentNameNull_returnException() {
        exception.expect(NullPointerException.class);
        TournamentEntity tournament = new TournamentEntity();
        tournament.setTournamentName(null);
    }

    @Test
    public void whenActiveNull_returnException() {
        exception.expect(NullPointerException.class);
        TournamentEntity tournament = new TournamentEntity();
        tournament.setActive(null);
    }

    @Test
    public void whenTournamentStyleNull_returnException() {
        exception.expect(NullPointerException.class);
        TournamentEntity tournament = new TournamentEntity();
        tournament.setTournamentStyle(null);
    }

    @Test
    public void whenSportsCategoryNull_returnException() {
        exception.expect(NullPointerException.class);
        TournamentEntity tournament = new TournamentEntity();
        tournament.setSportsCategory(null);
    }

    private TournamentEntity getTournamentEntity() {
        TournamentEntity tournamentEntity = new TournamentEntity();
        tournamentEntity.setTournamentId(1);
        tournamentEntity.setTournamentName("test");
        tournamentEntity.setTournamentStyle("test");
        tournamentEntity.setSportsCategory("test");
        tournamentEntity.setActive(true);
        return tournamentEntity;
    }
}
