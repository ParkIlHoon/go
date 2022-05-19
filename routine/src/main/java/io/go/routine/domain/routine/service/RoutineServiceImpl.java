package io.go.routine.domain.routine.service;

import io.go.routine.domain.routine.entity.Routine;
import io.go.routine.domain.routine.entity.RoutineDetail;
import io.go.routine.domain.routine.repository.RoutineDetailRepository;
import io.go.routine.domain.routine.repository.RoutineRepository;
import io.go.routine.domain.routine.vo.Set;
import io.go.routine.interfaces.routine.dto.RoutineDetailDto;
import io.go.routine.interfaces.routine.dto.RoutineDto;
import io.go.routine.interfaces.routine.service.RoutineService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoutineServiceImpl implements RoutineService {

    private final RoutineRepository routineRepository;
    private final RoutineDetailRepository routineDetailRepository;

    @Override
    @Transactional
    public RoutineDto.Response createRoutine(RoutineDto.Create createDto) {
        Routine newRoutine = Routine.builder()
            .userId(createDto.getUserId())
            .name(createDto.getName())
            .build();

        for (RoutineDetailDto.Create detail : createDto.getDetails()) {
            newRoutine.addExercise(detail.getExerciseId(), detail.getSets());
        }

        return routineRepository.save(newRoutine).toDto();
    }

    @Override
    public Optional<RoutineDto.Response> getRoutine(long routineId) {
        return routineRepository.findById(routineId).map(Routine::toDto);
    }

    @Override
    public List<RoutineDto.Response> getRoutinesByUser(long userId) {
        return routineRepository.findAllByUserId(userId).stream().map(Routine::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RoutineDto.Response setExercise(long routineId, long exerciseId, List<Set> sets) {
        Optional<Routine> optionalRoutine = routineRepository.findById(routineId);
        if (optionalRoutine.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 루틴입니다.");
        }

        Routine routine = optionalRoutine.get();
        routine.addExercise(exerciseId, sets);
        return routineRepository.save(routine).toDto();
    }

    @Override
    @Transactional
    public void deleteExercise(long routineId, long exerciseId) {
        Optional<Routine> optionalRoutine = routineRepository.findById(routineId);
        if (optionalRoutine.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 루틴입니다.");
        }

        Optional<RoutineDetail> optionalDetail = optionalRoutine.get().getDetails().stream().filter(d -> d.getExerciseId() == exerciseId).findAny();
        if (optionalDetail.isEmpty()) {
            throw new IllegalStateException("루틴에 포함되지 않은 운동입니다.");
        }

        routineDetailRepository.delete(optionalDetail.get());
    }

    @Override
    @Transactional
    public void deleteRoutine(long routineId) {
        Optional<Routine> optionalRoutine = routineRepository.findById(routineId);
        if (optionalRoutine.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 루틴입니다.");
        }

        optionalRoutine.get().delete();
    }
}
