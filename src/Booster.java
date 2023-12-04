import java.util.Random;

public class Booster extends Item{
    private int bonusMovimento;

    public Booster(String nome, String descricao){
        super(nome,descricao);
        this.bonusMovimento = 1;
        Random random = new Random();
        this.bonusMovimento = random.nextInt(4) + 1;
    }

    public int getBonusMovimento() {
        return bonusMovimento;
    }



}
