package Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Log2 {
    public class LogbackExample {

        public static final Logger logger = LoggerFactory.getLogger(LogbackExample.class);

        public static void main(String[] args) {
            logger.info("Hello Logback!");
        }
    }
}
