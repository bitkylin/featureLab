package cc.bitky.transfer.order.service.impl;

import cc.bitky.transfer.account.api.dto.AccountActionDTO;
import cc.bitky.transfer.account.api.dto.AccountOperationDTO;
import cc.bitky.transfer.account.api.dto.TransferDTO;
import cc.bitky.transfer.account.api.service.IAccountService;
import cc.bitky.transfer.order.service.ITransferService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liMingLiang
 * @date 2020/12/19
 */
@Slf4j
@Service
public class TransferService implements ITransferService, InitializingBean {

    @DubboReference(url = "127.0.0.1:28090")
    private IAccountService accountServiceA;

    @DubboReference(url = "127.0.0.1:28091")
    private IAccountService accountServiceB;

    private Map<String, IAccountService> accountServiceMap;


    @Override
    @HmilyTCC(confirmMethod = "transferConfirm", cancelMethod = "transferCancel")
    public void transferByDubbo(TransferDTO transferDTO) {
        AccountOperationDTO accountOperationSource = buildAccountOperation(transferDTO.getBizSerial(), transferDTO.getSource());
        IAccountService sourceService = resolveService(transferDTO.getSource());

        AccountOperationDTO accountOperationTarget = buildAccountOperation(transferDTO.getBizSerial(), transferDTO.getTarget());
        IAccountService targetService = resolveService(transferDTO.getTarget());

        sourceService.accountTry(accountOperationSource);
        targetService.accountTry(accountOperationTarget);
    }

    public void transferConfirm(TransferDTO transferDTO) {
        log.info("transferConfirm : " + transferDTO.getBizSerial());
    }

    public void transferCancel(TransferDTO transferDTO) {
        log.info("transferCancel : " + transferDTO.getBizSerial());
    }

    private IAccountService resolveService(AccountActionDTO accountActionDTO) {
        return accountServiceMap.get(accountActionDTO.getServiceName());
    }

    private AccountOperationDTO buildAccountOperation(String bizSerial, AccountActionDTO accountActionDTO) {
        return AccountOperationDTO.builder()
                .bizSerial(bizSerial)
                .uid(accountActionDTO.getUid())
                .amount(accountActionDTO.getAmount())
                .accountType(accountActionDTO.getAccountType())
                .build();


    }

    @Override
    public void afterPropertiesSet() {
        accountServiceMap = new HashMap<>(16);
        accountServiceMap.put("accountServiceA", accountServiceA);
        accountServiceMap.put("accountServiceB", accountServiceB);
    }
}
