// Classe a respeito do ambiente, que representa uma localização no cenário do jogo
// Conectando outros ambientes através das saídas, para cada direção é referenciado um ambiente vizinho
// Ou null caso não haja ambiente naquela direção
public class Ambiente 
{
    public String descricao;
    public Ambiente saidaNorte;
    public Ambiente saidaSul;
    public Ambiente saidaLeste;
    public Ambiente saidaOeste;

    // Construtor para criar um ambiente com a descrição passada
    public Ambiente(String descricao) 
    {
        this.descricao = descricao;
    }

    // Método para definir as saídas do ambiente
    // Caso não tenha saída definida, é setado como null
    public void ajustarSaidas(Ambiente norte, Ambiente leste, Ambiente sul, Ambiente oeste) 
    {
        if(norte != null)
            saidaNorte = norte;
        if(leste != null)
            saidaLeste = leste;
        if(sul != null)
            saidaSul = sul;
        if(oeste != null)
            saidaOeste = oeste;
    }

    // Método que retorna a descrição do ambiente
    public String getDescricao()
    {
        return descricao;
    }

}
