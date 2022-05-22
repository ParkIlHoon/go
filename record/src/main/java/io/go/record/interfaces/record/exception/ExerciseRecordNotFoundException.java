package io.go.record.interfaces.record.exception;

/**
 * <h1>기록된 운동 존재하지 않음 예외</h1>
 */
public class ExerciseRecordNotFoundException extends RuntimeException{

    public ExerciseRecordNotFoundException() {
    }

    public ExerciseRecordNotFoundException(String message) {
        super(message);
    }
}
