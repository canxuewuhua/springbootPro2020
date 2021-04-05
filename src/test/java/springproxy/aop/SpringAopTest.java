package springproxy.aop;

import com.example.demo.SpringBootWildApplication;
import com.example.demo.springproxy.aop.AopPersonServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@Slf4j
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SpringBootWildApplication.class)
public class SpringAopTest {

    /**
     *
     说明：如果目标类没有实现接口，spring容器会采用cglib的方式产生代理对象，如果实现了接口，则会采用jdk的动态代理产生代理对象
     使用cglib生成的代理对象是目标对象的子类
     */
    @Autowired
    private AopPersonServiceImpl aopPersonServiceImpl;

    @Test
    public void test(){
        String returnValue = aopPersonServiceImpl.savePerson();
        System.out.println(returnValue);

    }

}
