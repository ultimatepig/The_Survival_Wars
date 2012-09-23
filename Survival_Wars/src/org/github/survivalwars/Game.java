package org.github.survivalwars;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import org.github.survivalwars.screen.Screen;
import org.github.survivalwars.sprites.SpriteSheetLoader;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	public static String NAME = "The Survival Wars";
	public static int height = 120;
	public static int width = 160;
	public static int scale = 3;
	Random r = new Random();
	private boolean running = false;
	private Screen screen;
	public static Dimension gamedim = new Dimension(width * scale, height
			* scale);
	private BufferedImage image = new BufferedImage(width * scale, height
			* scale, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer())
			.getData();
	public SpriteSheetLoader loader;

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			requestFocus();
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		bs.show();
	}

	public void tick() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = r.nextInt();
		}
	}

	public void init() {
		BufferedImage sheet = null;
		try {
			loader = new SpriteSheetLoader(ImageIO.read(Game.class
					.getResourceAsStream("/tiles.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		loader = new SpriteSheetLoader(sheet);
	}

	public void start() {
		running = true;
		new Thread(this).start();
	}

	public void run() {
		while (running) {
			tick();
			render();
		}
	}

	public static void main(String args[]) {
		Game game = new Game();
		game.setPreferredSize(gamedim);
		game.setMaximumSize(gamedim);
		game.setMinimumSize(gamedim);
		JFrame frame = new JFrame(NAME);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.start();
	}
}