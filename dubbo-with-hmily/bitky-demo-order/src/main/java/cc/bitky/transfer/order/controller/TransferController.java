package cc.bitky.transfer.order.controller;

import cc.bitky.transfer.account.api.dto.TransferDTO;
import cc.bitky.transfer.account.common.BitkylinConfig;
import cc.bitky.transfer.order.service.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liMingLiang
 * @date 2020/12/19
 */
@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private ITransferService transferService;

    @Resource(name = "bitkyconfig")
    private BitkylinConfig bitkylinConfig;

    @PostMapping(value = "/transfer")
    public String transfer(@RequestBody TransferDTO transferDTO) {
        transferService.transferByDubbo(transferDTO);
        return "SUCCESS";
    }
}
