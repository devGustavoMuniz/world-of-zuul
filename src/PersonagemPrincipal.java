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

    /**
     *  Construtor que inicializa um PersonagemPrincipal
     */
    public PersonagemPrincipal(String nome, char sexo) {
        super(nome, sexo);
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
            System.out.println("Erro ao interagir com npc. " + e);
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
            return npc.getDica();
        }
        return "Você deve falar com o " + npc.getRequisito() + " para conseguir minha dica.";
    }

    /**
     *  Método pra checar se ja interagiu com o NPC
     */
    public boolean interagiu(Npc npc) {
        try{
            return getNpcByName(npc.getNome()) != null;
        }
        catch (Exception e){
            System.out.println("Erro ao interagir com npc:" + e.getMessage() + e);
            return false;
        }
    }

    /**
     *  Método para buscar o npc pelo nome
     *
     * @param nome nome do npc
     * @return Npc Objeto da classe Npc
     */
    public Npc getNpcByName(String nome) {
        for (Npc npc : this.npcsInteragidos) {
            if (npc.getNome().equals(nome)) {
                return npc;
            }
        }
        return null;
    }

    /**
     *  Retorna o atribbuto movimentos
     */
    public int getMovimentos(){return this.movimentos;}

    /**
     *  Método para consumir um movimento do jogador
     */
    public void gastarMovimento() {
        this.movimentos -= 1;
    }

    /**
     *  Método para verificar se ainda tem movimentos restantes
     */
    public boolean verificaMovimentos(){
        return this.movimentos != 0;
    }
}