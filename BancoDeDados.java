import java.sql.*;
import java.util.ArrayList;

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

    public static ArrayList<String[]> buscarTodasContas() {
        ArrayList<String[]> contas = new ArrayList<>();
        String sql = "SELECT nome, conta, saldo, tipo_conta FROM contas";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nome = rs.getString("nome");
                String conta = rs.getString("conta");
                double saldo = rs.getDouble("saldo");
                String tipoConta = rs.getString("tipo_conta");
                contas.add(new String[]{nome, conta, String.valueOf(saldo), tipoConta});
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar dados da tabela 'contas': " + e.getMessage());
            e.printStackTrace();
        }
        return contas;
    }

    public static void modificarDados(String numeroConta, String novoNome, double novoSaldo, String novoTipoConta) {
        String sql = "UPDATE contas SET nome = ?, saldo = ?, tipo_conta = ? WHERE conta = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, novoNome);
            pstmt.setDouble(2, novoSaldo);
            pstmt.setString(3, novoTipoConta);
            pstmt.setString(4, numeroConta);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dados modificados com sucesso na tabela 'contas'.");
            } else {
                System.out.println("Nenhum dado foi modificado na tabela 'contas'.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao modificar dados na tabela 'contas': " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void excluirDados(String numeroConta) {
        String sql = "DELETE FROM contas WHERE conta = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, numeroConta);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Dados excluídos com sucesso da tabela 'contas'.");
            } else {
                System.out.println("Nenhum dado foi excluído da tabela 'contas'.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir dados da tabela 'contas': " + e.getMessage());
            e.printStackTrace();
        }
    }
}
