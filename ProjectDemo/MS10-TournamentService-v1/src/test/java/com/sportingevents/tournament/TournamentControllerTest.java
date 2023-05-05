package com.sportingevents.tournament;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportingevents.common.constant.ApiEndpoint;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@AutoConfigureMockMvc
public class TournamentControllerTest {

    private MockMvc mockMvc;
//the object on which the injection should be performed, the mock should be injected
    @InjectMocks
    private TournamentController tournamentController;

//    the mock object is created
    @Mock
    private TournamentServiceImpl tournamentService;

//    initializing before each test.
    @Before
    public void init() {
//        Initializes objects annotated with Mockito annotations for given testClass : TournamentControllerTest
        MockitoAnnotations.openMocks(this);
//        Build a MockMvc instance by registering one or more @Controller instances a
        this.mockMvc = MockMvcBuilders.standaloneSetup(tournamentController).build();
    }

    @Test
    public void givenAuthenticatedUser_whenGetAllTournaments_returnTournaments() throws Exception {
        when(tournamentService.getAllTournaments()).thenReturn(getAllTournaments());
        mockMvc.perform(MockMvcRequestBuilders.get(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.TOURNAMENTS))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(getAllTournaments())));
    }

    @Test
    public void givenAuthenticatedUser_whenGetTournament_returnTournament() throws Exception {
        when(tournamentService.getTournament(anyInt())).thenReturn(getTournament());
        mockMvc.perform(MockMvcRequestBuilders.get(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.TOURNAMENTS + ApiEndpoint.ID_PATH_VARIABLE, "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(getTournament())));
    }

    @Test
    public void givenAuthenticatedUser_whenSaveTournament_returnMessage() throws Exception {
        when(tournamentService.saveTournament(any(TournamentRequestModel.class))).thenReturn(TournamentMessage.TOURNAMENT_SAVE_SUCCESS);
        mockMvc.perform(MockMvcRequestBuilders.post(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.TOURNAMENTS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getTournamentRequestModel())))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(content().string(TournamentMessage.TOURNAMENT_SAVE_SUCCESS));
    }

    @Test
    public void givenAuthenticatedUser_whenSaveTournament_returnMessage_Simple() throws Exception {
        when(tournamentService.saveTournament(any(TournamentRequestModel.class))).thenReturn("Tournament saved successfully.");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tournaments")
                        .contentType("application/json")
                        .content(asJsonString(getTournamentRequestModel())))
                .andExpect(status().is(201))
                .andExpect(content().string("Tournament saved successfully."));
    }
    @Test
    public void givenAuthenticatedUser_whenUpdateTournament_returnMessage() throws Exception {
        when(tournamentService.updateTournament(anyInt(), any(TournamentRequestModel.class))).thenReturn(TournamentMessage.TOURNAMENT_UPDATE_SUCCESS);
        mockMvc.perform(MockMvcRequestBuilders.put(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.TOURNAMENTS + ApiEndpoint.ID_PATH_VARIABLE, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getTournamentRequestModel())))
                .andExpect(status().isOk())
                .andExpect(content().string(TournamentMessage.TOURNAMENT_UPDATE_SUCCESS));
    }

    @Test
    public void givenAuthenticatedUser_whenDeleteTournament_returnMessage() throws Exception {
        when(tournamentService.deleteTournament(anyInt())).thenReturn(TournamentMessage.TOURNAMENT_DELETE_SUCCESS);
        mockMvc.perform(MockMvcRequestBuilders.delete(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.TOURNAMENTS + ApiEndpoint.ID_PATH_VARIABLE, "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(TournamentMessage.TOURNAMENT_DELETE_SUCCESS));
    }

    public List<TournamentEntity> getAllTournaments() {
        List<TournamentEntity> tournaments = new ArrayList<>();
        for(int x=0;x<2;x++) {
            TournamentEntity tournament = new TournamentEntity();
            tournament.setTournamentId(x+1);
            tournament.setTournamentName("test");
            tournament.setTournamentStyle("test");
            tournament.setActive(true);
            tournament.setSportsCategory("test");
            tournaments.add(tournament);
        }
        return tournaments;
    }

    public TournamentEntity getTournament() {
        TournamentEntity tournament = new TournamentEntity();
        tournament.setTournamentId(1);
        tournament.setTournamentName("test");
        tournament.setTournamentStyle("test");
        tournament.setActive(true);
        tournament.setSportsCategory("test");
        return tournament;
    }

    public TournamentRequestModel getTournamentRequestModel() {
        TournamentRequestModel tournament = new TournamentRequestModel();
        tournament.setTournamentName("test");
        tournament.setTournamentStyle("test");
        tournament.setSportsCategory("test");
        return tournament;
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
