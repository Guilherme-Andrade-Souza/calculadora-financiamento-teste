package util; 
// Define que esta classe faz parte do pacote 'util', usada para funcionalidades auxiliares do sistema.

import java.util.InputMismatchException;
import java.util.Scanner;
// Importa classes necessárias para leitura de dados e tratamento de exceções.

public class InterfaceUsuario {
    // Scanner para capturar a entrada do usuário via console
    private final Scanner scanner = new Scanner(System.in);
    
    // Instância da classe ValidaDado, usada para validar os dados digitados pelo usuário
    private final ValidaDado validaDado = new ValidaDado();

    // Método que solicita e valida o valor do imóvel
    public double valorImovel() {
        while (true) {
            System.out.print("\nInsira o valor do imóvel: ");

            // Lê a entrada como String e troca vírgulas por pontos (para aceitar 0,1 e 0.1)
            String entrada = scanner.next().replace(",", ".");

            try {
                // Converte a String para double
                double valor = Double.parseDouble(entrada);

                // Verifica se o valor é válido através da classe ValidaDado
                if (validaDado.validaValorImovel(valor)) {
                    return valor; // Retorna o valor se for válido
                } else {
                    System.out.println("Valor inválido. O valor deve ser positivo.");
                }
            } catch (NumberFormatException e) {
                // Caso o valor digitado não seja numérico
                System.out.println("Entrada inválida. Use apenas números, ex: 250000.00 ou 250000,00");
            }
        }
    }

    // Método que solicita e valida o prazo do financiamento (em anos)
    public int prazoFinanciamentoAnos() {
        while (true) {
            System.out.print("\nInsira o prazo de financiamento em anos: ");
            try {
                // Lê a entrada como inteiro
                int prazo = scanner.nextInt();

                // Valida o prazo com base nas regras da classe ValidaDado
                if (validaDado.validaTempoFinanciamento(prazo)) {
                    return prazo;
                } else {
                    System.out.println("Prazo inválido. Insira um valor dentro dos limites permitidos.");
                }
            } catch (InputMismatchException e) {
                // Caso o usuário digite algo que não seja inteiro
                System.out.println("Entrada inválida. Digite um número inteiro, por exemplo: 15");
                scanner.next(); // Limpa o buffer da entrada inválida
            }
        }
    }

    // Método que solicita e valida a taxa de juros anual
    public double taxaJurosAnual() {
        while (true) {
            System.out.print("\nInsira a taxa de juros anual (ex: 0,08 ou 0.08 para 8%): ");
            String entrada = scanner.next().replace(",", ".");

            try {
                // Converte a taxa de String para double
                double taxa = Double.parseDouble(entrada);

                // Valida a taxa com base em critérios definidos em ValidaDado
                if (validaDado.validaTaxa(taxa)) {
                    return taxa;
                } else {
                    System.out.println("Taxa inválida. Deve estar dentro de uma faixa aceitável.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número decimal, como 0.08 ou 0,08.");
            }
        }
    }

    public int quantidadeVagasGaragem(){

        while (true) {
            System.out.print("\nInsira a quantidade de vagas disponíveis: ");
            int vagas = scanner.nextInt();

            try {
                if (vagas >= 0) {
                    return vagas;
                } else {
                    System.out.println("Quantidade inválida. O valor deve ser zero ou positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro, por exemplo: 1, 2, 3...");
            }
        }       
    }

    public int andarApartamento(){
        while (true) {
            System.out.print("\nInsira o andar do apartamento: ");
            int andar = scanner.nextInt();

            try {
                if (andar >= 0) {
                    return andar;
                } else {
                    System.out.println("Andar inválido. O valor deve ser zero ou positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro, por exemplo: 0, 1, 2...");
            }
    }
    }



    // Método que solicita e valida a área construída do imóvel
    public double tamanhoAreaConstruida() {
        while (true) {
            System.out.print("\nInsira a área construída (em m²): ");
            String entrada = scanner.next().replace(",", ".");

            try {
                double area = Double.parseDouble(entrada);

                if (area > 0) {
                    return area;
                } else {
                    System.out.println("Área inválida. O valor deve ser positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número decimal, por exemplo: 150.5 ou 150,5");
            }
        }
    }

    // Método que solicita e valida o tamanho total do terreno
    public double tamanhoTerreno() {
        while (true) {
            System.out.print("\nInsira o tamanho total do terreno (em m²): ");
            String entrada = scanner.next().replace(",", ".");

            try {
                double terreno = Double.parseDouble(entrada);

                if (terreno > 0) {
                    return terreno;
                } else {
                    System.out.println("Tamanho inválido. O valor deve ser positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número decimal, como 300.0 ou 300,0");
            }
        }
    }

    public String zonaTerreno() {
        while (true) {
            try {
                System.out.print("\nInforme o tipo da zona do terreno (residencial, comercial, industrial ou mista): ");
                String entrada = scanner.nextLine().trim().toLowerCase();

                switch (entrada) {
                    case "residencial":
                    case "comercial":
                    case "industrial":
                    case "mista":
                        return entrada;

                    default:
                        System.out.println("Tipo inválido. Digite uma das opções válidas: residencial, comercial, industrial ou mista.");
                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro ao ler a entrada. Tente novamente.");
            }
        }
    }

}
