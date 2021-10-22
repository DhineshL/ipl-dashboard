package io.dhinesh.ipldashboard.repository;

import io.dhinesh.ipldashboard.Model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends CrudRepository<Match,Long> {
    List<Match> getByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);

    @Query("Select m from Match m where (m.team1= :teamName or team2= :teamName) and m.date between :dateStart and :dateEnd order by date desc")
    List<Match> getMatchesByTeamBetweenDates(@Param("teamName") String teamName,@Param("dateStart") LocalDate dateStart,@Param("dateEnd") LocalDate dateEnd );

//    List<Match> getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(String team1,LocalDate date1, LocalDate date2,String team2, LocalDate date3, LocalDate date4);

    default List<Match> getLatestMatchesByTeam(String team1, String team2){

        Pageable pageable = PageRequest.of(0,4);
        List<Match> matches = getByTeam1OrTeam2OrderByDateDesc(team1,team2,pageable);

        return  matches;
    }
}
