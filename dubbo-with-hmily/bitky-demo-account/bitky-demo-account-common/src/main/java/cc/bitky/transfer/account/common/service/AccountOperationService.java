package cc.bitky.transfer.account.common.service;

import cc.bitky.transfer.account.api.dto.AccountOperationDTO;
import cc.bitky.transfer.account.api.service.IAccountService;
import cc.bitky.transfer.account.common.repository.entity.TAccount;
import cc.bitky.transfer.account.common.repository.entity.TOrder;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liMingLiang
 * @date 2020/12/19
 */
@DubboService
@Service
public class AccountOperationService implements IAccountService {

    @Autowired
    private AccountOperationRepository repository;

    @Override
    @HmilyTCC(confirmMethod = "actionConfirm", cancelMethod = "actionCancel")
    public void accountTry(AccountOperationDTO accountOperationDTO) {
        TOrder order = buildOrderEntity(accountOperationDTO);
        TAccount account = buildAccountEntity(accountOperationDTO);
        repository.doTry(order, account);
    }

    public void actionConfirm(AccountOperationDTO accountOperationDTO) {
        repository.doConfirm(buildOrderEntity(accountOperationDTO));
    }

    public void actionCancel(AccountOperationDTO accountOperationDTO) {
        TOrder order = buildOrderEntity(accountOperationDTO);
        TAccount account = buildAccountEntity(accountOperationDTO);
        account.setAmount(-account.getAmount());
        repository.doCancel(order, account);
    }

    private TAccount buildAccountEntity(AccountOperationDTO accountOperationDTO) {
        return TAccount.builder()
                .amount(accountOperationDTO.getAmount())
                .uid(accountOperationDTO.getUid())
                .build();
    }

    private TOrder buildOrderEntity(AccountOperationDTO accountOperationDTO) {
        return TOrder.builder()
                .bizSerial(accountOperationDTO.getBizSerial())
                .uid(accountOperationDTO.getUid())
                .accountType(accountOperationDTO.getAccountType())
                .amount(accountOperationDTO.getAmount())
                .finished(false)
                .build();
    }
}
