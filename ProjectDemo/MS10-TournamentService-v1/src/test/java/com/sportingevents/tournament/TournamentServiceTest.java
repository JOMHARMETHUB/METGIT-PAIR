package com.sportingevents.tournament;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TournamentServiceTest {

    @InjectMocks
    private TournamentServiceImpl tournamentService;

    @Mock
    private TournamentRepository tournamentRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenGetAllTournaments_returnAllTournaments() {
        when(tournamentRepository.findAll()).thenReturn(getTournamentEntities());
        List<TournamentEntity> result = tournamentService.getAllTournaments();;
        Assert.assertNotNull(result);
    }


    @Test
    public void givenValidId_whenGetTournament_returnTournament() {
        when(tournamentRepository.findByTournamentIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.of(getTournamentEntity()));
        TournamentEntity result = tournamentService.getTournament(1);
        Assert.assertNotNull(result);
    }

    @Test
    public void givenValidRequestModel_whenSaveTournament_returnMessage() {
        TournamentRequestModel tournament = getTournamentRequestModel();
        String result = tournamentService.saveTournament(tournament);
//        Assert.assertEquals(TournamentMessage.TOURNAMENT_SAVE_SUCCESS, result);

                Assert.assertEquals("Tournament saved successfully.", result);
    }

    @Test
    public void givenValidRequestModel_whenSaveTournament_returnTournament() {
        TournamentRequestModel tournament = getTournamentRequestModel();
        TournamentEntity expected = getTournamentEntity();
        when(tournamentRepository.saveAndFlush(any(TournamentEntity.class))).thenReturn(getTournamentEntity());
        TournamentEntity result = tournamentService.saveAndReturnTournament(tournament);
        Assert.assertEquals(expected.toString(), result.toString());
    }

    @Test
    public void givenValidRequestModelAndValidId_whenUpdateTournament_returnMessage() {
        TournamentRequestModel tournament = getTournamentRequestModel();
        when(tournamentRepository.findByTournamentIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.of(getTournamentEntity()));
        String result = tournamentService.updateTournament(1, tournament);
        Assert.assertEquals(TournamentMessage.TOURNAMENT_UPDATE_SUCCESS, result);
    }

    @Test
    public void givenValidId_whenDeleteTournament_returnMessage() {
        when(tournamentRepository.findByTournamentIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.of(getTournamentEntity()));
        String result = tournamentService.deleteTournament(1);
        Assert.assertEquals(TournamentMessage.TOURNAMENT_DELETE_SUCCESS, result);
    }

    private TournamentRequestModel getTournamentRequestModel() {
        TournamentRequestModel tournamentRequestModel = new TournamentRequestModel();
        tournamentRequestModel.setTournamentStyle("test");
        tournamentRequestModel.setTournamentName("test");
        tournamentRequestModel.setSportsCategory("test");
        return tournamentRequestModel;
    }

    private List<TournamentEntity> getTournamentEntities() {
        List<TournamentEntity> tournaments = new ArrayList<>();
        for(int x=0;x<2;x++) {
            TournamentEntity tournament = getTournamentEntity();
            tournament.setTournamentId(x+1);
            tournaments.add(tournament);
        }
        return tournaments;
    }

    private TournamentEntity getTournamentEntity() {
        TournamentEntity tournamentEntity = new TournamentEntity();
        tournamentEntity.setTournamentStyle("test");
        tournamentEntity.setSportsCategory("test");
        tournamentEntity.setActive(true);
        tournamentEntity.setTournamentId(1);
        tournamentEntity.setTournamentName("test");
        return tournamentEntity;
    }
}
