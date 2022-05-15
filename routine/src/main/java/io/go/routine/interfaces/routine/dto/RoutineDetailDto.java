package io.go.routine.interfaces.routine.dto;

import io.go.routine.domain.routine.vo.Set;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <h1>루틴 상세 DTO</h1>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoutineDetailDto {

    /**
     * <h2>생성 DTO</h2>
     */
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Create {
        /**
         * <h3>운동 아이디</h3>
         */
        @Min(1)
        private long exerciseId;

        /**
         * <h3>세트 정보</h3>
         */
        @NotNull
        @NotEmpty
        private List<Set> sets;
    }

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
         * <h3>운동 아이디</h3>
         */
        private long exerciseId;
        /**
         * <h3>세트 정보</h3>
         */
        private List<Set> sets;
    }
}
