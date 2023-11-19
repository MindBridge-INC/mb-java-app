package popUpModel;
import loginModel.Conexao;
import loginModel.Login;
import java.sql.*;
public class popUpDAO extends Login{
    public boolean popUp() throws SQLException {
        Login login = new Login();
        String email = login.getEmail();
        PreparedStatement ps = null;
        Connection conn = null;
        Statement stmt = null;
        System.out.println(email);
        String selectIdAluno = String.format("select id from UsuarioAluno where email = 'joaosilva@gmail.com'");
        String insertPontos = "insert into Pontuacao (pontos, fkAluno) VALUES(?,?)";

        conn = Conexao.getConnection();
        stmt = conn.createStatement();

        try {
            ResultSet rs = stmt.executeQuery(selectIdAluno);
            if (rs.next()){
                System.out.println("Inserindo pontuação.");
                Integer fkAluno = rs.getInt(1);
                ps = Conexao.getConnection().prepareStatement(insertPontos);
                ps.setInt(1,10);
                ps.setInt(2, fkAluno);
                ps.execute();
            }
        }catch (SQLException ex){
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        }
        return false;
    }
}
