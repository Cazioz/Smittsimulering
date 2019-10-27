import java.security.SecureRandom; 
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;

public class Simulation {
    static int day;
    static int numOfInfected;
    static int totalInfected;
	static ArrayList<String> sickDailyArray = new ArrayList<String>();
	static ArrayList<String> recoveredDailyArray = new ArrayList<String>();
	static ArrayList<String> infectedDailyArray = new ArrayList<String>();
	static ArrayList<String> diseasedDailyArray = new ArrayList<String>();
	static int recoveredDaily;
	static int infectedDaily;
	static int diseasedDaily;
    static int totalDeaths;

    public static void runSim(byte[] seed, int sizeOfPop, double chanceOfInfection, int minDays, int maxDays,
            double deathChance, int initiallySick, Position initiallySickArray[], Population population,int infday,int disday,int recday,int sickday,int totinf,int totdis) {
        SecureRandom rand = new SecureRandom(seed);

        // instantiate all variables
        day = 1;
        numOfInfected = initiallySick;
        totalInfected = 0;
        totalDeaths = 0;

        while (numOfInfected > 0) {
			recoveredDaily = 0;
			infectedDaily = 0;
			diseasedDaily = 0;
            // check if healthy/immune/dead
            // check if sick, might become immune or else die, if survives - might infect others
            for (int i = 0; i < sizeOfPop; i++) {
                for (int j = 0; j < sizeOfPop; j++) {
                    // Check if person is healthy, immune or dead
                    if (!population.humanarray[i][j].getIsSick() || population.humanarray[i][j].getIsImmune()
                            || !population.humanarray[i][j].getIsAlive()) {
                        // Healthy, immune or dead person won't do anything
                    }
                    // if person is sick, check if it has survived long enough to become immune
                    else if (population.humanarray[i][j].getIsAlive() && population.humanarray[i][j].getIsSick() && ((population.humanarray[i][j].getSickDays() - 1) <= 0)) {
                        //System.out.println("Human at: " + i + ", " + j + "became immune");
						recoveredDaily++;
						population.humanarray[i][j].setIsImmune(true);
                        population.humanarray[i][j].setIsSick(false);
                        numOfInfected--;
                    }
                    // else if person is sick and wont become immune check if it survives the day
                    else if (population.humanarray[i][j].getIsAlive() && population.humanarray[i][j].getIsSick()) {
                        // check if infected person dies today
                        double chanceOfDeath = rand.nextDouble();
                        if (chanceOfDeath < (deathChance / 100.0)) {
                            //System.out.println("Human at: " + i + ", " + j + " died with chance: " + chanceOfDeath);
                            population.humanarray[i][j].setIsAlive(false);
							population.humanarray[i][j].setIsSick(false);
							diseasedDaily++;
                            totalDeaths++;
                            numOfInfected--;
                        }
                        // infected person survived, now might infect others if it is not it's first day
                        // of being sick
                        if (population.humanarray[i][j].getIsAlive() && population.humanarray[i][j].getInfectionDay() != day) {
                            infect(seed, sizeOfPop, i, j, chanceOfInfection, day, population);
                            population.humanarray[i][j].setsickDays(population.humanarray[i][j].getSickDays() - 1);
                        }
                    }
                }
            }
					
            // graphical representation, "." is a healthy person, "0" is a diseased person
            // , "1" is an infected person and "2" is an immune person
            for (int i = 0; i < sizeOfPop; i++) {
                for (int j = 0; j < sizeOfPop; j++) {
                    if (population.humanarray[i][j].getIsAlive() && !population.humanarray[i][j].getIsSick() && !population.humanarray[i][j].getIsImmune()) {
                        System.out.print(" . ");
                    } else if (population.humanarray[i][j].getIsAlive() && population.humanarray[i][j].getIsSick()) {
                        System.out.print(" 1 ");
                    } else if (population.humanarray[i][j].getIsAlive() && population.humanarray[i][j].getIsImmune()) {
                        System.out.print(" 2 ");
                    } else {
                        System.out.print(" 0 ");
                    }
                }
                System.out.println("");
            }
			
			// saving all the data
			sickDailyArray.add(String.valueOf(numOfInfected));
			recoveredDailyArray.add(String.valueOf(recoveredDaily));
			infectedDailyArray.add(String.valueOf(infectedDaily));
			diseasedDailyArray.add(String.valueOf(diseasedDaily));
			day++;
			
			// a wait timer so that the graphics are somewhat understandable
			// comment this out if you do not wish to wait for the program (ie make it faster)
			// beware that graphics will glitch a lot without this timer
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                System.out.println("failed");
                e.printStackTrace();
            }

        }
		
		// print out data
		if(infday == 1) {
		System.out.print("Infected each day: ");
		System.out.println(infectedDailyArray);
		}
		if(disday == 1) {
		System.out.print("Diseased each day: ");
		System.out.println(diseasedDailyArray);
		}
		if(recday == 1) {
		System.out.print("Recovered each day: ");
		System.out.println(recoveredDailyArray);
		}
		if(sickday == 1) {
		System.out.print("Sick each day: ");
		System.out.println(sickDailyArray);
		}
		if(totdis == 1) {
		System.out.print("Total Deaths: ");
		System.out.println(totalDeaths);
		}
		if(totinf == 1) {
		System.out.print("Total infected: ");
		System.out.println(totalInfected + initiallySick);
		}
		
		
		// if you want to save each run to a txt file, you may use this code
		// very useful for reporting results
		/* 
		try (FileWriter fstream = new FileWriter("data.txt",true)) {
			try (PrintWriter out = new PrintWriter(fstream)) {
			out.println(totalInfected + initiallySick);
			}
			catch (Exception e) {
				System.out.print("error");
			}
		}
		catch (Exception e) {
			System.out.print("error");
		}
		*/

		
		if ((totalInfected + initiallySick) > ((sizeOfPop*sizeOfPop) / 2)) {
			System.out.println("Epidemic");
		}

    }


	// this function is used to infect others randomly (seeded)
    public static void infect(byte[] seed, int sizeOfPop, int x, int y, double chanceOfInfection, int day,
            Population pop) {
        // infects randomly
        SecureRandom random = new SecureRandom(seed);
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (((x + i) >= 0) && ((x + i) < sizeOfPop)) {
                    if (((y + j) >= 0) && ((y + j) < sizeOfPop)) {
                        // System.out.println("x,y " + x + ", " + y + " i,j " + i + "," + j);
						// simply checks whether the person is alive, already sick or immune
						// if person is alive, healthy and not immune it might get infected
                        if (!pop.humanarray[x + i][y + j].getIsSick() && pop.humanarray[x + i][y + j].getIsAlive() && !pop.humanarray[x + i][y + j].getIsImmune()) {

                            double chance = random.nextDouble();
                            //System.out.println("tries to infect with chance: " + chance);
                            if (chance < (chanceOfInfection / 100.0)) {
                                pop.humanarray[x + i][y + j].setInfectionDay(day);
                                pop.humanarray[x + i][y + j].setIsSick(true);
								infectedDaily++;
                                numOfInfected++;
                                totalInfected++;
                            }
                        }
                    }
                }
            }
        }
    }
}
