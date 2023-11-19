/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginModel;
import java.io.IOException;
import java.sql.SQLException;
import loginView.LoginView;
import popUpModel.popUpDAO;

/**
 *
 * @author Matheus
 */
public class LoginController {
    public void loginUsuario(LoginView view) throws SQLException, IOException{
        LoginDAO login = new LoginDAO();
        Login login1 = new Login();
        login1.setEmail(view.getjTextFieldLogin().getText());
        login.Login(view.getjTextFieldLogin().getText(), view.getjPasswordFieldSenha().getText());
    }
}
