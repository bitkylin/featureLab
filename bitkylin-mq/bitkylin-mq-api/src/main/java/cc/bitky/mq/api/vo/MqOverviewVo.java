package cc.bitky.mq.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liMingLiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MqOverviewVo {

    private List<ConsumerGroup> groupList;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Topic {
        private String name;
        private int capacity;
        private int maxIndex;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConsumerGroup {
        private String groupName;
        private Topic topic;
        private int offset;
    }
}
