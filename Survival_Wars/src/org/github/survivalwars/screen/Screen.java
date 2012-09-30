package org.github.survivalwars.screen;

import org.github.survivalwars.sprites.SpriteSheetLoader;

public class Screen {
	private SpriteSheetLoader loader;
	private int w, h;
	public int[] pixels = new int[w*h];
	int xOffset = 0, yOffset = 0;
	public Screen(int w, int h, SpriteSheetLoader newloader) {
		loader = newloader;
		this.w = w;
		this.h = h;
	}

	public void render(int tile, int xPos, int yPos, int width, int height) {
		loader.grabTile(tile, width, height);
		xPos = xOffset;
		yPos = yOffset;
		for(int y = 0; y < height; y++){
			if(yPos + y < 0 || yPos + y >= height)continue;
			for(int x = 0; x < width; x++){
				if(xPos + x < 0 || xPos + x >= width)continue;
				int col = loader.pixels[x + (y * height)];
			}
		}
	}
}