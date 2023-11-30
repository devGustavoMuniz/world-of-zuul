// Classe a respeito do Comando digitado pelo usuário
// Um comando consiste em 2 strings, uma palavra de comando, como 'ir'
// E a segunda como 'para o pátio'

public class Comando
{
    private final String palavraDeComando;
    private final String segundaPalavra;

    // Construtor, ambos os atributos podem ser null
    public Comando(String primeiraPalavra, String segundaPalavra)
    {
        palavraDeComando = primeiraPalavra;
        this.segundaPalavra = segundaPalavra;
    }

    // Retorna a palavra de comando (primeira palavra)
    public String getPalavraDeComando()
    {
        return palavraDeComando;
    }

    // @return A segunda palavra deste comando
    // e null se nao existe segunda palavra.
    public String getSegundaPalavra()
    {
        return segundaPalavra;
    }

    // Verifica se o comando do usuário não existe
    // @return 'true' se o comando nao foi entendido.
    public boolean ehDesconhecido()
    {
        return (palavraDeComando == null);
    }

    // Verifica se o comando do usuário tem segunda palavra
    // @return 'true' se o comando tem uma segunda palavra
    public boolean temSegundaPalavra()
    {
        return (segundaPalavra != null);
    }
}

