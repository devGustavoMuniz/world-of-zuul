import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe Ambiente - um ambiente em um jogo adventure.
 *
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.  
 *
 * Um "Ambiente" representa uma localizacao no cenario do jogo. Ele eh
 * conectado aos outros ambientes atraves de saidas. As saidas sao
 * nomeadas como norte, sul, leste e oeste. Para cada direcao, o ambiente
 * guarda uma referencia para o ambiente vizinho, ou null se nao ha
 * saida naquela direcao.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */
// Classe a respeito do ambiente, que representa uma localização no cenário do jogo
// Conectando outros ambientes através das saídas, para cada direção é referenciado um ambiente vizinho
// Ou null caso não haja ambiente naquela direção
public class Ambiente
{
    private String descricao;

    private HashMap<String, Ambiente> saidas;

    private ArrayList<Npc> npcs;

    // Construtor para criar um ambiente com a descrição passada
    public Ambiente(String descricao) 
    {
        this.descricao = descricao;
        saidas = new HashMap<String, Ambiente>();
        npcs = new ArrayList<Npc>();
    }

    // Método para adicionar os Npcs
    public void addNpc(Npc npc){
        try{
            if (!npcs.contains(npc)) {
                this.npcs.add(npc);
            }
        } catch (Exception e) {
            System.out.println("Erro ao adicionar npc. " + e);
        }
    }

    // Método para definir as saídas do ambiente
    // Caso não tenha saída definida, é setado como null
    public void ajustarSaidas(String direcao, Ambiente ambiente) {
        saidas.put(direcao, ambiente);
    }



    // Método que retorna a descrição do ambiente
    public String getDescricao()
    {
        return descricao;
    }

    public Ambiente getAmbiente(String direcao){
        return saidas.get(direcao);
    }

    public String getSaidasAmbiente(){
        String retorno = "";

        for(String direcao : saidas.keySet()){
            retorno += direcao + " ";
        }
        return retorno;

    }

}
