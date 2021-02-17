package cc.bitky.transfer.accounta;

import cc.bitky.transfer.account.api.dto.AccountOperationDTO;
import cc.bitky.transfer.account.api.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liMingLiang
 * @date 2020/12/19
 */
@RestController
@RequestMapping("/account")
public class AccountAController {

    @Autowired
    private IAccountService accountService;

    @PostMapping(value = "/account")
    public String transfer(@RequestBody AccountOperationDTO accountOperationDTO) {
        accountService.accountTry(accountOperationDTO);
        return "SUCCESS";
    }
}
