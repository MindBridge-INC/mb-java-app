package Log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

    public void exibirLog(String s) {

        // criando um objeto Logger com o nome "MyLog".
        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh = null;

        try {
            // esta redirecionando o texto para a pasta
            fh = new FileHandler("C", true);
            logger.addHandler(fh);
            // formatando a mensagem
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);


        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fh != null){
                fh.close();
            }
        }

    }
}
