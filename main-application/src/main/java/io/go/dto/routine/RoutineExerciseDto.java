package io.go.dto.routine;

import io.go.exercise.domain.exercise.enums.MovementPartType;
import io.go.exercise.interfaces.exercise.dto.ExerciseDto;
import io.go.exercise.interfaces.exercise.dto.ExerciseDto.Response;
import io.go.routine.domain.routine.vo.Set;
import io.go.routine.interfaces.routine.dto.RoutineDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(title = "루틴(운동포함) DTO")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RoutineExerciseDto {

    @Schema(title = "루틴 아이디")
    private long id;
    @Schema(title = "이름")
    private String name;
    @Schema(title = "삭제 여부")
    private boolean isDeleted;
    @Schema(title = "상세 정보")
    private List<RoutineExerciseDetailDto> details;

    public String getJoinedExerciseNames(String delimeter) {
        return this.details.stream().map(RoutineExerciseDetailDto::getExerciseName).collect(Collectors.joining(delimeter));
    }

    @Schema(title = "루틴(운동포함) 상세 DTO")
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class RoutineExerciseDetailDto {
        @Schema(title = "운동 아이디")
        private long exerciseId;
        @Schema(title = "운동 이름")
        private String exerciseName;
        @Schema(title = "부위")
        private MovementPartType movementPart;
        @Schema(title = "세트 정보")
        private List<Set> sets;
    }

    public static RoutineExerciseDto of(RoutineDto.Response routineDto, List<ExerciseDto.Response> exerciseDtos) {
        final Map<Long, ExerciseDto.Response> exerciseDtoMap = exerciseDtos.stream().collect(Collectors.toMap(Response::getId, Function.identity()));
        return RoutineExerciseDto.builder()
            .id(routineDto.getId())
            .name(routineDto.getName())
            .isDeleted(routineDto.isDeleted())
            .details(
                routineDto.getDetails().stream()
                    .map(d -> RoutineExerciseDetailDto.builder()
                        .exerciseId(d.getExerciseId())
                        .exerciseName(exerciseDtoMap.get(d.getExerciseId()).getName())
                        .movementPart(exerciseDtoMap.get(d.getExerciseId()).getMovementPart())
                        .sets(d.getSets())
                        .build())
                    .collect(Collectors.toList())
            )
            .build();
    }
}
