package cc.bitky.transfer.account.api.service;

import cc.bitky.transfer.account.api.dto.AccountOperationDTO;
import org.dromara.hmily.annotation.Hmily;

public interface IAccountService {

    @Hmily
    void accountTry(AccountOperationDTO accountOperationDTO);
}
