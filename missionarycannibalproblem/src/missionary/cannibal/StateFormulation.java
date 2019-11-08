package missionary.cannibal;

import java.util.ArrayList;
import java.util.List;

public class StateFormulation {

    int parentState;
    int MissionaryOnLeft;
    int MissionaryOnRight;
    int CannibalOnLeft;
    int CannibalOnRight;
    int boatCapacity;
    int side;
    

    public StateFormulation(int MissionaryOnLeft, int CannibalOnLeft, int side, int MissionaryOnRight, int CannibalOnRight,
            int boatCapacity) {

        this.MissionaryOnLeft = MissionaryOnLeft;
        this.CannibalOnLeft = CannibalOnLeft;
        this.MissionaryOnRight = MissionaryOnRight;
        this.CannibalOnRight = CannibalOnRight;
        this.boatCapacity = boatCapacity;
        this.side = side;
    }

    public boolean validState() {
        if (MissionaryOnLeft >= 0 && CannibalOnLeft >= 0 && MissionaryOnRight >= 0 && CannibalOnRight >= 0
                && (MissionaryOnLeft == 0 || MissionaryOnLeft >= CannibalOnLeft)
                && (MissionaryOnRight == 0 || MissionaryOnRight >= CannibalOnRight)) {
            return true;
        }
        return false;
    }

    public boolean checkGoal() {
        if (MissionaryOnLeft == 0 && CannibalOnLeft == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof StateFormulation)) {
            return false;
        }

        StateFormulation s = (StateFormulation) obj;
        return (s.CannibalOnLeft == CannibalOnLeft && s.MissionaryOnLeft == MissionaryOnLeft
                && s.side == side && s.CannibalOnRight == CannibalOnRight
                && s.MissionaryOnRight == MissionaryOnRight);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.MissionaryOnLeft;
        hash = 43 * hash + this.MissionaryOnRight;
        hash = 43 * hash + this.CannibalOnLeft;
        hash = 43 * hash + this.CannibalOnRight;
        hash = 43 * hash + this.side;
        return hash;
    }

    public List<StateFormulation> getSuccessors() {
        List<StateFormulation> successors = new ArrayList<>();
        generateSuccessors(successors);
        return successors;
    }

    public void generateSuccessors(List<StateFormulation> successors) {
        if (side == Const.LEFT) {
            for (int i = 0; i <= MissionaryOnLeft; i++) {
                for (int j = 0; j <= CannibalOnLeft; j++) {
                    // (i == 0 || i >= j) ---> if i is 0, no check is needed. otherwise, i >= j is a must
                    if ((i + j) != 0 && ((i + j) <= boatCapacity) && (i == 0 || i >= j)) {
                        StateFormulation tem = new StateFormulation(MissionaryOnLeft - i, CannibalOnLeft - j, Const.RIGHT, MissionaryOnRight + i,
                                CannibalOnRight + j, boatCapacity );
                        if (tem.validState()) {
                            successors.add(tem);
                        }
                    }
                }
            }
        } else if (side == Const.RIGHT) {
            for (int i = 0; i <= MissionaryOnRight; i++) {
                for (int j = 0; j <= CannibalOnRight; j++) {

                    if ((i + j) != 0 && ((i + j) <= boatCapacity)) {
                        StateFormulation tem = new StateFormulation(MissionaryOnLeft + i, CannibalOnLeft + j, Const.LEFT, MissionaryOnRight - i,
                                CannibalOnRight - j, boatCapacity);

                        if (tem.validState()) {
                            successors.add(tem);
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
            return "(" + MissionaryOnLeft + "," + CannibalOnLeft + ","+side+ ","
                    + MissionaryOnRight + "," + CannibalOnRight + ")";
    }


    public int getPS() {
        return parentState;
    }
    
    public void setPS(int parentState) {
        this.parentState = parentState;
    }

}
