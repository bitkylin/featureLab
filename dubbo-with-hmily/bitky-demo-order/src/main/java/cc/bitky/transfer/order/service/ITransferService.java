package cc.bitky.transfer.order.service;

import cc.bitky.transfer.account.api.dto.TransferDTO;

/**
 * @author liMingLiang
 * @date 2020/12/19
 */
public interface ITransferService {
    void transferByDubbo(TransferDTO transferDTO);
}
