import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Population {

    private List<Member> members;
    private int memberGenSize;

    public Population(int memberGenSize) {
        this.members = new ArrayList<>();
        this.memberGenSize = memberGenSize;
    }


    public static Population generateInitialPopulation(Knapsack knapsack, int populationSize) {
        int memberGenSize = knapsack.getItems().size();
        Population population = new Population(memberGenSize);
        for (int memberIndex = 0; memberIndex < populationSize; memberIndex++) {
            Member member = Member.generateRandomMember(memberGenSize);
            member.setFitness(countMemberFitness(member, knapsack));
            population.addMember(member);
        }
        return population;
    }

    public void addMember(Member m) {
        members.add(m);
    }

    public int getPopulationSize() {
        return members.size();
    }

    public Member getMember(int index) {
        if (index < members.size())
            return members.get(index);
        return null;
    }

    public void removeMember(int index){
        if (index < members.size())
            this.members.remove(index);
    }

    public Member getRandomMember() {
        Random random = new Random();
        return members.get(random.nextInt(members.size()));
    }

    public void moveToNextGeneration(List<Member> members) {
        this.members = members;
    }

    public static Integer countMemberFitness(Member member, Knapsack knapsack) {
        int index = 0;
        int fitness = 0;
        int weight = 0;
        for (Boolean gen : member.getGens()) {
            if (gen) {
                weight += knapsack.getItems().get(index).getWeight();
                fitness += knapsack.getItems().get(index).getValue();
            }
            index++;
        }
        if(weight > knapsack.getMaxWeight())
            fitness = 0;
        return fitness;
    }

    public List<Member> getMembers() {
        return members;
    }

    public int getMemberGenSize() {
        return memberGenSize;
    }

    public Member getFittestMember() {
        members.sort(Comparator.comparingInt(Member::getFitness).reversed());
        if(getPopulationSize() > 0)
            return members.get(0);
        return null;
    }
}
