import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import javax.swing.*;

public class Pacman extends JPanel{
	class Block{
		int x,y,width,height;
		Image image;
		
		int startX, startY;
		Block(Image image, int x, int y, int width, int height){
			this.image = image;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.startX = x;
			this.startY = y;
			
		}
	}
	
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
	
	HashSet<Block> walls;
	HashSet<Block> foods;
	HashSet<Block> ghosts;
	Block pacman;
	
	 //X = wall, O = skip, P = pac man, ' ' = food
    //Ghosts: b = blue, o = orange, p = pink, r = red
    private String[] tileMap = {
        "XXXXXXXXXXXXXXXXXXX",
        "X        X        X",
        "X XX XXX X XXX XX X",
        "X                 X",
        "X XX X XXXXX X XX X",
        "X    X       X    X",
        "XXXX XXXX XXXX XXXX",
        "OOOX X       X XOOO",
        "XXXX X XXrXX X XXXX",
        "O       bpo       O",
        "XXXX X XXXXX X XXXX",
        "OOOX X       X XOOO",
        "XXXX X XXXXX X XXXX",
        "X        X        X",
        "X XX XXX X XXX XX X",
        "X  X     P     X  X",
        "XX X X XXXXX X X XX",
        "X    X   X   X    X",
        "X XXXXXX X XXXXXX X",
        "X                 X",
        "XXXXXXXXXXXXXXXXXXX" 
    };
	
	Pacman(){
		setPreferredSize(new Dimension(boardWidth,boardHeight));
		setBackground(Color.black);
		//load images
		//the whole getclass and get resource works well if its the same class but i want it in a seperate folder just for good practice.
		
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
