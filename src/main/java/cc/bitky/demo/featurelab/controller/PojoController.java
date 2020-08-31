package cc.bitky.demo.featurelab.controller;

import cc.bitky.demo.featurelab.bo.person.Student;
import com.alibaba.fastjson.JSON;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liMingLiang
 * @date 2019/9/14
 */
@RestController
@RequestMapping("/pojo")
public class PojoController {

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/")
    public void pojo() {
        Student person1 = new Student("lml1", 18);
        Student person2 = new Student("lml2", 28);

        String test = null;
//        test = JSON.toJSONString(a);
        test = JSON.toJSONString(test);


        Student student1 = modelMapper.map(person1, Student.class);
        Student student2 = modelMapper.map(person2, Student.class);

        student1.setAge(22);
        student1.setName("李明亮");

        System.out.println(JSON.toJSONString(student2));


        Student person3 = new Student("lml3", 38);
        Student person4 = new Student("lml4", 48);

        Map<String, Integer> map1 = Stream.of(person3, person4)
            .collect(Collectors.toMap(Student::getName, Student::getAge));

        Map<String, Integer> map2 = Stream.of(person3, person4)
            .collect(Collectors.toMap(
                Student::getName,
                Student::getAge,
                (u, v) -> {
                    throw new IllegalStateException(String.format("Duplicate key %s", u));
                },
                () -> new HashMap<>(16)
            ));

        System.out.println(map1);
        System.out.println(map2);

    }
}
