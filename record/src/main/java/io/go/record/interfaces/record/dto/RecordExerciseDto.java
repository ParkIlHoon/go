package io.go.record.interfaces.record.dto;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecordExerciseDto {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        /**
         * <h3>아이디</h3>
         */
        private long id;
        /**
         * <h3>루틴 아이디</h3>
         */
        private Long routineId;
        /**
         * <h3>운동 아이디</h3>
         */
        private long exerciseId;
        /**
         * <h3>순서</h3>
         */
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
        private LocalDateTime startDatetime;
        /**
         * <h3>종료 일시</h3>
         */
        private LocalDateTime endDatetime;
    }


    public static class Create {

    }
}
