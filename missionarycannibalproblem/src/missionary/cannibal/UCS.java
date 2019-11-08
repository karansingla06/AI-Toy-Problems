package missionary.cannibal;

public class UCS {
	 
	int nodeNo;
    StateFormulation[] stateArray;
    StateFormulation initialState;
    BFS obj;

    public UCS(StateFormulation initialState) {
    	 obj= new BFS(initialState); 
	    }
    
	public void printPath() {
		obj.printPath();
	}
}
