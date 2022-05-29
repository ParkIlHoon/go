package io.go.exercise.domain.exercise.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MovementPartType {

    CHEST("가슴", "bg-red-lt"),
    SHOULDER("어깨", "bg-green-lt"),
    BACK("등", "bg-blue-lt"),
    ABS("복근", "bg-teal-lt"),
    ARM("팔", "bg-yellow-lt"),
    LOWER_BODY("하체", "bg-orange-lt"),
    HIP("엉덩이", "bg-purple-lt"),
    WHOLE_BODY("전신", "bg-cyan-lt"),
    AEROBIC("유산소", "bg-pink-lt");


    private String title;
    private String className;
}
