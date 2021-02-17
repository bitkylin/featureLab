package cc.bitky.transfer.accountb;

import cc.bitky.transfer.account.api.dto.AccountOperationDTO;
import cc.bitky.transfer.account.api.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liMingLiang
 * @date 2020/12/19
 */
@RestController
@RequestMapping("/account")
public class AccountBController {

    @Autowired
    private IAccountService accountService;

    @PostMapping(value = "/account")
    public String transfer(@RequestBody AccountOperationDTO accountOperationDTO) {
        accountService.accountTry(accountOperationDTO);
        return "SUCCESS";
    }
}
