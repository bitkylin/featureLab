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
public class MessageOfferReq {

    /**
     * 消息
     */
    @NotBlank(message = "message 不能为空")
    private String message;

    /**
     * topic名
     */
    @NotBlank(message = "topicName 不能为空")
    private String topicName;
}
