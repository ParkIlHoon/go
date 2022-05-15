package io.go.routine.domain.routine.service;

import io.go.routine.domain.routine.vo.Set;
import io.go.routine.interfaces.routine.dto.RoutineDto.Create;
import io.go.routine.interfaces.routine.dto.RoutineDto.Response;
import io.go.routine.interfaces.routine.service.RoutineService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class RoutineServiceImpl implements RoutineService {

    @Override
    public Response createRoutine(Create createDto) {
        //TODO
        return null;
    }

    @Override
    public Optional<Response> getRoutine(long routineId) {
        //TODO
        return Optional.empty();
    }

    @Override
    public List<Response> getRoutinesByUser(long userId) {
        //TODO
        return null;
    }

    @Override
    public Response setExercise(long exerciseId, List<Set> sets) {
        //TODO
        return null;
    }

    @Override
    public Response deleteExercise(long exerciseId) {
        //TODO
        return null;
    }

    @Override
    public void deleteRoutine(long routineId) {
        //TODO
    }
}
