package io.go.routine.domain.routine.vo;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode(of = {"order"})
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Set implements Serializable {
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
     * <h3>시간</h3>
     */
    private int second;
}
