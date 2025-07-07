// Define o pacote onde a exceção está localizada
package model;

// Define uma exceção personalizada que estende RuntimeException
public class AumentoMaiorDoQueJurosException extends RuntimeException {

    // Construtor da exceção que recebe uma mensagem e repassa para a superclasse
    public AumentoMaiorDoQueJurosException(String mensagem) {
        // Chama o construtor da classe RuntimeException passando a mensagem
        super(mensagem);
    }
}
