package com.sportingevents.tournament;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<TournamentEntity, Integer> {

    Page<TournamentEntity> findByActiveTrue(Pageable pageable);

    Optional<TournamentEntity> findByTournamentIdAndActiveTrue(Integer tournamentId);

//    @Override
//    <S extends TournamentEntity> S saveAndFlush(S entity);
}
