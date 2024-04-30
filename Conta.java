public abstract class Conta implements OperacoesBancarias {
    private String numeroConta;
    private double saldo;
    private Cliente cliente;

    public Conta(String numeroConta, Cliente cliente) {
        this.numeroConta = numeroConta;
        this.cliente = cliente;
        this.saldo = 0.0;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public boolean sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    public double consultarSaldo() {
        return saldo;
    }
}
