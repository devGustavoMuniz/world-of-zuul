/**
 *  Classe responsável pelos NPCs do jogo
 *
 * @author  Gabriel, Gabriela, Gustavo e Marcus
 */

public class Npc extends Personagem implements Requisito {
    private String informacao;
    private Npc requisito;

    /**
     *  Construtor que inicializa a classe caso o npc não tenha requisito
     */
    public Npc(String nome, String info, char sexo) {
        super(nome,sexo);
        this.informacao = info;
    }

    /**
     *  Construtor que inicializa a classe caso o npc tenha requisito
     */
    public Npc(String nome, String info, char sexo, Npc requisito) {
        super(nome,sexo);
        this.informacao = info;
        this.requisito = requisito;
    }

    /**
     *  Método para pegar o atributo informacao
     *
     * @return String do atributo informacao
     */
    public String getDica(){
        return "Dica: " + this.informacao;
    }


    /**
     *  Método para saber se o NPC em questão tem requisito
     *
     * @return boolean
     */
    @Override
    public boolean temRequisito(){
        return this.requisito != null;
    }
    private String apresentacao(){
        char vocabulo = this.getSexo() == 'm' ? 'o' : 'a';
        return "Olá, eu sou " + vocabulo +" " + this.getNome()+". ";
    }

    /**
     *  Método para pegar qual o requisito do NPC
     *
     * @return atributo requisito
     */
    public Npc getRequisito(){
        return this.requisito;
    }

    /**
     *  Método responsável pela fala do NPC
     *
     * @param fala
     * @param nomeJogador
     *
     * @return String com a frase montada
     */
    public String falar(String fala, String nomeJogador){
        return getNome() + ": " + nomeJogador + " " + fala;
    }

}