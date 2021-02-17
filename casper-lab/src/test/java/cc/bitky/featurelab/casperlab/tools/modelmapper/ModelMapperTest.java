package cc.bitky.featurelab.casperlab.tools.modelmapper;

import cc.bitky.featurelab.casperlab.constants.CategoryEnum;
import cc.bitky.featurelab.casperlab.constants.StatusEnum;
import cc.bitky.featurelab.casperlab.constants.TestStatusEnum;
import cc.bitky.featurelab.casperlab.constants.WorkType;
import cc.bitky.featurelab.casperlab.tools.modelmapper.bo.Work;
import cc.bitky.featurelab.casperlab.tools.modelmapper.input.ModelMapperInput;
import cc.bitky.featurelab.casperlab.tools.modelmapper.req.ModelMapperReq;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.Date;

@ExtendWith(SpringExtension.class)
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
        Assertions.assertEquals(CategoryEnum.BOOK.name(), input.getCategory());
        Assertions.assertSame(date, input.getExpireDate());
        Assertions.assertSame(instant, input.getExpireInstant());
        Assertions.assertEquals("李明亮", input.getName());
        Assertions.assertEquals(520, input.getPrice().intValue());
        Assertions.assertEquals(StatusEnum.PROCESSING, input.getStatus1());
        Assertions.assertNull(input.getStatus2());
        Assertions.assertEquals(req.getExtend().toJSONString(), input.getExtend());
        Assertions.assertSame(req.getWork(), input.getWork());
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
