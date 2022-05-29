package io.go.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoResponseDto<T> {

    private int resultCode = 0;
    private String resultMessage;
    private T result;

    public static <T> GoResponseDto<T> ok(T result) {
        return GoResponseDto.<T>builder()
            .resultCode(0)
            .result(result)
            .build();
    }
}
