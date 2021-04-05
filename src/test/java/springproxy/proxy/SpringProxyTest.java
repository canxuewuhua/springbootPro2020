package springproxy.proxy;

import com.example.demo.SpringBootWildApplication;
import com.example.demo.springproxy.proxy.PersonServiceProxy;
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
public class SpringProxyTest {

    @Autowired
    private PersonServiceProxy personServiceProxy;

    @Test
    public void test(){
        personServiceProxy.savePerson();
    }

}
