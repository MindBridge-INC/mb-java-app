package Main;
import Conexao.Conexao;
import loginView.LoginView;
import java.sql.SQLException;
public class App extends javax.swing.JFrame{
    public static void main(String[] args) throws SQLException {
        /*LoginView lv = new LoginView();
        lv.setVisible(true);*/

        Conexao conexao = new Conexao();
        conexao.getConexao();
    }
}
