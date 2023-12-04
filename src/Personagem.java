/**
 *  Classe abstrata Personagem, de onde Npc e PersonagemPrincipal
 *  irão herdar
 *
 * @author  Gabriel, Gabriela, Gustavo e Marcus
 */

public abstract class Personagem {
    private String nome;
    private char sexo;

    /**
     *  Construtor da classe Personagem
     */
    public Personagem(String nome, char sexo) {
        this.nome = nome;
        this.sexo = Character.toLowerCase(sexo);
    }

    /**
     *  Método para retornar o atributo nome
     */
    public String getNome(){
        return this.nome;
    }

    /**
     *  Método para retornar o atributo sexo
     */
    public char getSexo(){
        return this.sexo;
    }


}
