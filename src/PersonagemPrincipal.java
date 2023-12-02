import java.util.ArrayList;

public class PersonagemPrincipal extends Personagem {
    private ArrayList<Npc> npcsInteragidos;

    public PersonagemPrincipal(String nome, char sexo) {
        super(nome, sexo);
        this.npcsInteragidos = new ArrayList<Npc>();
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
}