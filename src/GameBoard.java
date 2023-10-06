import java.util.ArrayList;

public class GameBoard {
	private char [][] table = new char[4][4];
	public GameBoard() {
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				table[i][j]='_';
			}
		}
	}
	public GameBoard(GameBoard b) { //Copy Constructor for MinAgent (don't ruin the original gameboard).
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				table[i][j]=b.getSquare(i, j);
			}
		}
	}
	
	
	public char getSquare(int i,int j) {
		return table[i][j];
	}
	
	public boolean add(Square s,char value) {
		if(table[s.getI()][s.getJ()]=='_') {
			table[s.getI()][s.getJ()]=value;
			return true;
		}
		return false;
	}
	
	public void print() {
		System.out.println("board:");
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				System.out.print(table[i][j]);
				if(j<=2) {
					System.out.print(' ');
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public ArrayList<Square> getEmptySquares() {
		//Can be empty
		ArrayList<Square> emptyList = new ArrayList<Square>();
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(table[i][j]=='_')
					emptyList.add(new Square(i,j));
			}
		}
		
		return emptyList;
	}
	
	public boolean isTerminated() {
		//Returns game's status
		
		if(checkForWin('X') || checkForWin('O'))
			return true;
		else {
			if(getEmptySquares().isEmpty()) {
				return true;
			}else {
				return false;
			}
		}
	}
	public int getValue() {
		//This function will only be called when isTerminated() returns true
		if(checkForWin('X'))
			return 1;
		else if(checkForWin('O')) {
			return -1;
		}else{ //Draw
			return 0;
		}
	}
	
	public static GameBoard GetResult(GameBoard board,Square s,char c) {
		board.add(s, c);
		return board;
	}
	
	public boolean isMinTurn() {
		int countX = 0;
		int countO = 0;
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(table[i][j]=='X')
					countX++;
				else if(table[i][j]=='O')
					countO++;
			}
		}
		//There always be a bigger counter, if they are equal than its a draw
		return countO<countX;
	}
	//INFO: HELPER METHODS TO CHECK GAME STATE
	
	public boolean checkForWin(char c ) {
		boolean flag = false;
		for(int i=0;i<4;i++) {
			flag = flag || checkRow(i, c);
		}
		for(int j=0;j<4;j++) {
			flag = flag || checkCol(j, c);
		}
		flag = flag || checkDigonals(c);
		
		return flag;
	}
	public boolean checkRow(int i,char c) {
		 for(int j=0;j<4;j++) {
			 if(c!=table[i][j])
				 return false;
		 }
		 return true;
	}
	
	public boolean checkCol(int j,char c) {
		 for(int i=0;i<4;i++) {
			 if(c!=table[i][j])
				 return false;
		 }
		 return true;
	}
	
	public boolean checkDiagonal1(char c) {
		int i=0,j=0;
		while(i<4&&j<4) {
			if(table[i][j]!=c)
				return false;
			i++;
			j++;
		}
		 return true;
	}
	public boolean checkDiagonal2(char c) {
		int i=0,j=3;
		while(i<4&&j>0) {
			if(table[i][j]!=c)
				return false;
			i++;
			j--;
		}
		 return true;
	}
	public boolean checkDigonals(char c) {
		return checkDiagonal1(c)||checkDiagonal2(c);
	}
}
