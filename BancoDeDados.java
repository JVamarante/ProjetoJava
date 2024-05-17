import java.sql.*;

public class BancoDeDados {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mariadb://localhost/clientedb";
        String usuario = "root";
        String senha = "123";
        return DriverManager.getConnection(url, usuario, senha);
    }

    public static void inserirDados(String nome, String numeroConta, double saldo, String tipoConta) {
        String sql = "INSERT INTO contas (nome, conta, saldo, tipo_conta) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            pstmt.setString(2, numeroConta);
            pstmt.setDouble(3, saldo);
            pstmt.setString(4, tipoConta);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dados inseridos com sucesso na tabela 'contas'.");
            } else {
                System.out.println("Nenhum dado foi inserido na tabela 'contas'.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados na tabela 'contas': " + e.getMessage());
            e.printStackTrace();
        }
    }
}