package queen;

import java.util.ArrayList;
import java.util.Random;

public class Node implements Comparable<Node>{

	static private int SIZE = 10;
	private Queen[] st;
	private ArrayList<Node> neighbr;
	private int h;

	public Node() {
		st = new Queen[SIZE];
		neighbr = new ArrayList<>();
		h = 0;
	}

	public Node(Node n) {
		st = new Queen[SIZE];
		neighbr = new ArrayList<>();
		for (int j = 0; j < SIZE; j++) {
			st[j] = new Queen(n.st[j].returnRow(), n.st[j].returnCol());
		}
		h = 0;
	}

	public Node(Queen[] q) {
		st = new Queen[SIZE];
		for (int i = 0; i < SIZE; i++) {
			st[i] = q[i];
		}
		neighbr = new ArrayList<>();
		h = 0;
	}

	static public int returnSize() {
		return SIZE;
	}

	static public void fixSize(int s) {
		SIZE = s;
	}

	public ArrayList<Node> generateNeighbouringState(Node startS){

		int count=0;
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 1; j < SIZE; j++) {
				neighbr.add(count, new Node(startS));
				neighbr.get(count).st[i].downMove(j);
				neighbr.get(count).calculateHeuristic();
				count++;
			}
		}
		
		return neighbr;
	}

	public Node returnRandomNeighbour(Node startState){
		Random gen = new Random();
		
		int col = gen.nextInt(SIZE);
		int d = gen.nextInt(SIZE - 1) + 1;
		
		Node neigh = new Node(startState);
		neigh.st[col].downMove(d);
		neigh.calculateHeuristic();
		
		return neigh;
	}

	public int calculateHeuristic(){
	
		for (int i = 0; i < SIZE - 1; i++){
			for (int j = i + 1; j < SIZE; j++){
				if (st[i].QueenCanAttack(st[j])){
					h++;
				}
			}
		}

		return h;
	}

	public int getHeuristic(){
		return h;
	}

	public int compareTo(Node n){
		if (this.h < n.getHeuristic()) {
			return -1;
		} else if (this.h > n.getHeuristic()) {
			return 1;
		} else {
			return 0;
		}
	}

	public void setState(Queen[] s) {
		for(int j = 0; j < SIZE; j++){
			st[j]= new Queen(s[j].returnRow(), s[j].returnCol());
		}
	}

	public Queen[] returnState(){
		return st;
	}

	public String toString(){

		String res = "";
		String[][] bord = new String[SIZE][SIZE];

		for (int i = 0; i < SIZE; i++)
			for(int j = 0; j < SIZE; j++)
				bord[i][j] = ".";

		for (int i = 0; i < SIZE; i++){
			bord[st[i].returnRow()][st[i].returnCol()]="Q";
		}

		for (int i = 0; i < SIZE; i++){
			for (int j = 0; j < SIZE; j++){
				res += bord[i][j];
			}
			res += "\n";
		}
		
		return res;
	}
}