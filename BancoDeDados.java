import java.sql.*;

public class BancoDeDados {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mariadb://localhost/clientedb";
        String usuario = "root";
        String senha = "123";
        return DriverManager.getConnection(url, usuario, senha);
    }

    public static void inserirDados(String nome, String numeroConta, double saldo) {
        String sql = "INSERT INTO contas (nome, conta, saldo) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            pstmt.setString(2, numeroConta);
            pstmt.setDouble(3, saldo);

            pstmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso na tabela 'contas'.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
