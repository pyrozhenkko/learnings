package com.example.demo.reservations;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long>{
    List<ReservationEntity> findAllByStatusIs(ReservationStatus status);
//    @Query(value = "select r from ReservationEntity r where r.status = :status ")//nativeQuery = true - голий sql
//    List<ReservationEntity> findAllByStatusIs(ReservationStatus status);
//
//    @Query("select r from ReservationEntity r where r.roomId = :roomId")
//    List<ReservationEntity> findAllByRoomId(@Param("roomId") Long roomId);
//
//    @Transactional
//    @Modifying
//    @Query("""
//            update ReservationEntity r
//            set r.userId = :userId,
//                r.roomId = :roomId,
//                r.startDate = :startDate,
//                r.endDate = :endDate,
//                r.status = :status
//            where r.id = :id
//            """)
//    int updateAllFields(
//            @Param("id") Long id,
//            @Param("userId") Long userId,
//            @Param("roomId") Long roomId,
//            @Param("startDate") LocalDate startDate,
//            @Param("endDate") LocalDate endDate,
//            @Param("status") ReservationStatus status
//    );


    @Transactional
    @Modifying
    @Query("""
            update ReservationEntity r 
            set r.status = :status 
            where r.id = :id
            """)
    void setStatus(
            @Param("id") Long id,
            @Param("status") ReservationStatus reservationStatus
    );
    @Transactional
    @Query("""
            select r.id from ReservationEntity r
            where r.roomId = :roomId 
            and :startDate < r.endDate
            and :endDate > r.startDate
            and r.status = :status
            """)
    List<Long> findConflictReservationIds(
            @Param("roomId") Long roomId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param(":status") ReservationStatus status
    );

    @Query("""
            select r.id from ReservationEntity r
            where (:roomId is null or r.roomId = :roomId)
            and (:userId is null or  r.userId = :userId)
            """)
    List<ReservationEntity> searchAllByFilter(
            @Param("roomId") Long roomId,
            @Param("userId") Long userId,
            Pageable pageable
    );
}
