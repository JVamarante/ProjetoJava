import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Banco banco = new Banco();

        String continuar = "s";
        while (continuar.equalsIgnoreCase("s")) {
            System.out.println("Digite o nome do cliente:");
            String nome = scanner.nextLine();
            Cliente cliente = new Cliente(nome, banco);

            String continuarConta = "s";
            while (continuarConta.equalsIgnoreCase("s")) {
                System.out.println("Digite o tipo de conta (Corrente, Poupanca):");
                String tipoConta = scanner.nextLine();

                System.out.println("Digite o número da conta:");
                String numeroConta = scanner.nextLine();

                Conta conta = cliente.criarConta(tipoConta, numeroConta);

                System.out.println("Digite o valor para depositar:");
                double valorDeposito = Double.parseDouble(scanner.nextLine());
                conta.depositar(valorDeposito);
                System.out.println("Saldo após depósito: " + conta.consultarSaldo());

                System.out.println("Deseja sacar algum valor da conta? (s/n)");
                String sacar = scanner.nextLine();
                if (sacar.equalsIgnoreCase("s")) {
                    System.out.println("Digite o valor para sacar:");
                    double valorSaque = Double.parseDouble(scanner.nextLine());
                    if (conta.sacar(valorSaque)) {
                        System.out.println("Saque realizado com sucesso.");
                    } else {
                        System.out.println("Saldo insuficiente para saque.");
                    }
                    System.out.println("Saldo após saque: " + conta.consultarSaldo());
                }

                BancoDeDados.inserirDados(nome, conta.getNumeroConta(), conta.getSaldo());

                System.out.println("Deseja adicionar outra conta para o mesmo cliente? (s/n)");
                continuarConta = scanner.nextLine();
            }

            System.out.println("Deseja adicionar outro cliente? (s/n)");
            continuar = scanner.nextLine();
        }

        System.out.println("Contas criadas");
        //for (Conta conta : banco.getContas()) {
           // System.out.println("Número da conta: " + conta.getNumeroConta() + ", Saldo: " + conta.getSaldo() + ", Cliente: " + conta.getCliente().getNome());
        }

        //scanner.close();
    }
//}
