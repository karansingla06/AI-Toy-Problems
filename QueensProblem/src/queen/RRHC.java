package queen;

public class RRHC {
	private StochasticHC hillClimber;
	private int nodesGenerated;
	private Node start;

	public RRHC(Queen[] startBoard) {
		hillClimber = new StochasticHC(startBoard);
		nodesGenerated = 0;
	}

	public Node randomRestart() {
		Node currentNode = hillClimber.returnStartNode();
		setStartNode(currentNode);
		int heuristic = currentNode.getHeuristic();
				
		while(heuristic!=0){
			Node nextNode = hillClimber.hillClimbing();
			nodesGenerated += hillClimber.returnNodesGenerated();
			heuristic = nextNode.getHeuristic();

			if(heuristic!=0){
				hillClimber = new StochasticHC();
			}else
				currentNode = nextNode;
		}
		return currentNode;
	}

	public void setStartNode(Node n){
		start = n;
	}

	public Node getStartNode(){
		return start;
	}

	public int getNodesGenerated(){
		return nodesGenerated;
	}
}