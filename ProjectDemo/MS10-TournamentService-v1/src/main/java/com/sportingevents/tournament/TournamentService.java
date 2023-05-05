package com.sportingevents.tournament;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TournamentService {

    List<TournamentEntity> getAllTournaments();

    TournamentEntity getTournament(Integer tournamentId);

    String saveTournament(TournamentRequestModel tournamentRequestModel);

    TournamentEntity saveAndReturnTournament(TournamentRequestModel tournamentRequestModel);

    String updateTournament(Integer tournamentId, TournamentRequestModel tournamentRequestModel);

    String deleteTournament(Integer tournamentId);
}
