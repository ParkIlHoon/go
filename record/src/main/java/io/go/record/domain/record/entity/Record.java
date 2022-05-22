package io.go.record.domain.record.entity;

import io.go.record.interfaces.record.dto.RecordDto;
import io.go.record.interfaces.record.exception.ExerciseRecordNotFoundException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <h1>기록 엔티티</h1>
 */
@Entity
@Getter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Record {

    /**
     * <h3>아이디</h3>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * <h3>사용자 아이디</h3>
     */
    @Column(nullable = false)
    private long userId;

    /**
     * <h3>시작일시</h3>
     */
    @Column(nullable = false)
    private LocalDateTime startDatetime;

    /**
     * <h3>종료일시</h3>
     */
    @Column(nullable = false)
    private LocalDateTime endDatetime;

    /**
     * <h3>기록된 운동 리스트</h3>
     */
    @OneToMany(mappedBy = "record", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExerciseRecord> exerciseRecords;



    @Builder
    public Record(long userId) {
        this.userId = userId;

        validate();
    }

    private void validate() {
        if (Objects.isNull(userId) || userId <= 0) {
            throw new IllegalArgumentException("사용자 아이디가 유효하지 않습니다.");
        }
    }

    /**
     * 기록 타임을 세팅합니다.
     * <br>
     * 기록된 운동의 시작일시와 종료일시를 이용해 전체 운동의 시작일시와 종료일시를 세팅합니다.
     * @throws ExerciseRecordNotFoundException 기록된 운동지 존재하지 않는 경우
     */
    public void setRecordTimes() {
        Optional<ExerciseRecord> optionalFirstExercise = exerciseRecords.stream().sorted(Comparator.comparing(ExerciseRecord::getStartDatetime)).findFirst();
        Optional<ExerciseRecord> optionalLastExercise = exerciseRecords.stream().sorted(Comparator.comparing(ExerciseRecord::getEndDatetime).reversed()).findFirst();

        if (optionalFirstExercise.isEmpty() || optionalLastExercise.isEmpty()) {
            throw new ExerciseRecordNotFoundException("기록된 운동이 존재하지 않습니다.");
        }

        this.startDatetime = optionalFirstExercise.get().getStartDatetime();
        this.endDatetime = optionalLastExercise.get().getEndDatetime();
    }

    /**
     * 응답 DTO로 변환합니다.
     * @return 응답 DTO
     */
    public RecordDto.Response toDto() {
        return RecordDto.Response.builder()
            .id(id)
            .userId(userId)
            .startDatetime(startDatetime)
            .endDatetime(endDatetime)
            .exerciseRecords(
                exerciseRecords.stream()
                    .map(ExerciseRecord::toDto)
                    .collect(Collectors.toList())
            )
            .build();
    }
}
