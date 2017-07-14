package file.poller.sample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by fatma on 08/07/2017.
 */

public class MainClass {
    public static void main(String... args){
        ApplicationContext context = new ClassPathXmlApplicationContext("file-poller-context.xml");
    }
}
