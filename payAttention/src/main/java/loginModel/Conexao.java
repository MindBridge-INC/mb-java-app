/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Matheus
 */
public class Conexao {

    public Conexao() {
    }
    private static final String url = "jdbc:mysql://localhost:3306/Mindbridge";
    private static final String user = "root";
    private static final String password = "1999";

    private static Connection conn;

    public static Connection getConnection() throws SQLException{
        Connection conexao = DriverManager.getConnection(url,user,password);
        return conexao;
    }
}
