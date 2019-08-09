import java.util.*;

public class Member {

    private List<Boolean> gens;

    private int fitness;

    public Member(List<Boolean> gens) {
        this.gens = gens;
    }

    public List<Boolean> getGens() {
        return gens;
    }

    public void setGens(List<Boolean> gens) {
        this.gens = gens;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public static Member generateRandomMember(int memberGenSize) {
        List<Boolean> gens = new ArrayList<>();
        for(int genIndex = 0; genIndex < memberGenSize; genIndex++){
            // 50 % chance of getting true
            gens.add(new Random().nextInt() % 2 == 0);
        }
        return new Member(gens);
    }

    public void mutate(double mutationRate) {
        for(Boolean gen : this.gens){
           if(new Random().nextDouble() <= mutationRate) {
                gen = !gen;
           }
        }
    }

    public int[] getGensAsArray() {
        int[] solution = new int[gens.size()];
        for (int i = 0; i < gens.size(); i++)
            if (gens.get(i))
                solution[i] = 1;
            return solution;
    }
}
