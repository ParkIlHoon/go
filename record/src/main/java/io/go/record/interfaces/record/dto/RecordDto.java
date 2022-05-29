package io.go.record.interfaces.record.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RecordDto {

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
         * <h3>시작 일시</h3>
         */
        private LocalDateTime startDatetime;
        /**
         * <h3>종료 일시</h3>
         */
        private LocalDateTime endDatetime;
        /**
         * <h3>기록된 운동 리스트</h3>
         */
        private List<ExerciseRecordDto.Response> exerciseRecords;
    }
}
