package springproxy.jdkproxy;

import com.example.demo.SpringBootWildApplication;
import com.example.demo.springproxy.proxyjdk.MyTransaction;
import com.example.demo.springproxy.proxyjdk.JdkPersonService;
import com.example.demo.springproxy.proxyjdk.JdkPersonServiceImpl;
import com.example.demo.springproxy.proxyjdk.PersonServiceInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.lang.reflect.Proxy;

@Slf4j
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SpringBootWildApplication.class)
public class SpringJdkProxyTest {

    @Test
    public void testjdkProxy(){
        Object target = new JdkPersonServiceImpl();
        MyTransaction myTransaction = new MyTransaction();
        PersonServiceInterceptor interceptor = new PersonServiceInterceptor(target, myTransaction);
        JdkPersonService personService = (JdkPersonService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),interceptor);
        String returnValue = (String)personService.savePerson();
        System.out.println(returnValue);
    }

}
