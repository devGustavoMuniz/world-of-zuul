// Principal Classe do jogo, aqui que a maioria das outras classe são instanciadas
public class Jogo 
{
    private final Analisador analisador;
    private Ambiente ambienteAtual;
        
    // Construtor do jogo, onde é inicializado o mapa
    public Jogo() 
    {
        criarAmbientes();
        analisador = new Analisador();
    }

    // Criação dos ambientes e suas respectivas saídas
    private void criarAmbientes()
    {
        Ambiente patio, lab01, lab06, compjr, escada, portaria, fora;
      
        // Criação os ambientes
        patio = new Ambiente("Pátio do DCC");
        lab01 = new Ambiente("Laboratório 01 do DCC");
        lab06 = new Ambiente("Laboratório 06 do DCC");
        compjr = new Ambiente("Salinha da Comp Jr");
        escada = new Ambiente("Escada do DCC");
        portaria = new Ambiente("Portaria do DCC");
        fora = new Ambiente("Parte de fora do DCC");

        // Inicialização as saidas dos ambientes
        patio.ajustarSaidas(escada, compjr, lab01, lab06);
        lab01.ajustarSaidas(patio, null, null, null);
        lab06.ajustarSaidas(null, patio, null, null);
        compjr.ajustarSaidas(null, null, null, patio);
        escada.ajustarSaidas(portaria, null, patio, null);
        portaria.ajustarSaidas(fora, null, escada, null);

        // Define o ambiente em que o jogo é iniciado
        ambienteAtual = patio;
    }

    // Principal método do jogo, fica em loop até o mesmo ser finalizado
    public void jogar() 
    {            
        imprimirBoasVindas();

        // Loop dos comandos
        boolean terminado = false;
        while (! terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
        System.out.println("Obrigado por jogar. Ate mais!");
    }

    // Printa a mensagem de abertura
    private void imprimirBoasVindas()
    {
        System.out.println();
        System.out.println("Bem-vindo ao World of Zuul!");
        System.out.println("World of Zuul eh um novo jogo de aventura, incrivelmente chato.");
        System.out.println("Digite 'ajuda' se voce precisar de ajuda.");
        System.out.println();

        imprimirOpcoesSaida();
    }

    // Método que lida com o comando do usuário
    // @param 'comando' - O Comando a ser processado.
    // @return 'true' se o comando finalizar o jogo.
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

    // Printa o texto de ajuda para o usuário
    private void imprimirAjuda() 
    {
        System.out.println("Voce esta perdido. Voce esta sozinho. Voce caminha");
        System.out.println("pela universidade.");
        System.out.println();
        System.out.println("Suas palavras de comando sao:");
        System.out.println("   ir sair ajuda");
    }

    // Método para ir a algum ambiente, caso exista
    private void irParaAmbiente(Comando comando) 
    {
        if(!comando.temSegundaPalavra()) {
            // Lembra o usuário de informar a segunda palavra
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

    // Pega o ambiente em que o usuário está tentando entrar
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

    // Printa as opções de saída do ambiente atual
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

    // Método para lidar com o comando 'sair'
    // @param 'comando' - O Comando a ser processado
    // @return true, se o comando sair do jogo
    private boolean sair(Comando comando) 
    {
        if(comando.temSegundaPalavra()) {
            System.out.println("Sair o que?");
            return false;
        }
        // else alterado pra early return
        return true;
    }
}
