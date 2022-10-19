package ProgrammingAssignment2;

import java.util.Arrays;
import java.util.Random;

public class Population {
    Random r = new Random();
    int popLength;
    int generationNum;
    Chromosome[] chromosomeArray;
    Chromosome[] parentArray;
    int eliteUnitsNum;

    public Population(int popLength, int generationNum, int eliteUnitsNum) {
        this.popLength = popLength;
        this.generationNum = generationNum;
        chromosomeArray = new Chromosome[popLength];
        parentArray = new Chromosome[20];
        this.eliteUnitsNum = eliteUnitsNum;
    }

    public Population() {
        this.popLength = 0;
        this.generationNum = 0;
        this.chromosomeArray = null;
        this.parentArray = null;
        this.eliteUnitsNum = 0;
    }

    public void generateFirstGeneration(int len, String secretCode) {

        char[] character_list = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '!', '"', '#', '$', '%', '&',  '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', ']', '^', '_', '`', '{', '|', '}', '~', ' '};
        for (int i = 0; i < popLength; i++) {
            Chromosome newChromosome = new Chromosome(len);
            for (int j = 0; j < newChromosome.chromosomeLength; j++) {
                newChromosome.genesArray[j] = character_list[r.nextInt(character_list.length)];
            }
            newChromosome.calculateFitness(secretCode);
            chromosomeArray[i] = newChromosome;
        }
    }

    // selection
    public void selectParents() {
        Chromosome[] tmp = chromosomeArray;
        Arrays.sort(chromosomeArray);
        System.arraycopy(tmp, 0, parentArray, 0, 20);


    }

    //breeding logic
    public Chromosome breed(Chromosome parent1, Chromosome parent2) {

        Chromosome child = new Chromosome(chromosomeArray[0].chromosomeLength);

        int geneA = (int) (Math.random() * chromosomeArray[0].chromosomeLength);
        int geneB = (int) (Math.random() * chromosomeArray[0].chromosomeLength);

        int startGene = Math.min(geneA, geneB);
        int endGene = Math.max(geneA, geneB);

        for (int i = 0; i < chromosomeArray[0].chromosomeLength; i++) {
            if (i < startGene || i > endGene) {
                child.genesArray[i] = parent1.genesArray[i];
            } else {
                child.genesArray[i] = parent2.genesArray[i];
            }
        }
        return child;
    }

    // breeding and elitism
    public void createChildren(String secretCode) {
        Chromosome[] children = new Chromosome[popLength];


        for (int i = 0; i < eliteUnitsNum + 1; i++) {
            children[i] = parentArray[i];
        }

        for (int i = eliteUnitsNum; i < popLength; i++) {
            //selection and breeding
            Chromosome parent1 = parentArray[(int)(Math.random() * 20)];
            Chromosome parent2 = parentArray[(int)(Math.random() * 20)];

            children[i] = breed(parent1,parent2);

        }
        chromosomeArray = children;


    }

    //mutation
    public void mutation(String secretCode) {
        char[] character_list = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '!', '"', '#', '$', '%', '&',  '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', ']', '^', '_', '`', '{', '|', '}', '~', ' '};
        for (int i = 0; i < popLength; i++) {
            if (Math.random() < 0.1) { //if random number lower than .1 do mutation
                int mutatedPosition = (int) (Math.random() * chromosomeArray[0].chromosomeLength);
                char mutation = character_list[r.nextInt(character_list.length)];
                chromosomeArray[i].genesArray[mutatedPosition] = mutation;

            }
            chromosomeArray[i].calculateFitness(secretCode);
        }
        generationNum++;
    }
}
