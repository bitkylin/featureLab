package cc.bitky.demo.featurelab.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author liMingLiang
 * @date 2019-05-03
 */
@RestController
@RequestMapping("/aop")
public class AopController {

    private final ModelMapper modelMapper;

    public AopController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostMapping("/lock")
    public String aopLock(@Valid @RequestBody Object req) {
        return null;
    }
}
