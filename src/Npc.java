/**
 *  Classe responsável pelos NPCs do jogo
 *
 * @author  Gabriel, Gabriela, Gustavo e Marcus
 */

public class Npc extends Personagem implements Requisito {
    private String informacao;
    private Npc requisito;
    private Item item;

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
    public Npc(String nome, String info, char sexo, Npc requisito, Item item) {
        super(nome,sexo);
        this.informacao = info;
        this.item = item;
        this.requisito = requisito;
    }

    public Npc(String nome, String info, char sexo, Item item) {
        super(nome,sexo);
        this.informacao = info;
        this.item = item;
    }

    /**
     *  Método para pegar o atributo informacao
     *
     * @return String do atributo informacao
     */
    public String getDica(){

        String dica = "Dica: " + this.informacao;
        if(this.item != null){
            dica += ". Este NPC possui o item " + this.item.getNome() + ". Digite pegar " + this.item.getNome() + " para obter.";
        }
        return dica;
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
    public boolean temItem(){
        return this.item != null;
    }

    public Item getItem(String nome){
        if(this.item.getNome().toLowerCase().equals(nome.toLowerCase())){
            return this.item;
        }
        return null;
    }

    public void removerItem(){
        if(this.item != null){
            this.item = null;
        }
    }
}