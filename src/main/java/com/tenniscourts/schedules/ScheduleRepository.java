package com.tenniscourts.schedules;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByTennisCourt_IdOrderByStartDateTime(Long id);
    @Query(value = "SELECT * FROM SCHEDULE where START_DATE_TIME like ?1% AND TENNIS_COURT_ID = ?2",nativeQuery = true)
    List<Schedule>findByStartDateTimeContains(String date,Long tennisCourtId);
    
}