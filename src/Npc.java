public class Npc extends Personagem implements Requisito {
    private String informacao;
    private Npc requisito;
    public Npc(String nome, String info, char sexo) {
        super(nome,sexo);
        this.informacao = info;
    }
    public Npc(String nome, String info, char sexo, Npc requisito) {
        super(nome,sexo);
        this.informacao = info;
        this.requisito = requisito;
    }
    public String getDica(){
        return "Dica: " + this.informacao;
    }


    @Override
    public boolean temRequisito(){
        return this.requisito != null;
    }
    private String apresentacao(){
        char vocabulo = this.getSexo() == 'm' ? 'o' : 'a';
        return "Olá, eu sou " + vocabulo +" " + this.getNome()+". ";
    }

    public Npc getRequisito(){
        return this.requisito;
    }

    public String falar(String fala, String nomeJogador){
        return getNome() + ": " + nomeJogador + " " + fala;
    }

}