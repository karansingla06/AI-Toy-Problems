package queen;


import java.util.ArrayList;
import java.util.Random;

public class SAHC {

    private Queen[] startS;
    private Node strt;
    private int generatedNodes;

    public SAHC() {
        strt = new Node();
        startS = new Queen[Node.returnSize()];
        startS();
        generatedNodes=0;
    }

    public SAHC(Queen[] s) {
        strt = new Node();
        startS = new Queen[Node.returnSize()];
        for (int i = 0; i < s.length; i++){
            startS[i] = new Queen(s[i].returnRow(), s[i].returnCol());
        }
        strt.setState(startS);
        strt.calculateHeuristic();

        generatedNodes = 0;
    }

    public void startS() {
        Random r = new Random();
        for (int i = 0; i < Node.returnSize(); i++){
            startS[i] = new Queen(r.nextInt(Node.returnSize()), i);
        }
        strt.setState(startS);
        strt.calculateHeuristic();
    }

    public Node hillClimbing() {

        Node currNode = strt;

        while (true) {
            ArrayList<Node> successors = currNode.generateNeighbouringState(currNode);
            generatedNodes += successors.size();

            boolean existBetter = false;

            for (int i = 0; i < successors.size(); i++) {
                if (successors.get(i).compareTo(currNode) < 0) {
                    currNode = successors.get(i);
                    existBetter = true;
                }
            }

            if(!existBetter) {
                return currNode;
            }

        }
    }

    public Node getStartNode(){
        return strt;
    }

    public int returnNodesGenerated(){
        return generatedNodes;
    }
}
