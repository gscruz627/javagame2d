package main;

//IMPORT
import java.awt.*;
import java.awt.Color;
import javax.swing.JPanel;

//IMPORT LOCAL PACKAGES
import entity.*;
import tile.*;
import object.*;

public class GamePanel extends JPanel implements Runnable{
	
	// BACKGROUND TILES SETTINGS
    final int originalTitleSize = 16; //TILES ARE 16 X 16
    final int tileScale = 3; //SCALED BY 3
    public int tileSize = originalTitleSize * tileScale; //CURRENT TILE SIZE, DEFAULT = 48
    
    //SCREEN SETTINGS
    final int FPS = 60;
    
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;

    public final int screenWidth = tileSize * maxScreenCol; //768px wide
    public final int screenHeight = tileSize * maxScreenRow; //576px tall
    
    
    // WORLD SETTINGS
    public final int maxWorldCol = 100; // 100 TILES WIDE
    public final int maxWorldRow = 100; // 100 TILES TALL
    
    //MANAGERS;
    KeyHandler keyHandler = new KeyHandler(this); //MANAGES KEYBOARD RESPONSES
    TileManager tileManager = new TileManager(this); //MANAGES TILES
    public CollisionManager collisionManager = new CollisionManager(this); //MANAGES COLLISION (ENTITY-OBJECT)
    public ObjectManager objManager = new ObjectManager(this); //MANAGES OBJECTS
    SoundManager sound = new SoundManager();
    
    //CREATORS
    public Thread gameThread; //CREATES GAME THREAD
    public Player player = new Player(this, this.keyHandler); //CREATES A PLAYER
    public ParentObject[] obj = new ParentObject[10]; //CREATES A (10) LIST OF OBJECTS ON THE SCREEN
    
    //CLASS HELPERS
    private int keyZCounter = 0;

    // SETS UP ADDITIONAL DETAILS
    public void setupAdditional() {
    	
    	objManager.setObject(); //THE OBJECT MANAGER BEGINS CHECKING FOR COLLISION
    	playMusic(0); // BACKGROUND MUSIC WILL BE PLAYED
    }
    
    //STARTS THE GAME
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    // METHOD THAT RUNS FPS TIMES AND CALLS FOR DRAWING AND CHECKING
    public void run(){
    	
    	double drawInterval = 1000000000 / FPS; //NANO SECONDS IN FPS
    	double nextDrawTime = System.nanoTime() + drawInterval; //DEFINES NEXT DRAWING TIME
    	
    	while(gameThread != null) {
    		// IF THE GAMETHREAD IS ACTIVE IT WILL UPDATE AND REPAINT THE PANEL 60 TIMES PER SECOND
    		update(); //
    		repaint(); // METHOD TO CALL FOR paintComponent()
    		
    		try {
    			double remainingTime = nextDrawTime - System.nanoTime(); //WILL GIVE HOW MANY ns IS LEFT TO NEXT DRAW TIME
    			
    			//RESETS THE REMAINING TIME IF THE THREAD SLEPT ENOUGH :)
    			if (remainingTime < 0) {
    				remainingTime = 0; 
    			}
    			
    			Thread.sleep((long)remainingTime/1000000); //THREAD WILL SLEEP DURING remainingTime IN MILISECONDS
    			nextDrawTime += (drawInterval*2); //CALCULATE THE NEXT DRAWING TIME
    		} catch(Exception e) {
    			System.out.println(e.getMessage());
    		}
    		
    	}
    	
    }
    	
    public void playMusic(int i) {
    	sound.setFile(i);
    	sound.play();
    	sound.loop();
    }
    
    public void stopMusic() {
    	sound.stop();
    }
    
    public void playSoundEffect(int i) {
    	sound.setFile(i);
    	sound.play();
    }
    // UPDATES INFORMATION ON THE SCREEN
    public void update() {
    	player.update();
    }
    
    // OVERRIDES PANEL METHOD TO DRAW OBJECTS ON SCREEN
    public void paintComponent(Graphics graph) {
    	
    	super.paintComponent(graph); //RECURSION TO ITS PARENT
    	
    	Graphics2D graph2D = (Graphics2D)graph;
    	
    	//LAYER DRAWING
    	tileManager.draw(graph2D); //BACKGROUND TILES FIRST
    	
    	for (int i = 0; i < obj.length; i++) { //OBJECTS SECOND
    		if (obj[i] != null) {
    			obj[i].draw(graph2D, this);
    		}
    	}
    	
    	player.draw(graph2D); //PLAYER THIRD
    	graph2D.dispose();
    } 
    
    /* FUTURE IMPLEMENTATION
    private void zoom(int n) {
    	int oldWorldWidth = tileSize * maxWorldCol;
		tileSize += n;
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
    public void zoomIn() {   
    	if (keyZCounter == 0) {
    		keyZCounter = 1;
    		zoom(-16);
    	}
    }
    public void zoomOut(){
    	keyZCounter = 0;
    	int oldWorldWidth = tileSize * maxWorldCol;
    	zoom(16);
    }
    */
    
    // GAME PANEL CONSTRUCTOR
    public GamePanel(){
    	
        this.setSize(screenWidth, screenHeight);
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        
    }
}
