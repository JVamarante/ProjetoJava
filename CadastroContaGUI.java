import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CadastroContaGUI extends JFrame implements ActionListener {
    private final JTextField nomeField;
    private final JTextField numeroContaField;
    private final JTextField saldoField;
    private final JComboBox<String> tipoContaCombo;
    private final JButton salvarButton;
    private final JButton mostrarContasButton;

    public CadastroContaGUI() {
        setTitle("Cadastro de Conta");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField(20);

        JLabel tipoContaLabel = new JLabel("Tipo de Conta:");
        String[] tiposConta = {"Corrente", "Poupança"};
        tipoContaCombo = new JComboBox<>(tiposConta);

        JLabel numeroContaLabel = new JLabel("Número da Conta:");
        numeroContaField = new JTextField(20);

        JLabel saldoLabel = new JLabel("Saldo Inicial:");
        saldoField = new JTextField(20);

        salvarButton = new JButton("Criar conta");
        salvarButton.addActionListener(this);

        mostrarContasButton = new JButton("Mostrar Contas");
        mostrarContasButton.addActionListener(this);

        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(tipoContaLabel);
        panel.add(tipoContaCombo);
        panel.add(numeroContaLabel);
        panel.add(numeroContaField);
        panel.add(saldoLabel);
        panel.add(saldoField);
        panel.add(salvarButton);
        panel.add(mostrarContasButton);

        add(panel, BorderLayout.NORTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == salvarButton) {
            String nome = nomeField.getText();
            String tipoConta = (String) tipoContaCombo.getSelectedItem();
            String numeroConta = numeroContaField.getText();
            double saldo = Double.parseDouble(saldoField.getText());

            BancoDeDados.inserirDados(nome, numeroConta, saldo, tipoConta);

            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");

            nomeField.setText("");
            numeroContaField.setText("");
            saldoField.setText("");
        } else if (e.getSource() == mostrarContasButton) {
            mostrarContas();
        }
    }

    private void mostrarContas() {
        ArrayList<String[]> contas = BancoDeDados.buscarTodasContas();
        new MostrarContasGUI(contas);
    }

    public static void main(String[] args) {
        new CadastroContaGUI();
    }
}
