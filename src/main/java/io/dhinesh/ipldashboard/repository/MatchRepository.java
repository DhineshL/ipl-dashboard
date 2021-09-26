package io.dhinesh.ipldashboard.repository;

import io.dhinesh.ipldashboard.Model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface MatchRepository extends CrudRepository<Match,Long> {
    List<Match> getByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);

    default List<Match> getLatestMatchesByTeam(String team1, String team2){

        Pageable pageable = PageRequest.of(0,4);
        List<Match> matches = getByTeam1OrTeam2OrderByDateDesc(team1,team2,pageable);

        return  matches;
    }
}
