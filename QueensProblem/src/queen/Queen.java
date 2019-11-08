package queen;

public class Queen {

	private int row;
	private int col;

	public Queen(int r, int c){
		row = r;
		col = c;
	}

	public boolean QueenCanAttack(Queen q) {
		return row == q.returnRow() ||
			   col == q.returnCol() ||
			   Math.abs(col - q.returnCol()) == Math.abs(row - q.returnRow());
	}

	public void downMove(int spaces) {
		row = (row + spaces) % Node.returnSize();
	}

	public void setRow(int ro){
		row = ro;
	}

	public int returnRow(){
		return row;
	}

	public void setCol(int c){
		col = c;
	}

	public int returnCol(){
		return col;
	}
	
	public String toString(){
		return "(" + row + ", " + col + ")";
	}
}