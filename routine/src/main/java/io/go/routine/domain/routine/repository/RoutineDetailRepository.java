package io.go.routine.domain.routine.repository;

import io.go.routine.domain.routine.entity.RoutineDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineDetailRepository extends JpaRepository<RoutineDetail, Long> {

}
