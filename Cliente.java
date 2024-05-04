public class Cliente {
    private String nome;
    private Banco banco;

    public Cliente(String nome, Banco banco) {
        this.nome = nome;
        this.banco = banco;
    }

    public Conta criarConta(String tipoConta, String numeroConta) {
        Conta conta = switch (tipoConta) {
            case "Corrente" -> new ContaCorrente(numeroConta, this);
            case "Poupanca" -> new ContaPoupanca(numeroConta, this);
            default -> null;
        };
        if (conta != null) {
            banco.adicionarConta(conta);
        }
        return conta;
    }

    public String getNome() {
        return nome;
    }
}
