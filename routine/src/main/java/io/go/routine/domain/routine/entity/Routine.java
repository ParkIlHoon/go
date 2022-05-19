package io.go.routine.domain.routine.entity;

import io.go.routine.domain.routine.vo.Set;
import io.go.routine.interfaces.routine.dto.RoutineDto;
import java.util.List;
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
import lombok.ToString;

/**
 * <h1>루틴 엔티티</h1>
 */
@Entity
@Getter
@ToString
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Routine {

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
     * <h3>이름</h3>
     */
    @Column(nullable = false)
    private String name;

    /**
     * <h3>삭제 여부</h3>
     */
    private boolean isDeleted = false;

    /**
     * <h3>루틴 세트</h3>
     */
    @OneToMany(mappedBy = "routine", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<RoutineDetail> details;



    @Builder
    public Routine(long userId, String name) {
        this.userId = userId;
        this.name = name;

        validate();
    }

    private void validate() {
        if (userId <= 0 || name == null) {
            throw new IllegalArgumentException("유효하지 않은 인자입니다.");
        }
    }

    /**
     * 운동 세트를 추가합니다.
     *
     * @param exerciseId 운동 아이디
     * @param sets       세트 정보
     */
    public void addExercise(long exerciseId, List<Set> sets) {
        Optional<RoutineDetail> optionalExercise = this.details.stream().filter(s -> s.getExerciseId() == exerciseId).findAny();
        if (optionalExercise.isEmpty()) {
            this.details.add(RoutineDetail.builder().routine(this).exerciseId(exerciseId).sets(sets).build());
        } else {
            List<Set> routineSet = optionalExercise.get().getSets();
            routineSet.clear();
            routineSet.addAll(sets);
        }
    }

    /**
     * 루틴을 제거합니다.
     */
    public void delete() {
        this.isDeleted = true;
    }

    public RoutineDto.Response toDto() {
        return RoutineDto.Response.builder()
            .id(this.id)
            .name(this.name)
            .isDeleted(this.isDeleted)
            .details(this.details.stream().map(RoutineDetail::toDto).collect(Collectors.toList()))
            .build();
    }
}
