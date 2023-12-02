import java.util.Scanner;

// Classe a respeito do Analisador, que le a entrada do usuário e a interpreta
// Cada vez que é chamada, le uma linha do terminal e tenta interpretar uma linha como duas palavras
// O comando (primeira palavra) e o complemento (segunda palavra)

// O Analisador tem um conjunto de palavras de comando conhecidas, que compara com a entrada do usuário
// Se a entrada nao eh um dos comandos conhecidos, ele retorna um objeto comando que eh marcado como um comando desconhecido
public class Analisador
{
    private final PalavrasComando palavrasDeComando;  // guarda todas as palavras de comando validas
    private final Scanner entrada;         // origem da entrada de comandos

    // Construtor do analisador pra ler do terminal
    public Analisador() 
    {
        palavrasDeComando = new PalavrasComando();
        entrada = new Scanner(System.in);
    }

    // @return O próximo comando do usuario

    public String interagirComUsuario(String pergunta){
        System.out.println(pergunta);
        return entrada.nextLine();
    }

    public void forcarInteracaoUsuario(String pergunta){
        System.out.println(pergunta);
        entrada.nextLine();
    }

    /**
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
