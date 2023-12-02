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
            if (!npcsInteragidos.contains(npc)) {
                this.npcsInteragidos.add(npc);
            }
        } catch (Exception e) {
            System.out.println("Erro ao interagir com npc. " + e);
        }
    }

    public boolean interagiu(Npc npc) {
        return this.npcsInteragidos.contains(npc);
    }

//    public Npc getNpcByName(String nome) {
//        for (Npc npc : this.npcsInteragidos) {
//            System.out.println(npc.getNome() + " vs " + nome);
//            if (npc.getNome() == nome) {
//                return npc;
//            }
//        }
//        return null;
//    }

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