import static java.lang.Integer.parseInt;

// Principal Classe do jogo, aqui que a maioria das outras classe são instanciadas
// Principal Classe do jogo, aqui que a maioria das outras classe são instanciadas
public class Jogo
{
    private final Analisador analisador;
    private Ambiente ambienteAtual;

    private PersonagemPrincipal personagemPrincipal;

    // Construtor do jogo, onde é inicializado o mapa
    public Jogo() 
    {
        criarAmbientes();
        criarNpcs();
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


        // inicializa as saidas dos ambientes
        patio.ajustarSaidas("norte",escada);
        patio.ajustarSaidas("leste",compjr);
        patio.ajustarSaidas("sul",lab01);
        patio.ajustarSaidas("oeste",lab06);
        lab01.ajustarSaidas("norte",patio);
        lab06.ajustarSaidas("leste",patio);
        compjr.ajustarSaidas("oeste",patio);
        escada.ajustarSaidas("norte", portaria);
        escada.ajustarSaidas("norte", portaria);
        escada.ajustarSaidas("sul",patio);
        portaria.ajustarSaidas("norte",fora);
        portaria.ajustarSaidas("sul",escada);


        // Define o ambiente em que o jogo é iniciado
        ambienteAtual = patio;
    }

    private void introducao(){
        System.out.println("Nossa, que pena que não consegui ir fazer a atividade 4 de Estrutura de Dados, mas que bom que o Joaquim me deixou refazer a atividade hoje, sábado, bora pra o laboratório DCC-01 no dcc da Ufla e tomara que dê tudo certo...");
        tecleEContinue();

        System.out.println("No DCC...");
        System.out.println("Acho que o Joaquim já chegou, o DCC e o laboratório DCC-01 está destrancado, mas é melhor continuar deixando as portas fechadas, entrei no laboratório.");
        tecleEContinue();

        System.out.println("Laboratório DCC-01");
        System.out.println("Vejo o Joaquim no laboratório com o pé enfaixado em cima da cadeira, o Dolf (seu cachorro da raça Dachshund) e duas muletas estavam do lado dele.");

        pegarResposta("1- Joaquim, o que aconteceu com seu pé??\n Digite qual opção você quer falar:",1);
        System.out.println("Joaquim: Escorreguei naquela chuva de granizo de ontem, cai e machuquei o pé, agora vou ter que ficar um tempo com essas muletas");
        pegarResposta("1- Nossa, melhoras!\n Digite qual opção você quer falar:",1);

        System.out.println("O Carlinhos, outro aluno que ia fazer segunda chamada da prova entrou no laboratório deixando a porta aberta.");
        System.out.println("Joaquim: Fecha a por...");
        System.out.println("Antes que o Joaquim pudesse terminar a frase o Dolf viu um passarinho do lado de fora do lab e saiu correndo atrás dele.");
        System.out.println("Joaquim: Dolf, Dolf!");
        System.out.println("Mas o cachorro não volto");
        pegarResposta("1 - Poxa Carlinhos!\n Digite qual opção você quer falar:",1);

        System.out.println("Joaquim: Jovens padawans, como vocês viram, o Dolf fugiu e, como estou com o pé machucado, não vou conseguir ir atrás dele. Por isso, queria a ajuda de vocês.");
        System.out.println("Carlinhos: E a nossa segunda chamada fica como?!");
        System.out.println("Joaquim: Ok, vocês vão ganhar os pontos da atividade se ajudarem, e, quem conseguirem pegar o Dolf, vai ganhar dois pontos extras.");
        pegarResposta("1 - Carlinhos, você fechou a porta do DCC quando você entrou? Se estiver fechada, o Dolf não tem como sair do DCC.",1);

        System.out.println("Carlinhos: Fechei sim!");
        System.out.println("Joaquim: Ótimo! Então conto com a ajuda de vocês!");

    }

    // Principal método do jogo, fica em loop até o mesmo ser finalizado
    private void tecleEContinue(){
        analisador.forcarInteracaoUsuario("-\nAperte enter para continuar");
    }

    private void pegarResposta(String pergunta, int numeroDesejado){
        try{
            if (parseInt(analisador.interagirComUsuario(pergunta)) != numeroDesejado){
                pegarResposta("Opção inválida, por favor digite um número válido",numeroDesejado);
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            pegarResposta("Opção inválida, por favor digite um número válido",numeroDesejado);
        }
    }

    private void criarNpcs(){
        Npc joaquim, merschmann, aluna, carlinhos, dolf, duda, motoboy;
        joaquim = new Npc("Joaquim",
                " ele gosta muito de frango e de coco seco. E ah, o frango pode ser com ou sem osso. Boa sorte!",
                'm');
        merschmann = new Npc("Merschmann", " Se você falar comigo de novo, vou te processar.", 'm');
        Npc alunaPPOO = new Npc("Aluna de PPOO","Pode ser que alguém tenha frango na sala da comp.", 'f',joaquim);
    }

    // Principal método do jogo, fica em loop até o mesmo ser finalizado
    public void jogar()
    {            
        imprimirBoasVindas();
        introducao();
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

        String nome = analisador.interagirComUsuario("Qual o seu nome?");
        char sexo = analisador.interagirComUsuario("Qual o seu gênero? (F ou M)").charAt(0);
        this.personagemPrincipal = new PersonagemPrincipal(nome,sexo);

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
            case "saidas":
                imprimirOpcoesSaida();
                break;
            case "interagir":
//                interagirComNpc(ambienteAtual.getNpc());
                break;
        }

        return querSair;
    }

    private void interagirComNpc(Npc npc) {
        if(this.personagemPrincipal != null){
            System.out.println();
        }
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
        if(ambienteAtual.getAmbiente(direcao) != null) {
            proximoAmbiente = ambienteAtual.getAmbiente(direcao);
        }
        return proximoAmbiente;
    }

    // Printa as opções de saída do ambiente atual
    public void imprimirOpcoesSaida() {
        System.out.println("Voce esta " + ambienteAtual.getDescricao());
        System.out.println("Saidas: ");
        System.out.println(ambienteAtual.getSaidasAmbiente());


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
