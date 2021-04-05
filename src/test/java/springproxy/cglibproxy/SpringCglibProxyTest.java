package springproxy.cglibproxy;

import com.example.demo.SpringBootWildApplication;
import com.example.demo.springproxy.proxycglib.CglibPersonService;
import com.example.demo.springproxy.proxycglib.CglibPersonServiceImpl;
import com.example.demo.springproxy.proxycglib.MyTransactionCglib;
import com.example.demo.springproxy.proxycglib.PersonServiceInterceptorCglib;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@Slf4j
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SpringBootWildApplication.class)
public class SpringCglibProxyTest {

    @Test
    public void test(){
        Object target = new CglibPersonServiceImpl();
        MyTransactionCglib myTransaction = new MyTransactionCglib();
        PersonServiceInterceptorCglib interceptor = new PersonServiceInterceptorCglib(target, myTransaction);
        CglibPersonService personService =(CglibPersonService) interceptor.createProxy();
        String returnValue = (String)personService.savePerson();
        System.out.println(returnValue);
    }

}
