package org.github.survivalwars.sprites;

import java.awt.image.BufferedImage;

public class SpriteSheetLoader {
	public int[] sheetPixels;
	public int[] pixels;
	int x, y, sheetWidth;

	public SpriteSheetLoader(BufferedImage sheet) {
		BufferedImage image = new BufferedImage(sheet.getWidth(),
				sheet.getHeight(), BufferedImage.TYPE_INT_RGB);
	}

	public void grabTile(int tile, int width, int height) {
		int xTile = tile % 16;
		int yTile = tile % 16;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + (y * width)] = sheetPixels[x + (xTile * width) + y + (yTile * height) * sheetWidth];
			}
		}
	}
}