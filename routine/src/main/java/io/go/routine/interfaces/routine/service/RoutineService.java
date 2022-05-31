package io.go.routine.interfaces.routine.service;

import io.go.routine.domain.routine.vo.Set;
import io.go.routine.interfaces.routine.dto.RoutineDto;
import java.util.List;
import java.util.Optional;
import org.springframework.validation.annotation.Validated;

/**
 * <h1>루틴 서비스</h1>
 */
@Validated
public interface RoutineService {

    /**
     * 새 루틴을 생성합니다.
     *
     * @param createDto 루틴 생성 DTO
     * @return 생성된 루틴 DTO
     */
    RoutineDto.Response createRoutine(RoutineDto.Create createDto);

    /**
     * 루틴을 조회합니다.
     *
     * @param routineId 루틴 아이디
     * @return 루틴 DTO
     */
    Optional<RoutineDto.Response> getRoutine(long routineId);

    /**
     * 루틴 목록을 조회합니다.
     *
     * @param userId 사용자 아이디
     * @return 사용자 아이디에 해당하는 루틴 목록
     */
    List<RoutineDto.Response> getRoutinesByUser(long userId);

    /**
     * 루틴 목록을 조회합니다.
     *
     * @param userId    사용자 아이디
     * @param isDeleted 삭제 여부
     * @return 사용자 아이디에 해당하는 루틴 목록
     */
    List<RoutineDto.Response> getRoutinesByUser(long userId, boolean isDeleted);

    /**
     * 루틴의 운동을 세팅합니다.
     *
     * @param routineId  루틴 아이디
     * @param exerciseId 운동 아이디
     * @param sets       세트 정보
     * @return 루틴 DTO
     */
    RoutineDto.Response setExercise(long routineId, long exerciseId, List<Set> sets);

    /**
     * 루틴의 운동을 제거합니다.
     *
     * @param routineId  루틴 아이디
     * @param exerciseId 운동 아이디
     */
    void deleteExercise(long routineId, long exerciseId);

    /**
     * 루틴을 제거합니다.
     *
     * @param routineId 루틴 아이디
     */
    void deleteRoutine(long routineId);
}
