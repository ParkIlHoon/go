package io.go.controller.api;

import io.go.dto.GoResponseDto;
import io.go.dto.routine.RoutineExerciseDto;
import io.go.exercise.domain.exercise.enums.MovementPartType;
import io.go.exercise.interfaces.exercise.dto.ExerciseDto;
import io.go.routine.interfaces.routine.dto.RoutineDto.Response;
import io.go.routine.interfaces.routine.service.RoutineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "루틴")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines")
public class RoutineController {

    private final RoutineService routineService;

    private static final List<ExerciseDto.Response> SAMPLE_DATA = List.of(
        ExerciseDto.Response.builder().id(1).name("윗몸 일으키기").movementPart(MovementPartType.ABS).build(),
        ExerciseDto.Response.builder().id(2).name("이두 컬").movementPart(MovementPartType.ARM).build(),
        ExerciseDto.Response.builder().id(3).name("데드 리프트").movementPart(MovementPartType.BACK).build(),
        ExerciseDto.Response.builder().id(4).name("벤치 프레스").movementPart(MovementPartType.CHEST).build(),
        ExerciseDto.Response.builder().id(5).name("힙 쓰러스트").movementPart(MovementPartType.HIP).build(),
        ExerciseDto.Response.builder().id(6).name("스쿼트").movementPart(MovementPartType.LOWER_BODY).build(),
        ExerciseDto.Response.builder().id(7).name("숄더 프레스").movementPart(MovementPartType.SHOULDER).build(),
        ExerciseDto.Response.builder().id(8).name("러닝머신").movementPart(MovementPartType.AEROBIC).build()
    );

    @GetMapping
    @Operation(summary = "루틴 목록을 조회합니다.")
    public ResponseEntity<GoResponseDto<List<RoutineExerciseDto>>> getRoutines(long userId) {
        List<Response> routines = routineService.getRoutinesByUser(userId, false);
        //TODO 운동 리스트 조회
        return ResponseEntity.ok(GoResponseDto.ok(routines.stream().map(r -> RoutineExerciseDto.of(r, SAMPLE_DATA)).collect(Collectors.toList())));
    }


}
