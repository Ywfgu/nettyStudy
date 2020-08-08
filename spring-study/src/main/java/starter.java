import org.springframework.context.support.ClassPathXmlApplicationContext;

public class starter {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
    }
}
