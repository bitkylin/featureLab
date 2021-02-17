package cc.bitky.transfer.account.api.dto;

import cc.bitky.transfer.account.common.constants.AccountTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liMingLiang
 * @date 2020/12/19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountOperationDTO implements Serializable {
    private static final long serialVersionUID = 577520L;
    /**
     * 交易流水号
     */
    private String bizSerial;

    /**
     * 用户Id
     */
    private String uid;

    /**
     * 账户类型
     */
    private AccountTypeEnum accountType;

    /**
     * 金额增加值
     */
    private Long amount;

}
