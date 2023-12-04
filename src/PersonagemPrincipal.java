import java.util.ArrayList;

import java.util.Random;

/**
 *  Classe reponsável pelo PersonagemPrincipal
 *
 * @author  Gabriel, Gabriela, Gustavo e Marcus
 */
public class PersonagemPrincipal extends Personagem {
    private ArrayList<Npc> npcsInteragidos;
    private int movimentos;
    private ArrayList<Item> items;

    /**
     *  Construtor que inicializa um PersonagemPrincipal
     */
    public PersonagemPrincipal(String nome, char sexo) {
        super(nome, sexo);
        this.items = new ArrayList<Item>();
        this.npcsInteragidos = new ArrayList<Npc>();
        Random random = new Random();
        this.movimentos = random.nextInt(16) + 10;
    }

    /**
     *  Adiciona um npc à lista de NPCs interagidos
     *
     * @param npc em questao
     */
    public void adicionarNpcInteragido(Npc npc) {
        try {
            if (getNpcByName(npc.getNome()) == null) {
                this.npcsInteragidos.add(npc);
            }
        } catch (Exception e) {
            System.out.println("Erro ao interagir com npc. ");
        }
    }

    /**
     *  Método pra marcar que o requisito do NPC em questão foi cumprido
     */
    public boolean cumpreRequisito(Npc target){
        if(!target.temRequisito()){
            return true;
        }
        return this.interagiu(target.getRequisito());
    }

    /**
     *  Método pra marcar que o requisito do Ambiente em questão foi cumprido
     */
    public boolean cumpreRequisito(Ambiente target){
        if(!target.temRequisito()){
            return true;
        }
        return this.interagiu(target.getRequisito());
    }

    /**
     *  Método responsável por interagir com o npc
     */
    public String interagir(Npc npc){
        if(this.cumpreRequisito(npc)) {
            interagiu(npc);
            adicionarNpcInteragido(npc);
            return npc.getDica();
        }
        return "Você deve falar com o " + npc.getRequisito().getNome() + " para conseguir minha dica.";
    }

    public boolean interagiu(Npc npc) {
        try{
            return getNpcByName(npc.getNome()) != null;
        }
        catch (Exception e){
            System.out.println("Erro ao interagir com npc:");
            return false;
        }
    }

    public Npc getNpcByName(String nome) {
        for (Npc npc : this.npcsInteragidos) {
            if (npc.getNome().toLowerCase().equals(nome.toLowerCase())) {

                return npc;
            }
        }
        return null;

    }

    public int getMovimentos(){return this.movimentos;}

    public ArrayList<Item> getItems(){
        return this.items;
    }
    // Gasta um movimento do jogador
    public void gastarMovimento() {
        this.movimentos -= 1;
    }

    public void pegarItem(Item item){
        items.add(item);
    }
    // Verifica se o jogador ja gastou todos os seus movimentos
    public boolean verificaMovimentos(){
        return this.movimentos != 0;
    }

    public boolean temFrango(){
        for (Item item : this.items){
            if(item.getNome().toLowerCase().equals("frango".toLowerCase())){
                return true;
            }
        }
        return false;
    }

    public Item usarItem(String nomeItem){
        for (int i = 0; i < this.items.size(); i++) {
            Item v_Item = items.get(i);
            if(v_Item.getNome().toLowerCase().equals(nomeItem.toLowerCase())){
                items.remove(i);
                return v_Item;
            }
        }
        return null;
    }

    public void adicionarMovimento(int valor){
        this.movimentos += valor;
    }
}