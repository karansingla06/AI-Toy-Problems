package missionary.cannibal;

import java.util.List;
import java.util.Stack;
import java.util.HashMap;

public class DFS {

    int nodeIndex;
    StateFormulation iniState;
    StateFormulation[] stateList;

    public DFS(StateFormulation initialState) {
        this.iniState = initialState;
        stateList = new StateFormulation[1000005];
        nodeIndex = 0;
    }

    public StateFormulation findFinalState() {
        if (iniState.checkGoal()) {
            return iniState;
        }
        nodeIndex = 0;
        Stack<StateFormulation> stack = new Stack<>();
        HashMap<StateFormulation, Integer> map = new HashMap<>();
        map.put(iniState, nodeIndex);
        iniState.setPS(-1);
        stateList[nodeIndex] = iniState;
        stack.push(iniState);

        while (!stack.isEmpty()) {

            StateFormulation u = stack.pop();
            int indexU = map.get(u);

            List<StateFormulation> successors = u.getSuccessors();

            for (StateFormulation v : successors) {

                if (!map.containsKey(v)) {
                    nodeIndex++;
                    v.setPS(indexU);
                    stateList[nodeIndex] = v;

                    if (v.checkGoal()) {
                        return v;
                    }

                    map.put(v, nodeIndex);
                    stack.push(v);
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

        while (!s.equals(iniState)) {
            str[t] = s.toString();
            t++;
            s = stateList[s.getPS()];
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
