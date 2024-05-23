import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MostrarContasGUI extends JFrame {
    private final JTable contasTable;
    private final DefaultTableModel tableModel;

    public MostrarContasGUI(ArrayList<String[]> contas) {
        setTitle("Contas Cadastradas");
        setSize(600, 400);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new Object[]{"Nome", "Número da Conta", "Saldo", "Tipo de Conta"}, 0);
        contasTable = new JTable(tableModel);
        contasTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(contasTable);
        add(scrollPane, BorderLayout.CENTER);


        JPanel buttonsPanel = new JPanel();
        JButton modificarButton = new JButton("Modificar Conta");
        modificarButton.addActionListener(e -> modificarConta());
        JButton excluirButton = new JButton("Excluir Conta");
        excluirButton.addActionListener(e -> excluirConta());
        buttonsPanel.add(modificarButton);
        buttonsPanel.add(excluirButton);
        add(buttonsPanel, BorderLayout.SOUTH);

        for (String[] conta : contas) {
            tableModel.addRow(conta);
        }

        setVisible(true);
    }

    private void modificarConta() {
        int selectedRow = contasTable.getSelectedRow();
        if (selectedRow != -1) {
            String numeroConta = (String) tableModel.getValueAt(selectedRow, 1);
            String novoNome = JOptionPane.showInputDialog(this, "Novo nome:", tableModel.getValueAt(selectedRow, 0));
            String novoTipoConta = (String) JOptionPane.showInputDialog(this, "Novo tipo de conta:", "Tipo de Conta", JOptionPane.QUESTION_MESSAGE, null, new String[]{"Corrente", "Poupança"}, tableModel.getValueAt(selectedRow, 3));
            double novoSaldo = Double.parseDouble(JOptionPane.showInputDialog(this, "Novo saldo:", tableModel.getValueAt(selectedRow, 2)));
            BancoDeDados.modificarDados(numeroConta, novoNome, novoSaldo, novoTipoConta);
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Dados modificados com sucesso!");
        }
    }

    private void excluirConta() {
        int selectedRow = contasTable.getSelectedRow();
        if (selectedRow != -1) {
            String numeroConta = (String) tableModel.getValueAt(selectedRow, 1);
            BancoDeDados.excluirDados(numeroConta);
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Conta excluída com sucesso!");
        }
    }

    private void atualizarTabela() {
        ArrayList<String[]> contas = BancoDeDados.buscarTodasContas();
        tableModel.setRowCount(0);
        for (String[] conta : contas) {
            tableModel.addRow(conta);
        }
    }
}
