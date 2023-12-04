import static java.lang.Integer.parseInt;
import java.util.List;
import java.util.ArrayList;

/**
 *  Essa eh a classe principal da aplicacao "World of Zull".
 *  "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 *  Usuarios podem caminhar em um cenario. E eh tudo! Ele realmente
 *  precisa ser estendido para fazer algo interessante!
 *
 *  Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 *  "jogar".
 *
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

    private ArrayList<Npc> npcs;
    private ArrayList<Ambiente> ambientes;
    private PersonagemPrincipal personagemPrincipal;

    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() 
    {
        npcs = new ArrayList<Npc>(criarNpcs());
        criarAmbientes();
        analisador = new Analisador();
    }



    /**
     * Método para printar a introdução do jogo na tela
     */
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

    /**
     * Método para o usuário sinalizar que pode prosseguir com o jogo
     */
    private void tecleEContinue(){
        analisador.forcarInteracaoUsuario("-\nAperte enter para continuar");
    }

    /**
     * Pega a respota do usuário pra uma determinada pergunta
     *
     * @param pergunta Pergunta que o jogo fará ao usuário
     * @param numeroDesejado Resposta esperada pelo programa
     */
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

    /**
     * Cria todos os NPCs
     *
     * @return Uma lista com os NPCs criados
     */
    private List<Npc> criarNpcs(){
        List<Npc> npcs = new ArrayList<>();

        Npc joaquim, merschmann, aluna, carlinhos, dolf, duda, motoboy;
        joaquim = new Npc("Joaquim",
                " ele gosta muito de frango e de coco seco. E ah, o frango pode ser com ou sem osso. Boa sorte!",
                'm');
        merschmann = new Npc("Merschmann", " Se você falar comigo de novo, vou te processar.", 'm');
        aluna = new Npc("Aluna","Pode ser que alguém tenha frango na sala da comp.", 'f',joaquim);
        carlinhos = new Npc("Carlinhos", "aaaa", 'm');
        dolf = new Npc("Dolf", "aaaa", 'm');
        duda = new Npc("Duda", "aaaa", 'f');
        motoboy = new Npc("Motoboy", "aaaa", 'm');

        npcs.add(joaquim);
        npcs.add(merschmann);
        npcs.add(aluna);
        npcs.add(carlinhos);
        npcs.add(dolf);
        npcs.add(duda);
        npcs.add(motoboy);

        return npcs;
    }

    /**
     * Procura um NPC pelo nome
     *
     * @return Objeto da classe Npc
     */

    private Npc getNpcByName(String nome){
        for (Npc npc : this.npcs) {
            if (npc.getNome().equals(nome)) {
                return npc;
            }
        }
        return null;
    }

    /**
     * Cria todos os ambientes e liga as saidas deles
     */
    private void criarAmbientes()
    {
        Ambiente patio, lab01, lab06, compjr, escada, portaria, fora;
        Npc merschmann, joaquim, dolf, carlinhos, aluna, duda, motoboy;

        merschmann = getNpcByName("Merschmann");
        joaquim = getNpcByName("Joaquim");
        dolf = getNpcByName("Dolf");
        carlinhos = getNpcByName("Carlinhos");
        aluna = getNpcByName("Aluna");
        duda = getNpcByName("Duda");
        motoboy = getNpcByName("Motoboy");

        // Criação os ambientes
        patio = new Ambiente("Pátio do DCC");
        lab01 = new Ambiente("Laboratório 01 do DCC",joaquim);
        lab06 = new Ambiente("Laboratório 06 do DCC",merschmann);
        compjr = new Ambiente("Salinha da Comp Jr",joaquim);
        escada = new Ambiente("Escada do DCC");
        portaria = new Ambiente("Portaria do DCC");
        fora = new Ambiente("Parte de fora do DCC");

        // inicializa as saidas dos ambientes
        patio.ajustarSaidas("norte",escada);
        patio.ajustarSaidas("leste",compjr);
        patio.ajustarSaidas("sul",lab01);
        patio.ajustarSaidas("oeste",lab06);
        patio.addNpc(dolf);

        lab01.ajustarSaidas("norte",patio);
        lab01.addNpc(joaquim);
        lab01.addNpc(carlinhos);

        lab06.ajustarSaidas("leste",patio);
        lab06.addNpc(merschmann);
        lab06.addNpc(aluna);

        compjr.ajustarSaidas("oeste",patio);
        compjr.addNpc(duda);

        escada.ajustarSaidas("norte", portaria);
        escada.ajustarSaidas("norte", portaria);
        escada.ajustarSaidas("sul",patio);

        portaria.ajustarSaidas("norte",fora);
        portaria.ajustarSaidas("sul",escada);

        fora.addNpc(motoboy);

        // Define o ambiente em que o jogo é iniciado
        ambienteAtual = patio;

        ambientes.add(patio);
        ambientes.add(lab01);
        ambientes.add(lab06);
        ambientes.add(compjr);
        ambientes.add(escada);
        ambientes.add(portaria);
        ambientes.add(fora);

    }

    /**
     * Adiciona cada NPC ao seu respectivo ambiente
     */
    private void adicionarNpcsAosAmbientes(List<Npc> npcs, List<Ambiente> ambientes) {
        ambientes.get(0).addNpc(npcs.get(4)); // Dolf no pátio
        ambientes.get(1).addNpc(npcs.get(0)); // Joaquim no lab01
        ambientes.get(1).addNpc(npcs.get(3)); // Carlinhos no lab01
        ambientes.get(2).addNpc(npcs.get(1)); // Merschmann no lab06
        ambientes.get(2).addNpc(npcs.get(2)); // Aluna no lab06
        ambientes.get(3).addNpc(npcs.get(5)); // Duda na compjr
        ambientes.get(6).addNpc(npcs.get(6)); // Motoboy no lado de fora
    }

    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
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

        String nome = analisador.interagirComUsuario("Qual o seu nome?");
        char sexo = analisador.interagirComUsuario("Qual o seu gênero? (F ou M)").charAt(0);
        this.personagemPrincipal = new PersonagemPrincipal(nome,sexo);

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
            case "saidas":
                imprimirOpcoesSaida();
                break;
            case "interagir":
                interagirComNpc(comando);
                break;
            case "observar":
                observar();
                break;
        }

        return querSair;
    }

    /**
     * Esse método é responsável pela interação com NPC
     */
    private void interagirComNpc(Comando comando) {
        if(!comando.temSegundaPalavra()) {
            // Lembra o usuário de informar com quem deseja interagir
            System.out.println("Interagir com quem?");
            return;
        }
        String nomeNpc = comando.getSegundaPalavra();
        Npc npc = ambienteAtual.getNpcByName(nomeNpc);
        if(npc != null){
            String informacao = personagemPrincipal.interagir(npc);
            System.out.println(informacao);
        }
        else{
            System.out.println("Não existem npcs por aqui.");
        }
//        "Não encontramos nenhum npc com este nome."
//        return npc.getDica();


    }


    /**
     * Esse método diz pro jogador se tem alguem no ambiente.
     */
    private void observar(){
        System.out.println("Você está em: " + ambienteAtual.getDescricao());
        if(!ambienteAtual.existeNpc()){
            System.out.println("Você está sozinho.");
        }
        else{
            ArrayList<String> nomeNpcs = ambienteAtual.getNomeNpcs();
            for(String nome : nomeNpcs){
                System.out.println("O npc " + nome + " está aqui.");
            }
        }
    }

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
        System.out.println(PalavrasComando.getComandosValidos());
    }

    /**
     * Tenta ir em uma direcao. Se existe uma saida entra no
     * novo ambiente, caso contrario imprime mensagem de erro.
     */
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
            if(personagemPrincipal.cumpreRequisito(proximoAmbiente)){
                ambienteAtual = proximoAmbiente;
                personagemPrincipal.gastarMovimento();
            }
            else{
                System.out.println("Para entrar nesta sala você deve falar com " + proximoAmbiente.getRequisito());
            }
            imprimirOpcoesSaida();
        }
    }

    /**
     * Faz uma busca pelos ambientes de acordo com sua descrição
     * @param descricao
     * @return objeto da classe Ambiente
     */
    private Ambiente getAmbienteByDescricao(String descricao){
        for (Ambiente ambiente : ambientes){
            if(ambiente.getDescricao().equals(descricao)){
                return ambiente;
            }
        }
        return null;
    }

    /**
     * Esse método trata dos requisitos pra entrar em determinado ambiente
     */
    private void tratarEntradaAmbientesComRequisito(Ambiente ambiente){
        if(ambiente.getDescricao().equals("Laboratório 06 do DCC")){
            System.out.println("Não foi possível entrar na sala");
            System.out.println("Seus pontos de ação diminúíram com essa tentativa.");
            personagemPrincipal.gastarMovimento();

        }
        System.out.println("Para entrar nesta sala você deve falar com " + ambiente.getRequisito());
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


    /**
     * Esse método mostra pro usuário as opções de saída do ambiente atual
     */
    public void imprimirOpcoesSaida() {
        System.out.println("Voce esta " + ambienteAtual.getDescricao());
        System.out.println("Saidas: ");
        System.out.println(ambienteAtual.getSaidasAmbiente());


        System.out.println();
    }

    /**
     * Printa a mensagem de game over caso o usuário esgote o numero de movimentos
     */
    public void printGameOverOutMoves(){
        System.out.println("* Tum Dum *");
        System.out.println("Recebo uma notificação no celular, é uma mensagem de Carlinhos");
        System.out.println("Notificação: Há, recuperei o Dolf");
        System.out.println();
        printGameOver();
    }

    /**
     * Printa a mensagem de game over pro usuário
     */
    public void printGameOver(){
        System.out.println("GAME OVER!!!!");
        System.out.println();
        System.out.println("Deseja jogar novamente? (sim/nao)");
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
        return true;
    }
}
