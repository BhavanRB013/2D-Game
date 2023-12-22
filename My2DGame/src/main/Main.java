package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		//window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//This lets the window properly close when user clicks the close ("x") button
		window.setResizable(false);//so we cannot resize the window
		window.setTitle("2D Adventure");//To set the title of the game
		
		GamePanel gamepanel = new GamePanel();
		window.add(gamepanel);//to add the gamepanel to this window
		
		window.pack();//Causes this window to be sized to fit the preferred size and layouts of its subcomponents
		
		window.setLocationRelativeTo(null);//Not specifying the location of the window then the window will be displayed on the center of screen
		window.setVisible(true);//To see the window
		
		gamepanel.setupGame();
		gamepanel.startGameThread();
		

	}

}
