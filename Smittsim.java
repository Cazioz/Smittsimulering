import java.util.*;
import java.util.stream.*;
import java.util.Arrays;

public class Smittsim {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Do you wish to use default values? yes/no: ");
		
        if (in.next().equals("yes")) {
            // run simulation with standard values
			Default.runDefault();
        } else {
            Inputs.getParameters();
        }
		
    }
}