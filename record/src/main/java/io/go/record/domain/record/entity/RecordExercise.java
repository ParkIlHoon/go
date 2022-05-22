package io.go.record.domain.record.entity;

import io.go.record.interfaces.record.dto.RecordExerciseDto;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <h1>기록된 운동 엔티티</h1>
 */
@Entity
@Getter
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecordExercise {

    /**
     * <h3>아이디</h3>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * <h3>기록 아이디</h3>
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "record_id")
    private Record record;

    /**
     * <h3>루틴 아이디</h3>
     */
    private Long routineId;

    /**
     * <h3>운동 아이디</h3>
     */
    @Column(nullable = false)
    private long exerciseId;

    /**
     * <h3>순서</h3>
     */
    @Column(nullable = false)
    private int order;

    /**
     * <h3>무게</h3>
     */
    private int weight;

    /**
     * <h3>개수</h3>
     */
    private int count;

    /**
     * <h3>초</h3>
     */
    private int second;

    /**
     * <h3>시작 일시</h3>
     */
    @Column(nullable = false)
    private LocalDateTime startDatetime;

    /**
     * <h3>종료 일시</h3>
     */
    @Column(nullable = false)
    private LocalDateTime endDatetime;



    @Builder
    public RecordExercise(Record record, Long routineId, long exerciseId, int order, int weight, int count, int second, LocalDateTime startDatetime, LocalDateTime endDatetime) {
        this.record = record;
        this.routineId = routineId;
        this.exerciseId = exerciseId;
        this.order = order;
        this.weight = weight;
        this.count = count;
        this.second = second;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;

        validate();
    }

    private void validate() {
        if (Objects.isNull(record)) {
            throw new IllegalArgumentException("기록은 null일 수 없습니다.");
        }
        if (routineId <= 0) {
            throw new IllegalArgumentException("루틴 아이디는 0 이하일 수 없습니다,");
        }
        if (exerciseId <= 0) {
            throw new IllegalArgumentException("운동 아이디는 0 이하일 수 없습니다,");
        }
        if (order <= 0) {
            throw new IllegalArgumentException("순서는 0 이하일 수 없습니다.");
        }
        if (weight <= 0 && count <= 0 && second <= 0) {
            throw new IllegalArgumentException("잘못된 기록입니다.");
        }
        if (Objects.isNull(startDatetime) || Objects.isNull(endDatetime)) {
            throw new IllegalArgumentException("시작일시나 종료일시는 null일 수 없습니다.");
        }
        if (startDatetime.isAfter(endDatetime)) {
            throw new IllegalArgumentException("시작일시는 종료일시보다 이후일 수 없습니다.");
        }
    }

    /**
     * 응답 DTO로 변환합니다.
     * @return 응답 DTO
     */
    public RecordExerciseDto.Response toDto() {
        return RecordExerciseDto.Response.builder()
            .id(id)
            .routineId(routineId)
            .exerciseId(exerciseId)
            .order(order)
            .weight(weight)
            .count(count)
            .second(second)
            .startDatetime(startDatetime)
            .endDatetime(endDatetime)
            .build();
    }
}
