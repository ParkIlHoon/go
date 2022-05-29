package io.go.record.interfaces.record.service;

import io.go.record.interfaces.record.dto.RecordDto;
import io.go.record.interfaces.record.dto.ExerciseRecordDto;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

/**
 * <h1>기록 서비스</h1>
 */
@Validated
public interface RecordService {

    /**
     * 새로운 기록을 생성합니다.
     *
     * @param userId 사용자 아이디
     * @return 생성된 기록 정보
     */
    RecordDto.Response createRecord(long userId);

    /**
     * 새로운 운동 기록을 생성합니다.
     *
     * @param recordId  기록 아이디
     * @param createDto 생성할 운동 기록 정보
     * @return 생성된 운동 기록 정보
     */
    ExerciseRecordDto.Response createExerciseRecord(long recordId, ExerciseRecordDto.Create createDto);

    /**
     * 기록을 조회합니다.
     *
     * @param recordId 기록 아이디
     * @return 아이디에 해당하는 기록
     */
    Optional<RecordDto.Response> getRecord(long recordId);

    /**
     * 기록을 조회합니다.
     *
     * @param userId    사용자 아이디
     * @param startDate 시작일
     * @return 사용자 아이디와 시작일에 해당하는 기록
     */
    Optional<RecordDto.Response> getRecord(long userId, LocalDate startDate);

    /**
     * 기록을 조회합니다.
     *
     * @param userId     사용자 아이디
     * @param searchFrom 검색 시작일
     * @param searchTo   검색 종료일
     * @return 사용자 아이디외 검색 기간에 해당하는 기록 목록
     */
    List<RecordDto.Response> getRecords(long userId, LocalDate searchFrom, LocalDate searchTo);

    /**
     * 기록을 조회합니다.
     *
     * @param userId   사용자 아이디
     * @param pageable 페이징 정보
     * @return 사용자 아이디에 해당하는 기록 목록
     */
    Page<RecordDto.Response> getRecords(long userId, Pageable pageable);

    /**
     * 기록을 삭제합니다.
     *
     * @param recordId 삭제할 기록 아이디
     */
    void deleteRecord(long recordId);

    /**
     * 운동 기록을 삭제합니다.
     *
     * @param exerciseRecordId 삭제할 운동 기록 아이디
     */
    void deleteExerciseRecord(long exerciseRecordId);
}
