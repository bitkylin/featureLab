package cc.bitky.transfer.account.common.service;

import cc.bitky.transfer.account.common.repository.TAccountMapper;
import cc.bitky.transfer.account.common.repository.TOrderMapper;
import cc.bitky.transfer.account.common.repository.entity.TAccount;
import cc.bitky.transfer.account.common.repository.entity.TOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liMingLiang
 * @date 2020/12/19
 */
@Repository
public class AccountOperationRepository {

    @Autowired
    private TAccountMapper accountMapper;

    @Autowired
    private TOrderMapper orderMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    public void doTry(TOrder orderReq, TAccount accountReq) {
        TAccount tAccount = accountMapper.selectByUid(calcAccountTableName(orderReq), accountReq.getUid()).get(0);
        if (tAccount.getAmount() + accountReq.getAmount() < 0) {
            throw new RuntimeException("账户余额不能小于0");
        }
        orderMapper.insert(orderReq);
        accountMapper.updateAmount(calcAccountTableName(orderReq), accountReq);
    }

    public void doConfirm(TOrder order) {
        orderMapper.updateStatus(true, order.getBizSerial());
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void doCancel(TOrder order, TAccount account) {
        orderMapper.updateStatus(true, order.getBizSerial());
        accountMapper.updateAmount(calcAccountTableName(order), account);
    }

    private String calcAccountTableName(TOrder tOrder) {
        switch (tOrder.getAccountType()) {
            case RMB:
                return "t_rmb_account";
            case USD:
                return "t_usd_account";
            default:
                throw new RuntimeException();
        }
    }
}
