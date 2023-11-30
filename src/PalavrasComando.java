// Classe a respeito dos comandos do jogo
public class PalavrasComando
{
    // Um vetor constante que guarda todas as palavras de comandos válidas
    private static final String[] comandosValidos = {
        "ir", "sair", "ajuda"
    };

    // Construtor
    public PalavrasComando()
    {
        // nada a fazer no momento...
    }

    // Verifica se a palavra é um comando válido
    // @param 'umaString' - string de entrada do usuário
    // @return 'true' caso seja um comando válido
    public boolean ehComando(String umaString)
    {
        for (String comandosValido : comandosValidos) {
            if (comandosValido.equals(umaString))
                return true;
        }
        return false;
    }
}
