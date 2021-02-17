package cc.bitky.mq.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author liMingLiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessagePullReq {

    /**
     * 消费者组名
     */
    @NotBlank(message = "groupName 不能为空")
    private String groupName;

    /**
     * topic名
     */
    @NotBlank(message = "topicName 不能为空")
    private String topicName;
}
