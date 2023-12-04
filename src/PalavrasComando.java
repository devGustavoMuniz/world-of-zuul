/**
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 *
 * Essa classe guarda uma enumeracao de todos os comandos conhecidos do
 * jogo. Ela eh usada no reconhecimento de comandos como eles sao digitados.
 *
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */
public class PalavrasComando
{
    /**
     * Constante que armazena os comandos válidos.
    */
    private static final String[] comandosValidos = {
        "ir", "sair", "ajuda", "interagir", "saidas", "observar"
    };

    /**
     * Construtor que inicializa a classe PalavrasComando
    */
    public PalavrasComando()
    {
        // nada a fazer no momento...
    }

    /**
     * Este método verifica se a entrada é um comando.
     *
     * @param umaString O comando a ser analisado.
     * @return boolean.
     */
    public boolean ehComando(String umaString)
    {
        for (String comandosValido : comandosValidos) {
            if (comandosValido.equals(umaString))
                return true;
        }
        return false;
    }

    /**
     * Este método busca por todos os comando válidos.
     *
     * @return String.
     */
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
