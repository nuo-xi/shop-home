package com.fh;

import com.fh.service.TypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopTypeApiApplicationTests {

    @Autowired
    private TypeService typeService;

    @Test
    public  void contextLoads() {
       /* List<Map<String, Object>> mapList = typeService.queryTypeList();
        System.out.println(JSON.toJSONString(mapList));*/



    }

}
