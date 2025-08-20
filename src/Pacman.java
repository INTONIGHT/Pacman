import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import javax.swing.*;

public class Pacman extends JPanel implements ActionListener, KeyListener{
	class Block{
		int x,y,width,height;
		Image image;
		
		int startX, startY;
		char direction = 'U';//U,D,R,L
		int velocityX = 0;
		int velocityY = 0;
		
		Block(Image image, int x, int y, int width, int height){
			this.image = image;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.startX = x;
			this.startY = y;
			
		}
		void updateDirection(char direction) {
			char prevDirection = this.direction;
			this.direction = direction;
			updateVelocity();
			this.x += velocityX;
			this.y += velocityY;
			for(Block wall: walls) {
				//this being pacman
				if(collision (this, wall)) {
					this.x -= this.velocityX;
					this.y -= this.velocityY;
					this.direction = prevDirection;
					updateVelocity();
				}
			}
		}
		void updateVelocity() {
			switch(this.direction) {
			case 'U' :
				this.velocityX = 0;
				this.velocityY = -tileSize / 4;
				break;
			case 'D' :
				this.velocityX = 0;
				this.velocityY = tileSize / 4;
				break;
			case 'L' :
				this.velocityX = -tileSize / 4;
				this.velocityY = 0;
				break;
			case 'R':
				this.velocityX = tileSize / 4;
				this.velocityY = 0;
				break;
			}
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
	Timer gameLoop;
	
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
		addKeyListener(this);
		setFocusable(true);
		
		//load images
		//the whole getclass and get resource works well if its the same class but i want it in a seperate folder just for good practice.
		
		wallImg = new ImageIcon(getClass().getResource("./wall.png")).getImage();
		blueGhostImg= new ImageIcon(getClass().getResource("./blueGhost.png")).getImage();
		orangeGhostImg= new ImageIcon(getClass().getResource("./orangeGhost.png")).getImage();
		pinkGhostImg= new ImageIcon(getClass().getResource("./pinkGhost.png")).getImage();
		redGhostImg= new ImageIcon(getClass().getResource("./redGhost.png")).getImage();
		
		pacmanUpImg= new ImageIcon(getClass().getResource("./pacmanUp.png")).getImage();
		pacmanDownImg= new ImageIcon(getClass().getResource("./pacmanDown.png")).getImage();
		pacmanLeftImg= new ImageIcon(getClass().getResource("./pacmanLeft.png")).getImage();
		pacmanRightImg= new ImageIcon(getClass().getResource("./pacmanRight.png")).getImage();
		
		loadMap();
		gameLoop = new Timer(50,this);//20fps (1000/50)
		gameLoop.start();
		
		
	}
	public void loadMap() {
		walls = new HashSet<Block>();
		foods = new HashSet<Block>();
		ghosts = new HashSet<Block>();
		
		for(int r =0; r<rowCount;r++) {
			for(int c=0;c<columnCount;c++) {
				String row = tileMap[r];
				char tileMapChar = row.charAt(c);
				
				int x = c * tileSize;
				int y = r * tileSize;
				
				switch(tileMapChar) {
				case 'X'://walls
					Block wall = new Block(wallImg,x,y,tileSize,tileSize);
					walls.add(wall);
					break;
				case 'b'://blue ghost
					Block blueGhost = new Block(blueGhostImg,x,y,tileSize,tileSize);
					ghosts.add(blueGhost);
					break;
				case 'o'://orange ghost
					Block orangeghost = new Block(orangeGhostImg,x,y,tileSize,tileSize);
					ghosts.add(orangeghost);
					break;
				case 'p'://pink ghost
					Block pinkGhost = new Block(pinkGhostImg,x,y,tileSize,tileSize);
					ghosts.add(pinkGhost);
					break;
				case 'r'://red ghost
					Block redGhost = new Block(redGhostImg,x,y,tileSize,tileSize);
					ghosts.add(redGhost);
					break;
				case 'P'://pacman
					pacman = new Block(pacmanRightImg,x,y,tileSize,tileSize);
					break;
				case ' '://food
					Block food = new Block(null,x + 14,y + 14, 4, 4);
					foods.add(food);
					break;
				}
				

			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		g.drawImage(pacman.image,pacman.x,pacman.y,pacman.width,pacman.height,null);
		for(Block ghost: ghosts) {
			g.drawImage(ghost.image,ghost.x,ghost.y,ghost.width,ghost.height,null);
		}
		for(Block wall: walls) {
			g.drawImage(wall.image,wall.x,wall.y,wall.width,wall.height,null);
		}
		g.setColor(Color.white);
		for(Block food: foods) {
			g.fillRect(food.x, food.y, food.width, food.height);
		}
		
		
	}
	
	public void move() {
		pacman.x += pacman.velocityX;
		pacman.y += pacman.velocityY;
		for(Block wall: walls) {
			if(collision(pacman,wall)) {
				pacman.x -= pacman.velocityX;
				pacman.y -= pacman.velocityY;
				break;
			}
		}
	}
	
	public boolean collision(Block a, Block b) {
		//should recoghnize this formula from the FlappyBird game
		return a.x < b.x + b.width && //as top left corner doesnt reach bs top left corner
				a.x + a.width > b.x &&//as top right corner passes bs top left corner
				a.y < b.y + b.height &&//as top left corner doesnt reach bs bottom left corner
				a.y + a.height > b.y;//as bottom left corner basses bs top left corner
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		move();
		repaint();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			pacman.updateDirection('U');
			break;
		case KeyEvent.VK_DOWN:
			pacman.updateDirection('D');
			break;
		case KeyEvent.VK_LEFT:
			pacman.updateDirection('L');
			break;
		case KeyEvent.VK_RIGHT:
			pacman.updateDirection('R');
			break;
		}
		
	}
	
}
