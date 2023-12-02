public abstract class Personagem {
    private String nome;
    private char sexo;
    public Personagem(String nome, char sexo) {
        this.nome = nome;
        this.sexo = Character.toLowerCase(sexo);
    }

    public String getNome(){
        return this.nome;
    }
    public char getSexo(){
        return this.sexo;
    }

}
