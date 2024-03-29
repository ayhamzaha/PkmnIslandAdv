package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.sound.sampled.FloatControl;
import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;
public class GamePanel extends JPanel implements Runnable{
	
	final int originalTileSize = 16;
	final int scale = 3;
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenwidth = tileSize * maxScreenCol;
	public final int screenheight = tileSize * maxScreenRow;
	KeyHandler keyH = new KeyHandler();
	//WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	float  volume1 = -55.0f;
	float volume2 = -22.0f;
	
	
	
	
	
	
	
	
	
	
	
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	
	public Sound music = new Sound();
	
	public Sound SE = new Sound();
	
	public CollisionChecker cChecker = new CollisionChecker(this);
	
	public AssetSetter aSetter = new AssetSetter(this);
	
	public UI ui = new UI(this);
	
	Thread gameThread;
	
	public Player player = new Player(this,keyH);
	
	public SuperObject obj[] = new SuperObject[10];
	

	
	
	
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenwidth, screenheight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		
		aSetter.setObject();
		
		playMusic(0);
			
	}
	
	public void startGameThread() {
			gameThread = new Thread(this);
			gameThread.start();
			
		}

	@Override
	public void run() {
		while(gameThread != null) {
			
			double drawInterval = 1000000000/FPS;
			double nextDrawTime = System.nanoTime() + drawInterval;
			
			//System.out.println("RUNNING");
			//System.out.println("Current Time:"+ currentTime);

			update();
			
			repaint();
			

			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime<0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				e.printStackTrace();  
			}
		}
		
	}
	public void update() {
		player.update();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		//DEBUG
		long drawStart = 0;
		if(keyH.checkdrawTime == true) {
			drawStart = System.nanoTime();
		}
		
		tileM.draw(g2);
		
		for(int i = 0; i<obj.length;i++) {
			if(obj[i]!=null) {
				obj[i].draw(g2, this);
			}
		}
		
		player.draw(g2);
		
		ui.draw(g2);
		if(keyH.checkdrawTime == true) {
			
		long drawEnd = System.nanoTime();
		long passed = drawEnd - drawStart;
		g2.setColor(Color.WHITE);
		g2.drawString("Draw Time: "+ passed, 10, 400);
		System.out.println("Draw Time: "+passed);
		}
		g2.dispose();
		
	}
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
		music.volumeSetter(i);
		
	}
	public void stopMusic() {
		music.stop();
	}
	public void playSE(int i) {
		SE.setFile(i);
		SE.play();
		SE.volumeSetter(i);
	}
		
		
		
}	
