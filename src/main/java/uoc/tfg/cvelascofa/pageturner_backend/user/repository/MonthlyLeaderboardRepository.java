package uoc.tfg.cvelascofa.pageturner_backend.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uoc.tfg.cvelascofa.pageturner_backend.user.entity.MonthlyLeaderboard;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonthlyLeaderboardRepository extends JpaRepository<MonthlyLeaderboard, Long> {

    Optional<MonthlyLeaderboard> findByUserIdAndMonthAndYear(Long userId, Integer month, Integer year);
    List<MonthlyLeaderboard> findAllByMonthAndYearOrderByPagesReadDesc(Integer month, Integer year);
    List<MonthlyLeaderboard> findAllByMonthAndYear(Integer month, Integer year);
    List<MonthlyLeaderboard> findTop3ByMonthAndYearOrderByRankingPositionAsc(int month, int year);

}