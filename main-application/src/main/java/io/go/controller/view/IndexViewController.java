package io.go.controller.view;

import io.go.dto.routine.RoutineExerciseDto;
import io.go.exercise.domain.exercise.enums.MovementPartType;
import io.go.exercise.interfaces.exercise.dto.ExerciseDto;
import io.go.exercise.interfaces.exercise.dto.ExerciseDto.Response;
import io.go.routine.interfaces.routine.dto.RoutineDto;
import io.go.routine.interfaces.routine.service.RoutineService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class IndexViewController {

    private final RoutineService routineService;

    private static final long USER_ID = 1L;
    private static final List<Response> SAMPLE_DATA = List.of(
        ExerciseDto.Response.builder().id(1).name("윗몸 일으키기").movementPart(MovementPartType.ABS).build(),
        ExerciseDto.Response.builder().id(2).name("이두 컬").movementPart(MovementPartType.ARM).build(),
        ExerciseDto.Response.builder().id(3).name("데드 리프트").movementPart(MovementPartType.BACK).build(),
        ExerciseDto.Response.builder().id(4).name("벤치 프레스").movementPart(MovementPartType.CHEST).build(),
        ExerciseDto.Response.builder().id(5).name("힙 쓰러스트").movementPart(MovementPartType.HIP).build(),
        ExerciseDto.Response.builder().id(6).name("스쿼트").movementPart(MovementPartType.LOWER_BODY).build(),
        ExerciseDto.Response.builder().id(7).name("숄더 프레스").movementPart(MovementPartType.SHOULDER).build(),
        ExerciseDto.Response.builder().id(8).name("러닝머신").movementPart(MovementPartType.AEROBIC).build()
    );

    @GetMapping("/")
    public String index(@RequestParam(value = "theme", required = false) String theme, Model model) {
        model.addAttribute("theme", Objects.requireNonNullElse(theme, "light"));

        List<RoutineDto.Response> routines = routineService.getRoutinesByUser(USER_ID, false);
        List<RoutineExerciseDto> routineExerciseDtos = routines.stream().map(r -> RoutineExerciseDto.of(r, SAMPLE_DATA)).collect(Collectors.toList());
        model.addAttribute("routines", routineExerciseDtos);
        return "index";
    }

}
