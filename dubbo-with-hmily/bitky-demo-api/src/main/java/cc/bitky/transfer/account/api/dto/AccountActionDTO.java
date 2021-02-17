package cc.bitky.transfer.account.api.dto;

import cc.bitky.transfer.account.common.constants.AccountTypeEnum;
import lombok.Data;

/**
 * @author liMingLiang
 * @date 2020/12/19
 */
@Data
public class AccountActionDTO {

    /**
     * 服务名，简单起见，直接由客户端指定依赖的服务吧
     */
    private String serviceName;

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
