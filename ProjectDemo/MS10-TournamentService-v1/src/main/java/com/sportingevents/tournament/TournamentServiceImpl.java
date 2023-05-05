package com.sportingevents.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Override
    public List<TournamentEntity> getAllTournaments() {
        List<TournamentEntity> tournamentEntityList = tournamentRepository.findAll();
        if(tournamentEntityList.isEmpty())
            throw new TournamentException(TournamentMessage.TOURNAMENT_NOT_FOUND);
        return tournamentEntityList;
    }

    @Override
    public TournamentEntity getTournament(Integer tournamentId) {
        Optional<TournamentEntity> tournamentEntityOptional = tournamentRepository.findByTournamentIdAndActiveTrue(tournamentId);
        if(!tournamentEntityOptional.isPresent())
            throw new TournamentException(TournamentMessage.TOURNAMENT_NOT_FOUND);
        return tournamentEntityOptional.get();
    }

    @Override
    public String saveTournament(TournamentRequestModel tournamentRequestModel) {
        TournamentEntity tournamentEntity = new TournamentEntity();
        tournamentEntity.setTournamentName(tournamentRequestModel.getTournamentName());
        tournamentEntity.setTournamentStyle(tournamentRequestModel.getTournamentStyle());
        tournamentEntity.setSportsCategory(tournamentRequestModel.getSportsCategory());
        tournamentRepository.saveAndFlush(tournamentEntity);
//        return TournamentMessage.TOURNAMENT_SAVE_SUCCESS;
        return "Tournament saved successfully.";
    }

    @Override
    public TournamentEntity saveAndReturnTournament(TournamentRequestModel tournamentRequestModel) {
        TournamentEntity tournamentEntity = new TournamentEntity();
        tournamentEntity.setTournamentName(tournamentRequestModel.getTournamentName());
        tournamentEntity.setTournamentStyle(tournamentRequestModel.getTournamentStyle());
        tournamentEntity.setSportsCategory(tournamentRequestModel.getSportsCategory());
        tournamentRepository.saveAndFlush(tournamentEntity);
        TournamentEntity tournament = new TournamentEntity();
        tournament.setTournamentStyle("test");
        tournament.setSportsCategory("test");
        tournament.setActive(true);
        tournament.setTournamentId(1);
        tournament.setTournamentName("test");
        return tournament;
//        return TournamentMessage.TOURNAMENT_SAVE_SUCCESS;
//        return "Tournament saved successfully.";
    }

    @Override
    public String updateTournament(Integer tournamentId, TournamentRequestModel tournamentRequestModel) {
        Optional<TournamentEntity> tournamentEntityOptional = tournamentRepository.findByTournamentIdAndActiveTrue(tournamentId);
        if(!tournamentEntityOptional.isPresent())
            throw new TournamentException(TournamentMessage.TOURNAMENT_NOT_FOUND);
        TournamentEntity tournamentEntity = tournamentEntityOptional.get();
        tournamentEntity.setTournamentName(tournamentRequestModel.getTournamentName());
        tournamentEntity.setTournamentStyle(tournamentRequestModel.getTournamentStyle());
        tournamentEntity.setSportsCategory(tournamentRequestModel.getSportsCategory());
        tournamentRepository.saveAndFlush(tournamentEntity);
        return TournamentMessage.TOURNAMENT_UPDATE_SUCCESS;
    }

    @Override
    public String deleteTournament(Integer tournamentId) {
        Optional<TournamentEntity> tournamentEntityOptional = tournamentRepository.findByTournamentIdAndActiveTrue(tournamentId);
        if(!tournamentEntityOptional.isPresent())
            throw new TournamentException(TournamentMessage.TOURNAMENT_NOT_FOUND);
        TournamentEntity tournamentEntity = tournamentEntityOptional.get();
        tournamentEntity.setActive(false);
        tournamentRepository.saveAndFlush(tournamentEntity);
        return TournamentMessage.TOURNAMENT_DELETE_SUCCESS;
    }
}
