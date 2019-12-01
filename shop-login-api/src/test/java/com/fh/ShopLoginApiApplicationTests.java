package com.fh;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShopLoginApiApplicationTests {

    @Test
    public void contextLoads() {
        // "{"code":200,"msg":"1","obj":"323997"}"
        String  str ="{\"code\":200,\"msg\":\"1\",\"obj\":\"323997\"}";
        boolean contains = str.contains("323997");
        if(str.contains("323997")){
            System.out.println(str);
        }else
        {
            System.out.println("bug");
        }
    }


}
