package ProgrammingAssignment2;



public class Main {

    public static void main(String[] args) {

        String secretCode1 = "Deep Learning 2022";
        String secretCode2 = "DeepLearning2022";
        int totalGenNum1 = 0;
        int totalGenNum2 = 0;
        String crackedCode1 = "";
        String crackedCode2 = "";


        for (int i = 0; i < 3; i++) {
            //run GA
            long start = System.currentTimeMillis();
            Population population = new Population(20, 1, 2);
            // first generation created randomly
            population.generateFirstGeneration(secretCode1.length(), secretCode1);
            while (true) {

                population.selectParents();
                population.createChildren(secretCode1);
                population.mutation(secretCode1);
                Chromosome bestChild = population.chromosomeArray[0];
                for (Chromosome ch : population.chromosomeArray) {
                    if (ch.fitnessScore < bestChild.fitnessScore) {
                        bestChild = ch;
                    }
                }
                crackedCode1 = String.valueOf(bestChild.genesArray);

                if (bestChild.fitnessScore == 0) { // while check condition
                    long stop = System.currentTimeMillis();
                    System.out.println((i + 1) + ". iteration generation num is: " + population.generationNum + " algorithm performed in "+ ((stop-start)/1000f) +" milliseconds");
                    totalGenNum1 += population.generationNum;
                    break;
                }

            }
        }
        System.out.println("Cracked code was: " + crackedCode1);
        System.out.println("Its population average is: " + totalGenNum1 / 3);
        System.out.println("*****************************************************************************************");

        for (int i = 0; i < 3; i++) {
            long start = System.currentTimeMillis();
            Population population = new Population(20, 1, 2);
            // first generation created randomly
            population.generateFirstGeneration(secretCode2.length(), secretCode2);
            while (true) {

                population.selectParents();
                population.createChildren(secretCode2);
                population.mutation(secretCode2);
                Chromosome bestChild = population.chromosomeArray[0];
                for (Chromosome ch : population.chromosomeArray) {
                    if (ch.fitnessScore < bestChild.fitnessScore) {
                        bestChild = ch;
                    }
                }
                crackedCode2 = String.valueOf(bestChild.genesArray);

                if (bestChild.fitnessScore == 0) { // while condition check
                    long stop = System.currentTimeMillis();
                    System.out.println((i + 1) + ". iteration generation num is: " + population.generationNum + " algorithm performed in "+ ((stop-start)/1000f) +" milliseconds");
                    totalGenNum2 += population.generationNum;
                    break;
                }

            }
        }
        System.out.println("Cracked code was: " + crackedCode2);
        System.out.println("Its population average is: " + totalGenNum2 / 3);

    }
}
