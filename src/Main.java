import javax.swing.JFrame;



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int rowCount = 21;
		int columnCount = 19;
		int tileSize = 32;
		int boardWidth = tileSize * columnCount;
		int boardHeight = rowCount * tileSize;
		
		Pacman pacmanGame = new Pacman();
		
		
		JFrame frame = new JFrame("Pac man");
		
		frame.setSize(boardWidth,boardHeight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(pacmanGame);
		frame.pack();
		pacmanGame.requestFocus();
		
		frame.setVisible(true);
	}

}
