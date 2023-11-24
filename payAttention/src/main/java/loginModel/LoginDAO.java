/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginModel;
import Maquina.CapturaMaquina;
import Registros.Captura;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Matheus
 */
public class LoginDAO extends javax.swing.JFrame{
    public boolean Login(String email, String senha) throws SQLException{
        Connection conexao = new Conexao().getConnection();
        String sql = "SELECT email, senha FROM UsuarioAluno WHERE email = '"+email+"' AND senha = '"+senha+"'";
        System.out.println("sql");
        PreparedStatement statment = conexao.prepareStatement(sql);
        ResultSet rs = statment.executeQuery();
        if (rs.next()) {
            System.out.println("Possui");
            JOptionPane.showMessageDialog(rootPane, "Entrando !!!");
            CapturaMaquina capturaMaquina = new CapturaMaquina();
            capturaMaquina.capturaMaquina();
            Captura captura = new Captura();
            captura.capturaDados();
        }else{
            System.out.println("Não Possui");
            JOptionPane.showMessageDialog(rootPane, "Email e/ou senha inválidos !!!");
        }
        conexao.close();
        return false;
    }
    
}
