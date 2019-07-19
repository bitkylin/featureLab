package cc.bitky.demo.featurelab.controller;

import cc.bitky.demo.featurelab.tools.modelmapper.bo.input.ModelMapperInput;
import cc.bitky.demo.featurelab.tools.modelmapper.bo.req.ModelMapperReq;
import com.alibaba.fastjson.JSON;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

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
    public String aopLock(@Valid @RequestBody ModelMapperReq req) {
        ModelMapperInput input = modelMapper.map(req, ModelMapperInput.class);
        return JSON.toJSONString(input);
    }
}
