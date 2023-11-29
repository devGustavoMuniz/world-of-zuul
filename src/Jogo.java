/**
 *  Essa eh a classe principal da aplicacao "World of Zull".
 *  "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 *  Usuarios podem caminhar em um cenario. E eh tudo! Ele realmente
 *  precisa ser estendido para fazer algo interessante!

 *  Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 *  "jogar".

 *  Essa classe principal cria e inicializa todas as outras: ela cria os
 *  ambientes, cria o analisador e comeca o jogo. Ela tambeme avalia e 
 *  executa os comandos que o analisador retorna.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */

public class Jogo 
{
    private final Analisador analisador;
    private Ambiente ambienteAtual;
        
    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() 
    {
        criarAmbientes();
        analisador = new Analisador();
    }

    /**
     * Cria todos os ambientes e liga as saidas deles
     */
    private void criarAmbientes()
    {
        Ambiente patio, lab01, lab06, compjr, escada, portaria, fora;
      
        // cria os ambientes
        patio = new Ambiente("Pátio do DCC");
        lab01 = new Ambiente("Laboratório 01 do DCC");
        lab06 = new Ambiente("Laboratório 06 do DCC");
        compjr = new Ambiente("Salinha da Comp Jr");
        escada = new Ambiente("Escada do DCC");
        portaria = new Ambiente("Portaria do DCC");
        fora = new Ambiente("Parte de fora do DCC");

        // inicializa as saidas dos ambientes
        patio.ajustarSaidas(escada, compjr, lab01, lab06);
        lab01.ajustarSaidas(patio, null, null, null);
        lab06.ajustarSaidas(null, patio, null, null);
        compjr.ajustarSaidas(null, null, null, patio);
        escada.ajustarSaidas(portaria, null, patio, null);
        portaria.ajustarSaidas(fora, null, escada, null);

        ambienteAtual = patio;  // o jogo comeca do lado de fora
    }

    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() 
    {            
        imprimirBoasVindas();

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.
                
        boolean terminado = false;
        while (! terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
        System.out.println("Obrigado por jogar. Ate mais!");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas()
    {
        System.out.println();
        System.out.println("Bem-vindo ao World of Zuul!");
        System.out.println("World of Zuul eh um novo jogo de aventura, incrivelmente chato.");
        System.out.println("Digite 'ajuda' se voce precisar de ajuda.");
        System.out.println();

        imprimirOpcoesSaida();
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando) 
    {
        boolean querSair = false;

        if(comando.ehDesconhecido()) {
            System.out.println("Eu nao entendi o que voce disse...");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();
        switch (palavraDeComando) {
            case "ajuda":
                imprimirAjuda();
                break;
            case "ir":
                irParaAmbiente(comando);
                break;
            case "sair":
                querSair = sair(comando);
                break;
        }

        return querSair;
    }

    // Implementacoes dos comandos do usuario

    /**
     * Printe informacoes de ajuda.
     * Aqui nos imprimimos algo bobo e enigmatico e a lista de 
     * palavras de comando
     */
    private void imprimirAjuda() 
    {
        System.out.println("Voce esta perdido. Voce esta sozinho. Voce caminha");
        System.out.println("pela universidade.");
        System.out.println();
        System.out.println("Suas palavras de comando sao:");
        System.out.println("   ir sair ajuda");
    }

    /** 
     * Tenta ir em uma direcao. Se existe uma saida entra no 
     * novo ambiente, caso contrario imprime mensagem de erro.
     */
    private void irParaAmbiente(Comando comando) 
    {
        if(!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            System.out.println("Ir pra onde?");
            return;
        }

        Ambiente proximoAmbiente = getAmbiente(comando);

        if (proximoAmbiente == null) {
            System.out.println("Nao ha passagem!");
        }
        else {
            ambienteAtual = proximoAmbiente;

            imprimirOpcoesSaida();
        }
    }

    private Ambiente getAmbiente(Comando comando) {
        String direcao = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual
        Ambiente proximoAmbiente = null;
        if(direcao.equals("norte")) {
            proximoAmbiente = ambienteAtual.saidaNorte;
        }
        if(direcao.equals("leste")) {
            proximoAmbiente = ambienteAtual.saidaLeste;
        }
        if(direcao.equals("sul")) {
            proximoAmbiente = ambienteAtual.saidaSul;
        }
        if(direcao.equals("oeste")) {
            proximoAmbiente = ambienteAtual.saidaOeste;
        }
        return proximoAmbiente;
    }

    public void imprimirOpcoesSaida() {
        System.out.println("Voce esta " + ambienteAtual.getDescricao());

        System.out.println("Saidas: ");
        if(ambienteAtual.saidaNorte != null) {
            System.out.println("norte: "+ambienteAtual.saidaNorte.getDescricao()+", ");
        }
        if(ambienteAtual.saidaLeste != null) {
            System.out.println("leste: "+ambienteAtual.saidaLeste.getDescricao()+", ");
        }
        if(ambienteAtual.saidaSul != null) {
            System.out.println("sul: "+ambienteAtual.saidaSul.getDescricao()+", ");
        }
        if(ambienteAtual.saidaOeste != null) {
            System.out.println("oeste: "+ambienteAtual.saidaOeste.getDescricao()+", ");
        }
        System.out.println();
    }

    /** 
     * "Sair" foi digitado. Verifica o resto do comando pra ver
     * se nos queremos realmente sair do jogo.
     * @return true, se este comando sai do jogo, false, caso contrario
     */
    private boolean sair(Comando comando) 
    {
        if(comando.temSegundaPalavra()) {
            System.out.println("Sair o que?");
            return false;
        }
        // else alterado pra early return
        return true;  // sinaliza que nos queremos sair
    }
}
