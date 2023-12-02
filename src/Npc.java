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
    public String getDica(PersonagemPrincipal personagem){
        personagem.adicionarNpcInteragido(this);
        if(cumpreRequisito(personagem)){
            return apresentacao() + "Como posso lhe ajudar?";
        }
        return apresentacao() + "Caso você esteja atrás do Dolf, " + this.informacao;
    }

    @Override
    public boolean cumpreRequisito(PersonagemPrincipal personagem){
        return temRequisito() && personagem.interagiu(this.requisito);
    }
    @Override
    public boolean temRequisito(){
        return this.requisito != null;
    }
    private String apresentacao(){
        char vocabulo = this.getSexo() == 'm' ? 'o' : 'a';
        return "Olá, eu sou " + vocabulo +" " + this.getNome()+". ";
    }


}