package popUpModel;
import loginModel.Conexao;
import loginModel.Login;
import java.sql.*;
import java.time.LocalDateTime;

public class popUpDAO extends Login{
    public boolean popUpTrue() throws SQLException {
        Login login = new Login();
        String email = login.getEmail();
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        PreparedStatement ps = null;
        Connection conn = null;
        Statement stmt = null;
        System.out.println(email);
        String selectIdAluno = String.format("select id from UsuarioAluno where email = 'joaosilva@gmail.com'");
        String insertPontos = "insert into Pontuacao (pontos, dtRegistro, fkAluno) VALUES(?,?,?)";

        conn = Conexao.getConnection();
        stmt = conn.createStatement();

        try {
            ResultSet rs = stmt.executeQuery(selectIdAluno);
            if (rs.next()){
                System.out.println("Inserindo pontuação.");
                Integer fkAluno = rs.getInt(1);
                ps = Conexao.getConnection().prepareStatement(insertPontos);
                ps.setInt(1,10);
                ps.setString(2, String.valueOf(dataHoraAtual));
                ps.setInt(3, fkAluno);
                ps.execute();
            }
        }catch (SQLException ex){
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        }
        return true;
    }
    public boolean popUpFalse() throws SQLException {
        Login login = new Login();
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        String email = login.getEmail();
        PreparedStatement ps = null;
        Connection conn = null;
        Statement stmt = null;
        System.out.println(email);
        String selectIdAluno = String.format("select id from UsuarioAluno where email = 'joaosilva@gmail.com'");
        String insertPontos = "insert into Pontuacao (pontos, dtRegistro, fkAluno) VALUES(?,?,?)";

        conn = Conexao.getConnection();
        stmt = conn.createStatement();

        try {
            ResultSet rs = stmt.executeQuery(selectIdAluno);
            if (rs.next()){
                System.out.println("Inserindo pontuação.");
                Integer fkAluno = rs.getInt(1);
                ps = Conexao.getConnection().prepareStatement(insertPontos);
                ps.setInt(1,0);
                ps.setString(2, String.valueOf(dataHoraAtual));
                ps.setInt(3, fkAluno);
                ps.execute();
            }
        }catch (SQLException ex){
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        }
        return false;
    }
}
