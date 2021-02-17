package cc.bitky.transfer.account.api.dto;

import lombok.Data;

/**
 * @author liMingLiang
 * @date 2020/12/19
 */
@Data
public class TransferDTO {

    /**
     * 交易流水号
     */
    private String bizSerial;

    /**
     * 源账户
     */
    private AccountActionDTO source;

    /**
     * 目标账户
     */
    private AccountActionDTO target;

}
