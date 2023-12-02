public class Npc extends Personagem {
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
    public String getDica(PersonagemPrincipal personagem){
        personagem.adicionarNpcInteragido(this);

        if(this.requisito != null && !personagem.interagiu(this.requisito)){
            return apresentacao() + "Como posso lhe ajudar?";
        }
        return apresentacao() + "Caso você esteja atrás do Dolf, " + this.informacao;
    }

    private String apresentacao(){
        char vocabulo = this.getSexo() == 'm' ? 'o' : 'a';
        return "Olá, eu sou " + vocabulo +" " + this.getNome()+". ";
    }


}