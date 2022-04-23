package main;
import java.awt.*;
import java.awt.Color;
import javax.swing.JPanel;
import entity.*;
import tile.*;
import object.*;

public class GamePanel extends JPanel implements Runnable{

	private static final long serialVersionUID = 1L;
	// Game screen settings, sizing
    final int originalTitleSize = 16; //16x16 tile scaled by 3.0;
    final int tileScale = 3;
    public int tileSize = originalTitleSize * tileScale;
    final int FPS = 60;

    // Maximum size defined
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;

    public final int screenWidth = tileSize * maxScreenCol; // 768px wide
    public final int screenHeight = tileSize * maxScreenRow; //576px tall
    
    
    // World settings
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;
    public int worldWidth = tileSize * maxWorldCol;
    public int worldHeight = tileSize * maxWorldRow;
    
    // Modified KeyHandler is created;
    KeyHandler keyHandler = new KeyHandler(this);
    TileManager tileManager = new TileManager(this);
    public CollisionManager collisionManager = new CollisionManager(this);
    public ObjectManager objManager = new ObjectManager(this);
    // Game thread is created;
    public Thread gameThread;
    public Player player = new Player(this, this.keyHandler);
    public ParentObject[] obj = new ParentObject[10];
    
    //Helpers
    private int keyZCounter = 0;
    // Starts the main game thread;
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    // What does happens while the game thread is on
    @Override
    public void run(){
    	
    	double drawInterval = 1000000000 / FPS;
    	double nextDrawTime = System.nanoTime() + drawInterval;
    	while(gameThread != null) {
    		update();
    		repaint(); // Calls paintComponent;
    		
    		try {
    			double remainingTime = nextDrawTime - System.nanoTime();
    			remainingTime /= 1000000;
    			nextDrawTime += drawInterval;
    			
    			if (remainingTime < 0) {
    				remainingTime = 0;
    			}
    			Thread.sleep((long)remainingTime);
    			nextDrawTime += drawInterval;
    		} catch(Exception e) {
    			System.out.println(e.getMessage());
    		}
    	}
    }
    	
    
    // Method to update information on the screen
    public void update() {
    	player.update();
    }
    
    // Method to paint component on the screen each time
    public void paintComponent(Graphics graph) {
    	
    	super.paintComponent(graph);
    	
    	Graphics2D graph2D = (Graphics2D)graph;
    	
    	// Drawn in layers 
    	
    	tileManager.draw(graph2D);
    	
    	
    	for (int i = 0; i < obj.length; i++) {
    		if (obj[i] != null) {
    			obj[i].draw(graph2D, this);
    		}
    	}
    	player.draw(graph2D);
    	graph2D.dispose();
    } 
    
    public void zoomIn() {
    	if (keyZCounter == 0) {
    		keyZCounter = 1;
    	int oldWorldWidth = tileSize * maxWorldCol;
    	tileSize -= 16;
    	int newWorldWidth = tileSize * maxWorldCol;
    	double multiplier = (double)newWorldWidth/oldWorldWidth;
    	player.playerX *= multiplier;
    	player.playerY *= multiplier;
    	player.hitbox.x *= multiplier;
    	player.hitbox.y *= multiplier;
    	player.hitbox.width *= multiplier;
    	player.hitbox.height *= multiplier;
    	player.speed = newWorldWidth / (worldWidth / 4);
    	}
    	}    
    public void zoomOut(){
    	keyZCounter = 0;
    	int oldWorldWidth = tileSize * maxWorldCol;
		tileSize += 16;
		int newWorldWidth = tileSize * maxWorldCol;
		double multiplier = (double)newWorldWidth/oldWorldWidth;
		player.playerX *= multiplier;
		player.playerY *= multiplier;
    	player.hitbox.x *= multiplier;
    	player.hitbox.y *= multiplier;
    	player.hitbox.width *= multiplier;
    	player.hitbox.height *= multiplier;
		player.speed = newWorldWidth / (worldWidth / 4);
    }
    public void setUpGame() {
    	objManager.setObject();
    }
    
    // Panel constructor, what makes this panel different from JPanel;
    public GamePanel(){
        this.setSize(screenWidth, screenHeight);
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }
}
