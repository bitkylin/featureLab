package cc.bitky.testky.testbitkylin.controller;

import cc.bitky.testky.testbitkylin.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author limingliang
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping(value = "/one")
    public String one() {
        return testService.one();
    }

    @GetMapping(value = "/two")
    public String two() {
        return testService.two();
    }

}
