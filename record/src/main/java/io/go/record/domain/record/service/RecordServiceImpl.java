package io.go.record.domain.record.service;

import io.go.record.domain.record.entity.ExerciseRecord;
import io.go.record.domain.record.entity.Record;
import io.go.record.domain.record.repository.ExerciseRecordRepository;
import io.go.record.domain.record.repository.RecordRepository;
import io.go.record.interfaces.record.dto.ExerciseRecordDto;
import io.go.record.interfaces.record.dto.RecordDto;
import io.go.record.interfaces.record.exception.ExerciseRecordNotFoundException;
import io.go.record.interfaces.record.exception.RecordNotFoundException;
import io.go.record.interfaces.record.service.RecordService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;
    private final ExerciseRecordRepository exerciseRecordRepository;

    @Override
    public RecordDto.Response createRecord(long userId) {
        Record newRecord = Record.builder()
            .userId(userId)
            .build();

        return recordRepository.save(newRecord).toDto();
    }

    @Override
    public ExerciseRecordDto.Response createExerciseRecord(long recordId, ExerciseRecordDto.Create createDto) {
        Optional<Record> optionalRecord = recordRepository.findById(recordId);
        if (optionalRecord.isEmpty()) {
            throw new RecordNotFoundException();
        }

        ExerciseRecord newExerciseRecord = ExerciseRecord.builder()
            .record(optionalRecord.get())
            .routineId(createDto.getRoutineId())
            .exerciseId(createDto.getExerciseId())
            .order(createDto.getOrder())
            .weight(createDto.getWeight())
            .count(createDto.getCount())
            .second(createDto.getSecond())
            .startDatetime(createDto.getStartDatetime())
            .endDatetime(createDto.getEndDatetime())
            .build();

        return exerciseRecordRepository.save(newExerciseRecord).toDto();
    }

    @Override
    public Optional<RecordDto.Response> getRecord(long recordId) {
        return recordRepository.findById(recordId)
            .map(Record::toDto);
    }

    @Override
    public Optional<RecordDto.Response> getRecord(long userId, LocalDate startDate) {
        return recordRepository.findByUserIdAndStartDatetimeBetween(userId, LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(startDate, LocalTime.MAX))
            .map(Record::toDto);
    }

    @Override
    public List<RecordDto.Response> getRecords(long userId, LocalDate searchFrom, LocalDate searchTo) {
        return recordRepository.findByUserIdAndStartDatetimeBetween(userId, LocalDateTime.of(searchFrom, LocalTime.MIN), LocalDateTime.of(searchTo, LocalTime.MAX))
            .stream()
            .map(Record::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public Page<RecordDto.Response> getRecords(long userId, Pageable pageable) {
        return recordRepository.findAllByUserId(userId, pageable)
            .map(Record::toDto);
    }

    @Override
    public void deleteRecord(long recordId) {
        Optional<Record> optionalRecord = recordRepository.findById(recordId);
        if (optionalRecord.isEmpty()) {
            throw new RecordNotFoundException();
        }
        recordRepository.delete(optionalRecord.get());
    }

    @Override
    public void deleteExerciseRecord(long exerciseRecordId) {
        Optional<ExerciseRecord> optionalExerciseRecord = exerciseRecordRepository.findById(exerciseRecordId);
        if (optionalExerciseRecord.isEmpty()) {
            throw new ExerciseRecordNotFoundException();
        }
        exerciseRecordRepository.delete(optionalExerciseRecord.get());
    }
}
