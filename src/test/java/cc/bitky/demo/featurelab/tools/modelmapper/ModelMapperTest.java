package cc.bitky.demo.featurelab.tools.modelmapper;

import cc.bitky.demo.featurelab.constants.CategoryEnum;
import cc.bitky.demo.featurelab.constants.StatusEnum;
import cc.bitky.demo.featurelab.constants.TestStatusEnum;
import cc.bitky.demo.featurelab.constants.WorkType;
import cc.bitky.demo.featurelab.dto.bo.Work;
import cc.bitky.demo.featurelab.dto.input.ModelMapperInput;
import cc.bitky.demo.featurelab.dto.req.ModelMapperReq;
import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModelMapperTest {

    private static final Instant instant = Instant.now();
    private static final Date date = Date.from(instant);


    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void contextLoads() {
        ModelMapperReq req = ModelMapperReq.builder()
                .category(CategoryEnum.BOOK)
                .expireDate(date)
                .expireInstant(instant)
                .name("李明亮")
                .price(520)
                .status1(StatusEnum.PROCESSING.name())
                .status2(TestStatusEnum.PROCESS)
                .extend(buildJson())
                .work(buildWork())
                .build();
        ModelMapperInput input = modelMapper.map(req, ModelMapperInput.class);
        modelMapper.validate();
        Assert.assertEquals(CategoryEnum.BOOK.name(), input.getCategory());
        Assert.assertSame(date, input.getExpireDate());
        Assert.assertSame(instant, input.getExpireInstant());
        Assert.assertEquals("李明亮", input.getName());
        Assert.assertEquals(520, input.getPrice().intValue());
        Assert.assertEquals(StatusEnum.PROCESSING, input.getStatus1());
        Assert.assertNull(input.getStatus2());
        Assert.assertEquals(req.getExtend().toJSONString(), input.getExtend());
        Assert.assertSame(req.getWork(), input.getWork());
    }

    private JSONObject buildJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("keyString", "value");
        jsonObject.put("keyInteger", 18);
        jsonObject.put("work", buildWork());
        return jsonObject;
    }

    private Work buildWork() {
        return Work.builder()
                .desc("描述")
                .statusEnum(StatusEnum.FAILURE)
                .time(date)
                .type(WorkType.ENGINEER)
                .build();
    }

}
