package io.go.routine.interfaces.routine.dto;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <h1>루틴 DTO</h1>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoutineDto {

    /**
     * <h2>생성 DTO</h2>
     */
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Create {
        /**
         * <h3>사용자 아이디</h3>
         */
        @Min(1)
        private long userId;

        /**
         * <h3>이름</h3>
         */
        @NotBlank
        private String name;

        /**
         * <h3>루틴 상세</h3>
         */
        @NotNull
        @NotEmpty
        private List<RoutineDetailDto.Create> details;
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
         * <h3>사용자 아이디</h3>
         */
        private long userId;
        /**
         * <h3>이름</h3>
         */
        private String name;
        /**
         * <h3>삭제 여부</h3>
         */
        private boolean isDeleted;
        /**
         * <h3>상세</h3>
         */
        private List<RoutineDetailDto.Response> details;
    }
}
