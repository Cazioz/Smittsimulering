import java.util.*;
import java.security.SecureRandom; 

public class Inputs {
    // gets all the parameters used to run the simulation
    public static void getParameters() {
		
        Scanner Scanner = new Scanner(System.in);

        System.out.println("Enter a random seed: ");
		int intseed = Scanner.nextInt();
		String strseed = Integer.toString(intseed);
		byte[] seed = strseed.getBytes();

        System.out.print("Size of the population: ");
        int sizeOfPop = Scanner.nextInt();

        System.out.print("Chance that a sick individual infects its neighbor (0-100): ");
        double chanceOfInfection = Scanner.nextDouble();

        System.out.print("Min number of days an individual is sick: ");
        int minDays = Scanner.nextInt();

        System.out.print("Max number of days an individual is sick: ");
        int maxDays = Scanner.nextInt();

        System.out.print("Chance that an infected individual dies (0-100): ");
        double deathChance = Scanner.nextDouble();

        System.out.print("Number of initially infected: ");
        int initiallySick = Scanner.nextInt();

        // Saves position of all initially sick people
        Position initiallySickArray[] = new Position[initiallySick];
        System.out.println("Please enter the position of the infected in the format x y (separated bv space)");
        System.out.println("Please note this is 0-indexed");
        for (int i = 0; i < initiallySick; i++) {
            System.out.println("Enter the position of infected human number " + (i + 1) + ": ");
            int x = Scanner.nextInt();
            int y = Scanner.nextInt();
            initiallySickArray[i] = new Position(x, y);
        }

		System.out.println("Which outdata would you like? 1 for yes, 0 for no, separated by space");
		System.out.println("Infected every day, diseased every day, recovered every day, sick every day, total amount of infected, total amount of diseased");
        int infday = Scanner.nextInt();
		int disday = Scanner.nextInt();
		int recday = Scanner.nextInt();
		int sickday = Scanner.nextInt();
		int totinf = Scanner.nextInt();
		int totdis = Scanner.nextInt();
	
        SecureRandom rand = new SecureRandom(seed);
        int[][] minmaxDays = new int[sizeOfPop][sizeOfPop];
        // this will instantiate all Humans with a random (seeded) amount of days they
        // will be sick (if infected), between (inclusive) minDays and maxDays
        for (int i = 0; i < sizeOfPop; i++) {
            for (int j = 0; j < sizeOfPop; j++) {
                minmaxDays[i][j] = rand.nextInt((maxDays - minDays) + 1) + minDays;
            }
        }
        Population population = new Population(sizeOfPop, minmaxDays);

        // sets the initially sick according to input
        for (int i = 0; i < initiallySick; i++) {
            int x = initiallySickArray[i].getX();
            int y = initiallySickArray[i].getY();
            // System.out.print("x,y: " + x + "," + y);
            population.humanarray[x][y].setInfectionDay(1);
            population.humanarray[x][y].setIsSick(true);
        }
        System.out.println("Starting with values: " + seed + sizeOfPop + chanceOfInfection + minDays + maxDays
                + deathChance + initiallySick);

        Simulation.runSim(seed, sizeOfPop, chanceOfInfection, minDays, maxDays, deathChance, initiallySick,
                initiallySickArray, population, infday, disday, recday, sickday, totinf, totdis);
    }
}