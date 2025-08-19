import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import javax.swing.*;

public class Pacman extends JPanel{
	private int rowCount = 21;
	private int columnCount = 19;
	private int tileSize = 32;
	private int boardWidth = tileSize * columnCount;
	private int boardHeight = rowCount * tileSize;
	
	private Image wallImg;
	private Image blueGhostImg;
	private Image orangeGhostImg;
	private Image pinkGhostImg;
	private Image redGhostImg;
	
	private Image pacmanUpImg;
	private Image pacmanDownImg;
	private Image pacmanLeftImg;
	private Image pacmanRightImg;
	
	Pacman(){
		setPreferredSize(new Dimension(boardWidth,boardHeight));
		setBackground(Color.black);
		//load images
		//the whole getclass andn get resource works well if its the same class but i want it in a seperate folder just for good practice.
		
		wallImg = new ImageIcon(("/assets/wall.png")).getImage();
		blueGhostImg= new ImageIcon(("/assets/blueGhost.png")).getImage();
		orangeGhostImg= new ImageIcon(("/assets/orangeGhost.png")).getImage();
		pinkGhostImg= new ImageIcon(("/assets/pinkGhost.png")).getImage();
		redGhostImg= new ImageIcon(("/assets/redGhost.png")).getImage();
		
		pacmanUpImg= new ImageIcon(("/assets/pacmanUp.png")).getImage();
		pacmanDownImg= new ImageIcon(("/assets/pacmanDown.png")).getImage();
		pacmanLeftImg= new ImageIcon(("/assets/pacmanLeft.png")).getImage();
		pacmanRightImg= new ImageIcon(("/assets/pacmanRight.png")).getImage();
	}
}
