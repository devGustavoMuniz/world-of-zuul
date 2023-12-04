import java.util.Scanner;

/**
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 *
 * Esse analisador le a entrada do usuario e tenta interpreta-la como um
 * comando "Adventure". Cada vez que eh chamado ele le uma linha do terminal
 * e tenta interpretar a linha como um comando de duas palavras. Ele retorna
 * o comando como um objeto da classe Comando.
 *
 * O analisador tem um conjunto de palavras de comando conhecidas. Ele compara
 * a entrada do usuario com os comandos conhecidos, e se a entrada nao eh um
 * dos comandos conhecidos, ele retorna um objeto comando que eh marcado como
 * um comando desconhecido.
 *
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */
public class Analisador
{
    /**
     * Constante que guarda todas as palavras de comando válidas.
    */
    private final PalavrasComando palavrasDeComando;

    /**
     * Scanner pra receber a entrada do usuário.
    */
    private final Scanner entrada;

    /**
     * Construtor que inicializa a classe Analisador
    */
    public Analisador() 
    {
        palavrasDeComando = new PalavrasComando();
        entrada = new Scanner(System.in);
    }

    /**
     * Este método é para interação com usuário.
     *
     * @param pergunta A pergunta a ser analisada.
     * @return String.
     */
    public String interagirComUsuario(String pergunta){
        System.out.println(pergunta);
        return entrada.nextLine();
    }

    /**
     * Força o usuário a responder
     *
     * @param pergunta A pergunta em questão.
     */
    public void forcarInteracaoUsuario(String pergunta){
        System.out.println(pergunta);
        entrada.nextLine();
    }

    /**
     * Método para pegar a entrada do usuário
     *
     * @return O proximo comando do usuario
     */
    public Comando pegarComando()
    {
        String linha;   // guardará uma linha inteira
        String palavra1 = null;
        String palavra2 = null;

        System.out.print("> ");     // imprime o prompt

        linha = entrada.nextLine();

        // Tenta encontrar ate duas palavras na linha
        Scanner tokenizer = new Scanner(linha);
        if(tokenizer.hasNext()) {
            palavra1 = tokenizer.next();      // pega a primeira palavra
            if(tokenizer.hasNext()) {
                palavra2 = tokenizer.next();      // pega a segunda palavra
                // obs: nos simplesmente ignoramos o resto da linha.
            }
        }

        // Agora verifica se esta palavra eh conhecida. Se for, cria um comando
        // com ela. Se nao, cria um comando "null" (para comando desconhecido)
        if(palavrasDeComando.ehComando(palavra1)) {
            return new Comando(palavra1, palavra2);
        }
        else {
            return new Comando(null, palavra2); 
        }
    }
}
