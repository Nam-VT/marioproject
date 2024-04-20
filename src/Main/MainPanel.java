
package Main;

import Entity.Player;
import tile.TileManager;
import Entity.KeyHandler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class MainPanel extends JPanel implements Runnable{
	//SCEEN SETTINGS
	final int orginalTitleSize = 16; // 16x16 tile
	final int Scale = 3;
	
	public final int titleSize = orginalTitleSize * Scale; //48x48 tile
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 12;
	public final int screenWidth = maxScreenCol * titleSize;//768 pixels
	public final int screenHeight = maxScreenRow * titleSize;//576 pixels
	//WORLD SETTING
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = titleSize * maxWorldCol;
	public final int worldHeight = titleSize * maxWorldRow;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	public Player player = new Player(this, keyH);
	//FPS
	int FPS = 60;

	public MainPanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void run() {

		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while(gameThread != null) {

			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;

			lastTime = currentTime;

			if(delta >= 1) {
				//1. UPDATE: update information
				update();
				//2. DRAW:
				repaint();

				delta--;
			}
		}
	}

	public void update() {
		
		player.update(); 
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;

		tileM.draw(g2);
		
		player.draw(g2);
		
		g2.dispose();
	}
}
