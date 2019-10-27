public class Population {

    Human humanarray[][];

    public Population(int sizeOfPop, int[][] minmaxdays) {
        humanarray = new Human[sizeOfPop][sizeOfPop];
        for (int i = 0; i < sizeOfPop; i++) {
            for (int j = 0; j < sizeOfPop; j++) {
                humanarray[i][j] = new Human(minmaxdays[i][j]);
            }
        }
    }
}