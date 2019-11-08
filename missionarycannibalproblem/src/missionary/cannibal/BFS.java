package missionary.cannibal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {

    int nodeNo;
    StateFormulation[] stateArray;
    StateFormulation initialState;

    public BFS(StateFormulation initialState) {
        this.initialState = initialState;
        stateArray = new StateFormulation[1000005];
        nodeNo = 0;
    }

    public StateFormulation findFinalState() {

    	if (initialState.checkGoal()) {
            return initialState;
        }
        nodeNo = 0;
        Queue<StateFormulation> q = new LinkedList<>();
        HashMap<StateFormulation, Integer> map = new HashMap<>();
        map.put(initialState, nodeNo);
        initialState.setPS(-1);
        stateArray[nodeNo] = initialState;
        q.add(initialState);

        while (!q.isEmpty()) {
            StateFormulation u = q.poll();
            int indexU = map.get(u);
            List<StateFormulation> successors = u.getSuccessors();
            
            for (StateFormulation v : successors) {
                if (!map.containsKey(v)) {
                    nodeNo++;
                    v.setPS(indexU);
                    stateArray[nodeNo] = v;

                    if (v.checkGoal()) {
                        return v;
                    }
                    map.put(v, nodeNo);
                    q.add(v);
                }
            }
        }
        return null;
    }

    public void printPath() {
        int t = 0;
        StateFormulation s = findFinalState();

        if (s == null) {
            System.out.println("No solution found.");
            return;
        }

        String[] str = new String[100005];
        while (!s.equals(initialState)) {
            str[t] = s.toString();
            t++;
            s = stateArray[s.getPS()];
        }
        str[t] = s.toString();
        System.out.println("It takes " + t + " steps.");

        for (int i = t; i >= 0; i--) {
            System.out.print(str[i]);
            if (i != 0) {
                System.out.print(" --> ");
            }
        }
        System.out.println("");
    }
}
