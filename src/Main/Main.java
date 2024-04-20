package Main;

import javax.swing.JFrame;

public class Main {
	public static void main(String args[]) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Project");
		
		MainPanel gamePanel = new MainPanel();
		window.add(gamePanel);

		window.pack();

		window.setLocationRelativeTo(null);
		window.setVisible(true);

		gamePanel.startGameThread();
	}
}
