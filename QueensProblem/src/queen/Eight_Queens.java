package queen;

import java.text.NumberFormat;
import java.util.Random;

public class Eight_Queens {

    private static final int noOfTestcases = 100;

	public Eight_Queens() {

	}
	
	public static void main(String[] args){

			int NODE_SIZE=8;
            Node.fixSize(NODE_SIZE);

            Eight_Queens board = new Eight_Queens();

            System.out.println("\nTest for " + NODE_SIZE + " Queens");

            double SteepestAscentHillClimbing_SUM_Successes = 0;
            double SteepestAscentHillClimbing_AVE_Successes = 0;
            double RandomRestartHillClimbing_SUM_Successes = 0;
            double RandomRestartHillClimbing_AVE_Successes = 0;

            System.out.println("Running 100 tests: ");

            for (int currentTest = 1; currentTest <= noOfTestcases; currentTest++) {
            	Queen[] startBoard = board.generateBoard();

                RRHC randomRestartHillClimber
                        = new RRHC(startBoard);
                SAHC steepestAscentHillClimber
                        = new SAHC(startBoard);
                Node randomRestartHillClimbingNode = randomRestartHillClimber.randomRestart();
                Node steepestAscentHillClimbingNode = steepestAscentHillClimber.hillClimbing();

                if(randomRestartHillClimbingNode.getHeuristic() == 0){
                    RandomRestartHillClimbing_SUM_Successes++;
                }

                if (steepestAscentHillClimbingNode.getHeuristic() == 0) {
                    SteepestAscentHillClimbing_SUM_Successes++;
                }
            }

            SteepestAscentHillClimbing_AVE_Successes =
                    SteepestAscentHillClimbing_SUM_Successes / noOfTestcases;
            RandomRestartHillClimbing_AVE_Successes =
                    RandomRestartHillClimbing_SUM_Successes / noOfTestcases;

            NumberFormat fmt = NumberFormat.getPercentInstance();
            fmt.setMinimumFractionDigits(3);
            fmt.setMinimumIntegerDigits(10);

            System.out.println("Finished");
            System.out.println("\nSteepestAscentHillClimbing:"
                    + " SUCCESS = " + SteepestAscentHillClimbing_SUM_Successes
                    + ", FAILED = " + (100-SteepestAscentHillClimbing_SUM_Successes)
                    + ", SUCCESSES_RATE = " + SteepestAscentHillClimbing_AVE_Successes);
            System.out.println("\nRandomRestartHillClimbing :"
                    + " SUCCESS = " + RandomRestartHillClimbing_SUM_Successes
                    + ", FAILED = " + (100-RandomRestartHillClimbing_SUM_Successes)
                    + ", SUCCESSES_RATE = " + RandomRestartHillClimbing_AVE_Successes);

	}

	public Queen[] generateBoard(){
		Queen[] start = new Queen[Node.returnSize()];
		Random gen = new Random();

		for (int i = 0; i < Node.returnSize(); i++) {
			start[i] = new Queen(gen.nextInt(Node.returnSize()), i);
		}
		return start;
	}
}