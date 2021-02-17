package cc.bitky.mq.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liMingLiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResp<T> {

    /**
     * 是否成功拉取消息
     */
    private Boolean pullSuccess;

    /**
     * 消息
     */
    private T message;

    /**
     * 消息的索引
     */
    private Integer index;
}
