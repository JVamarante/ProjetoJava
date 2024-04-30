public class Cliente {
    private String nome;
    private Banco banco;

    public Cliente(String nome, Banco banco) {
        this.nome = nome;
        this.banco = banco;
    }

    public Conta criarConta(String tipoConta, String numeroConta) {
        Conta conta = null;
        switch (tipoConta) {
            case "Corrente":
                conta = new ContaCorrente(numeroConta, this);
                break;
            case "Poupanca":
                conta = new ContaPoupanca(numeroConta, this);
                break;
        }
        if (conta != null) {
            banco.adicionarConta(conta);
        }
        return conta;
    }

    public String getNome() {
        return nome;
    }
}
