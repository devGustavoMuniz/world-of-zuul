import java.util.ArrayList;

import java.util.Random;
public class PersonagemPrincipal extends Personagem {
    private ArrayList<Npc> npcsInteragidos;
    private int movimentos;

    public PersonagemPrincipal(String nome, char sexo) {
        super(nome, sexo);
        this.npcsInteragidos = new ArrayList<Npc>();
        Random random = new Random();
        this.movimentos = random.nextInt(16) + 10;
    }

    public void adicionarNpcInteragido(Npc npc) {
        try {
            if (getNpcByName(npc.getNome()) == null) {
                this.npcsInteragidos.add(npc);
            }
        } catch (Exception e) {
            System.out.println("Erro ao interagir com npc. " + e);
        }
    }

    public String interagir(Npc npc){
        if(npc.cumpreRequisito(this)) {
            interagiu(npc);
            return npc.getDica();
        }
        return "Você deve falar com o " + npc.getRequisito() + " para conseguir minha dica.";
    }

    public boolean interagiu(Npc npc) {
        try{
            return getNpcByName(npc.getNome()) != null;
        }
        catch (Exception e){
            System.out.println("Erro ao interagir com npc:" + e.getMessage() + e);
            return false;
        }
    }

    public Npc getNpcByName(String nome) {
        for (Npc npc : this.npcsInteragidos) {
            if (npc.getNome().equals(nome)) {
                return npc;
            }
        }
        return null;
    }

    public int getMovimentos(){return this.movimentos;}

    // Gasta um movimento do jogador
    public void gastarMovimento() {
        this.movimentos -= 1;
    }

    // Verifica se o jogador ja gastou todos os seus movimentos
    public boolean verificaMovimentos(){
        return this.movimentos != 0;
    }
}