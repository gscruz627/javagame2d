package main;

import javax.swing.*;

public class Main{
	
	// MAIN METHOD STARTS EVERYTHING
	public static void main(String[] args){
		
		//WINDOW SETTINGS
        JFrame window = new JFrame("Ancapistan 2D");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(768,572);
        window.setLocation(300,300);
        window.setResizable(false);
        
        //GAME PANEL SETTINGS
        GamePanel gamePane = new GamePanel();
        window.add(gamePane);
        
        //GAME LOOPS
        gamePane.setupAdditional();
        gamePane.startGameThread();
        
        //FINAL VISIBLE CALL
        window.setVisible(true);
    }
}