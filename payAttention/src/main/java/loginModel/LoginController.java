/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginModel;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import loginView.LoginView;

/**
 *
 * @author Matheus
 */
public class LoginController {
    
    public void loginUsuario(LoginView view) throws SQLException, IOException{
        Connection conexao = new Conexao().getConnection();
        LoginDAO login = new LoginDAO();
        login.Login(view.getjTextFieldLogin().getText(), view.getjPasswordFieldSenha().getText());
    }
}
