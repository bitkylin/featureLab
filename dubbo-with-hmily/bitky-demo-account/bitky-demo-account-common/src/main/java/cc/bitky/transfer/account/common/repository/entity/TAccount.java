package cc.bitky.transfer.account.common.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liMingLiang
 * @date 2020/12/19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TAccount {

    /**
     * 用户Id
     */
    private String uid;

    /**
     * 金额增加值
     */
    private Long amount;

    /**
     * 乐观锁
     */
    private Integer edition;

}
