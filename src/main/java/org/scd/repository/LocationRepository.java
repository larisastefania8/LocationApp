package org.scd.repository;

import org.scd.model.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;
import java.util.List;

public interface LocationRepository extends CrudRepository<Location,Long> {
@Query(value = "SELECT * FROM Locations l WHERE l.user_id = :id and l.date > :startDate and l.date < :endDate",nativeQuery = true)
List<Location> findAllByUserId(@Param("id") Long userid,@Param("startDate")String startDate,@Param("endDate")String endDate);
    Optional<Location> findById(Long Id);
  List<Location> findAllByUserId(Long userId);
}

//crud repo are metode care face chestii pe bd
