package io.go.routine.domain.routine.repository;

import io.go.routine.domain.routine.entity.Routine;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {
    List<Routine> findAllByUserId(long userId);

    List<Routine> findAllByUserIdAndIsDeleted(long userId, boolean isDeleted);
}
