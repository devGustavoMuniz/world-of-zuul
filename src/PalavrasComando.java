// Classe a respeito dos comandos do jogo
public class PalavrasComando
{
    // Um vetor constante que guarda todas as palavras de comandos válidas
    private static final String[] comandosValidos = {
        "ir", "sair", "ajuda", "interagir", "saidas", "observar"
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

    public static String getComandosValidos(){
        String retorno = "";
        for (int i = 0; i < comandosValidos.length; i++) {
            String comando = comandosValidos[i];
            if(i == comandosValidos.length -1){
                retorno += comando;
            }
            else{
                retorno += comando + " ";
            }
        }
        return retorno;
    }
}
