package io.go.routine.domain.routine.entity;

import io.go.routine.domain.routine.converter.SetJsonConverter;
import io.go.routine.domain.routine.vo.Set;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
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
import lombok.ToString;

/**
 * <h1>루틴 상세 엔티티</h1>
 */
@Entity
@Getter
@ToString
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoutineDetail {

    /**
     * <h3>아이디</h3>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * <h3>루틴</h3>
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "routine_id")
    private Routine routine;

    /**
     * <h3>운동 아이디</h3>
     */
    @Column(nullable = false)
    private long exerciseId;

    /**
     * <h3>세트 정보</h3>
     */
    @Column(name = "content", nullable = false)
    @Convert(converter = SetJsonConverter.class)
    private List<Set> sets;



    @Builder
    public RoutineDetail(Routine routine, long exerciseId, List<Set> sets) {
        this.routine = routine;
        this.exerciseId = exerciseId;
        this.sets = sets;
    }
}
