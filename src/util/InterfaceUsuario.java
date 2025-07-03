package util; // Define o pacote util para organização do código

import java.util.Scanner; // Importa a classe Scanner para entrada de dados pelo console

public class InterfaceUsuario {
    // Cria um objeto Scanner para ler entrada do usuário
    Scanner scanner = new Scanner(System.in);
    
    // Cria um objeto da classe ValidaDado para usar métodos de validação
    ValidaDado validaDado = new ValidaDado();

    // Método para ler e validar o valor do imóvel
    public double valorImovel(){
        while (true) { // Laço que continua até o usuário fornecer um valor válido
            System.out.print("\nInsira o valor do imovel: ");
            
            // Lê o valor digitado como double
            double valorImovel = scanner.nextDouble();

            // Verifica se o valor é válido usando o método da classe ValidaDado
            if (validaDado.validaValorImovel(valorImovel)) {
                return valorImovel; // Retorna o valor se for válido
            } else {
                System.out.println("Valor inválido."); // Mensagem de erro
            }
        }
    }

    // Método para ler e validar o prazo do financiamento (em anos)
    public int prazoFinanciamentoAnos(){
        while (true) {
            System.out.print("\nInsira o prazo de financiamento em anos: ");
            
            // Lê o valor digitado como inteiro
            int prazoFinanciamentoAnos = scanner.nextInt();

            // Verifica se o prazo é válido
            if (validaDado.validaTempoFinancimento(prazoFinanciamentoAnos)) {
                return prazoFinanciamentoAnos; // Retorna se for válido
            } else {
                System.out.println("Valor inválido."); // Mensagem de erro
            }
        }
    }

    // Método para ler e validar a taxa de juros anual
    public double taxaJurosAnual(){
        while (true) {
            System.out.print("\nInsira a taxa de juros anual(em decimal): ");

            // Lê a taxa como double (espera algo como 0.1)
            double taxaJurosAnual = scanner.nextDouble();

            // Verifica se a taxa é válida
            if (validaDado.validaTaxa(taxaJurosAnual)) {
                return taxaJurosAnual; // Retorna a taxa se for válida
            } else {
                System.out.println("Valor inválido."); // Mensagem de erro
            }
        }
    }

    public double tamanhoAreaConstruida(){
        System.out.print("Insira a area do terreno construido: ");
        
        double tamanhoAreaConstruida = scanner.nextDouble();

        return tamanhoAreaConstruida;
    }

    public double tamanhoTerreno(){
        System.out.print("Insira o tamanho do terreno");

        double tamanhoTerreno = scanner.nextDouble();

        return tamanhoTerreno;
    }
}
