package cc.bitky.demo.featurelab.controller;

import cc.bitky.demo.featurelab.service.springlab.AutoConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liMingLiang
 * @date 2019/9/14
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private AutoConfigService autoConfigService;

    @GetMapping("/env")
    public Object configEnv() {
        return autoConfigService.getPropertyBindConfig();
    }

    @GetMapping("/ext")
    public Object configExt() {
        return autoConfigService.getPropertyBindExternalConfig();
    }

    @GetMapping("/environment")
    public Object configEnvironment() {
        return autoConfigService.getEnvironment();
    }
}
