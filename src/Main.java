import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		GameBoard board = new GameBoard();
		board.print();
		
		if(args.length>0) {
			String [] arr = args[0].split(",");
			if(arr.length==12) {
				AddFromParameters(board,arr);
			}else {
				GenerateRandomNumbersForBoard(board);
			}
		}else {
			GenerateRandomNumbersForBoard(board);
		}
		
		//BOARD IS INITIALISED THUS FAR
		
		
		board.print();
		
		//GAME STARTS:
		
		boolean isMaxTurn = true;
		while(!board.isTerminated()) {
			if(isMaxTurn) {
				boolean isGood=false;
				while(!isGood) {
					System.out.println("choose row (0-3) \n");
					int i = in.nextInt();
					System.out.println("choose column (0-3) \n");
					int j = in.nextInt();
					Square s = new Square(i,j);
					isGood = board.add(s, 'X');
					if(!isGood)
						System.out.println("ERROR: Invalid input, try again with valid input please.");
				}			
			}else {
				System.out.println("AI plays...");
				//MINMAX ALGORITHM FOR MIN
				MinAgent agent  = new MinAgent(board);
				Square s = agent.MakeTurnDecision();
				board.add(s,'O');
			}
			
			isMaxTurn=!isMaxTurn;
			board.print();
		}
		switch(board.getValue()) {
		case 1:
			System.out.println("You win!");
			break;
		case -1:
			System.out.println("AI has won!");
			break;
		case 0:
			System.out.println("DRAW!");
		default: //not supposed to happen
			
			System.out.println("ERROR");
		}
		

		System.out.println("Visited Nodes: "+MinAgent.visitedNodes);
	}
	
	
	
	public static void AddFromParameters(GameBoard board, String[] arr) {
		for(int i=0;i<12;i+=4) {
			board.add(new Square(Integer.parseInt(arr[i]), Integer.parseInt(arr[i+1])),'X');
			board.add(new Square(Integer.parseInt(arr[i+2]), Integer.parseInt(arr[i+3])),'O');
		}
	}
	
	public static void GenerateRandomNumbersForBoard(GameBoard board) {
		int count=0;
		boolean isMax=true;
		while(count<6) {
			int i = utils.getRandomNumber(); //returns number between 0-3
			int j = utils.getRandomNumber(); //returns number between 0-3
			if(isMax) {
				if(board.add(new Square(i,j),'X')) {
					isMax=!isMax;
					count++;
				}
					
			}
			else {
				if(board.add(new Square(i,j),'O')) {
					isMax=!isMax;
					count++;
				}
			}
		}
	}
	

}
