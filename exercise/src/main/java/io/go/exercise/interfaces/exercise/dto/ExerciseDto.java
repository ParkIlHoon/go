package io.go.exercise.interfaces.exercise.dto;

import io.go.exercise.domain.exercise.enums.MovementPartType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExerciseDto {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {

        private long id;

        private String name;

        private MovementPartType movementPart;

    }
}
