import java.security.SecureRandom; 

public class Default {
	
	public static void runDefault() {
		byte[] seed = "9949".getBytes();
		SecureRandom rand = new SecureRandom(seed);
        int[][] minmaxDays = new int[40][40];
        // this will instantiate all Humans with a random (seeded) amount of days they
        // will be sick (if infected), between (inclusive) 6 and 9 days
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                minmaxDays[i][j] = rand.nextInt((9 - 6) + 1) + 6;
            }
        }
        Population population = new Population(40, minmaxDays);	

		Position initiallySickArray[] = new Position[1];
		initiallySickArray[0] = new Position(20, 20);

		// sets the initially sick according to input
        for (int i = 0; i < 1; i++) {
            int x = initiallySickArray[i].getX();
            int y = initiallySickArray[i].getY();
            // System.out.print("x,y: " + x + "," + y);
            population.humanarray[x][y].setInfectionDay(1);
            population.humanarray[x][y].setIsSick(true);
        }
		
        Simulation.runSim(seed, 40, 10.0, 6, 9, 5.0, 1, initiallySickArray, population, 1, 1, 1, 1, 1, 1);		
	}
	public static void runSomeDefault(byte[] seed, double chanceOfInfection) {
		SecureRandom rand = new SecureRandom(seed);
        int[][] minmaxDays = new int[40][40];
        // this will instantiate all Humans with a random (seeded) amount of days they
        // will be sick (if infected), between (inclusive) 6 and 9 days
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                minmaxDays[i][j] = rand.nextInt((9 - 6) + 1) + 6;
            }
        }
        Population population = new Population(40, minmaxDays);	

		Position initiallySickArray[] = new Position[1];
		initiallySickArray[0] = new Position(20, 20);

		// sets the initially sick according to input
        for (int i = 0; i < 1; i++) {
            int x = initiallySickArray[i].getX();
            int y = initiallySickArray[i].getY();
            // System.out.print("x,y: " + x + "," + y);
            population.humanarray[x][y].setInfectionDay(1);
            population.humanarray[x][y].setIsSick(true);
        }
		
        Simulation.runSim(seed, 40, chanceOfInfection, 6, 9, 0.0, 1, initiallySickArray, population, 1, 1, 1, 1, 1, 1);		
	}
}
