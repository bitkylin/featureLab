package cc.bitky.transfer.account.common.repository.entity;

import cc.bitky.transfer.account.common.constants.AccountTypeEnum;
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
public class TOrder {

    /**
     * 用户Id
     */
    private String uid;
    /**
     * 交易流水号
     */
    private String bizSerial;
    /**
     * 账户类型
     */
    private AccountTypeEnum accountType;
    /**
     * 金额增加值
     */
    private Long amount;

    /**
     * 乐观锁
     */
    private Integer edition;
    /**
     * 是否结束
     */
    private Boolean finished;
}
