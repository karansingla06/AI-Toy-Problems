package queen;

import java.util.ArrayList;
import java.util.Random;

public class StochasticHC {

	private Queen[] startS;
	// start state
	private Node strt;
	private int generatedNodes;

	public StochasticHC() {
		strt = new Node();
		startS = new Queen[Node.returnSize()];
		startState();
		generatedNodes = 0;
	}

	public StochasticHC(Queen[] s) {
		strt = new Node();
		startS = new Queen[Node.returnSize()];
		for (int i = 0; i < s.length; i++){
			startS[i] = new Queen(s[i].returnRow(), s[i].returnCol());
		}
		strt.setState(startS);
		strt.calculateHeuristic();
		
		generatedNodes = 0;
	}

	public void startState() {
		Random random = new Random();
		for (int i = 0; i < Node.returnSize(); i++){
			startS[i] = new Queen(random.nextInt(Node.returnSize()), i);
		}
		strt.setState(startS);
		strt.calculateHeuristic();
	}

	public Node hillClimbing() {

		Node currNode = strt;
		
		while (true) {
			ArrayList<Node> succsrs = currNode.generateNeighbouringState(currNode);
			generatedNodes += succsrs.size();
			
			Node nextNode = null;

			ArrayList<Node> betterSucc = new ArrayList<>();

			for (int i = 0; i < succsrs.size(); i++) {
				if (succsrs.get(i).compareTo(currNode) < 0) {
					betterSucc.add(succsrs.get(i));
				}
			}

			if (!betterSucc.isEmpty()) {
				Random random = new Random();
				nextNode = betterSucc.get(random.nextInt(betterSucc.size()));
			}
			
			if(nextNode == null) {
				return currNode;
			}

			currNode = nextNode;
		}
	}

	public Node returnStartNode(){
		return strt;
	}

	public int returnNodesGenerated(){
		return generatedNodes;
	}
}