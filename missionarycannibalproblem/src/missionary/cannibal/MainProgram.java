package missionary.cannibal;

import java.util.Scanner;


public class MainProgram {
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Initial State: (3,3,L,0,0)");
        System.out.println("Boat Capacity: 2");
        StateFormulation initialState = new StateFormulation(3,3,Const.LEFT,0,0,2);
        initialState.setPS(-1);
        
        System.out.println("\nUsing BFS-");
        BFS bfs = new BFS(initialState);
        long startTime= System.nanoTime();
        bfs.printPath();
        long endTime= System.nanoTime();
        System.out.println("Time taken by BFS: "+ (endTime-startTime));

        System.out.println("\nUsing DFS-");
        DFS dfs = new DFS(initialState);
        startTime= System.nanoTime();
        dfs.printPath();
        endTime= System.nanoTime();
        System.out.println("Time taken by DFS: "+ (endTime-startTime));

        System.out.println("\nUsing Uniform Cost Search-");
        UCS ucs = new UCS(initialState);
        startTime= System.nanoTime();
        ucs.printPath();
        endTime= System.nanoTime();
        System.out.println("Time taken by UCS: "+ (endTime-startTime));
    }
}
