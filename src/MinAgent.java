import java.util.ArrayList;
 
public class MinAgent {
	GameBoard Board; //Used to calculate the next step
	Square bestAction = null;
	static int visitedNodes = 0;
	
	public MinAgent(GameBoard board) {
		this.Board = new GameBoard(board);
	}
	
	public Square MakeTurnDecision() {
		ArrayList<Square> actions = Board.getEmptySquares();
		double minValue = Double.POSITIVE_INFINITY;
		for(Square action : actions) {
			double actionValue = MinMaxAlgorithm(GameBoard.GetResult(Board, action, 'O'));
			if(minValue > actionValue) {
				minValue = actionValue;
				bestAction = action;
			}
				
		}
		return bestAction;
	}
	
	
	public double MinMaxAlgorithm(GameBoard board) {
		visitedNodes++;
		
		if(board.isTerminated())
			return board.getValue();
		
		
		if(board.isMinTurn()) {
			double value = Double.POSITIVE_INFINITY;
			ArrayList<Square> actions = board.getEmptySquares();
			for(Square action : actions) {
				value = Math.min(value, MinMaxAlgorithm(GameBoard.GetResult(board, action, 'O')));
			}
			
			return value;
		}
		else {//max turn calc
			double value = Double.NEGATIVE_INFINITY;
			ArrayList<Square> actions = board.getEmptySquares();
			for(Square action : actions) {
				value = Math.max(value, MinMaxAlgorithm(GameBoard.GetResult(board, action, 'X')));
			}
			
			return value;
		}
	}
	
	
	

}
