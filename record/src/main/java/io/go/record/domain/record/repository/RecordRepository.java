package io.go.record.domain.record.repository;

import io.go.record.domain.record.entity.Record;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    Optional<Record> findByUserIdAndStartDatetimeBetween(long userId, LocalDateTime from, LocalDateTime to);

    Page<Record> findAllByUserId(long userId, Pageable pageable);

}
