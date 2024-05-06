public class Cliente {
    private String nome;
    private Banco banco;

    public Cliente(String nome, Banco banco) {
        this.nome = nome;
        this.banco = banco;
    }

    public Conta criarConta(String tipoConta, String numeroConta) {
        Conta conta = switch (tipoConta) {
            case "corrente" -> new ContaCorrente(numeroConta, this);
            case "poupanca" -> new ContaPoupanca(numeroConta, this);
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
