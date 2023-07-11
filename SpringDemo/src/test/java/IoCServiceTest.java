import com.IoCService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IoCServiceTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application-ioc.xml");
        IoCService ioCService = applicationContext.getBean(IoCService.class);
        System.out.println(ioCService.hello());
    }
}
