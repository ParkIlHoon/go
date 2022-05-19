package io.go.routine.domain.routine.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.go.routine.domain.routine.vo.Set;
import java.util.List;
import javax.persistence.AttributeConverter;
import lombok.extern.slf4j.Slf4j;

/**
 * <h1>세트 정보 JSON String Converter</h1>
 */
@Slf4j
public class SetJsonConverter implements AttributeConverter<List<Set>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Set> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            log.error("JSON 파싱 중 오류 발생 : {}", attribute, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Set> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<List<Set>>() {});
        } catch (JsonProcessingException e) {
            log.error("JSON 파싱 중 오류 발생 : {}", dbData, e);
            throw new RuntimeException(e);
        }
    }
}
