package main;
import javax.swing.*;
public class Main{
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        GamePanel gamePane = new GamePanel();
        window.add(gamePane);
        //Last make window visible;
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setSize(gamePane.getWidth() + 14, gamePane.getHeight() + 37);
        window.setVisible(true);
        System.out.println(gamePane.getSize());
        gamePane.startGameThread();
    }
}