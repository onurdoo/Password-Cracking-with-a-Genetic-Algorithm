package ProgrammingAssignment2;


public class Chromosome implements Comparable<Chromosome> {
    char[] genesArray;
    int chromosomeLength;
    int fitnessScore;

    public Chromosome(int chromosomeLength) {
        this.chromosomeLength = chromosomeLength;
        genesArray = new char[chromosomeLength];
    }

    public Chromosome() {
        this.genesArray = null;
        this.chromosomeLength = 0;
    }

    public void calculateFitness(String secretCode) {
        for (int i = 0; i < secretCode.length(); i++) {
            if (genesArray[i] != secretCode.charAt(i)) {
                fitnessScore++;
            }
        }

    }

    @Override
    public int compareTo(Chromosome o) {
        return this.fitnessScore - o.fitnessScore;
    }
}

