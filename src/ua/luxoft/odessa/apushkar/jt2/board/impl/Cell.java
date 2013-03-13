package ua.luxoft.odessa.apushkar.jt2.board.impl;

import java.awt.Color;
import java.awt.Graphics;

public class Cell {
	public static final Color FREE_CELL_COLOR = Color.GRAY;

	private Boolean mChecked;
	private Boolean mVisible;
	private Color mColor;	
	
	public Cell() {
		mChecked = false;
		mVisible = false;
		mColor = FREE_CELL_COLOR;
	}
	
	public Cell setVisible(Boolean v) {
		mVisible = v;
		return this;
	}
	
	public Cell setChecked(Boolean v) {
		mChecked = v;
		return this;
	}
	
	public Cell setColor(Color c) {
		mColor = c;
		return this;
	}
	
	public Boolean isChecked()
	{
		return mChecked;
	}
	
	public void draw(Graphics g, int x, int y, int size) {
		if (mVisible && y >= 0)
		{
			g.setColor(mColor);
			g.fillRect(x, y, size, size);
			if (mChecked)
			{
				g.setColor(mColor.darker());
				g.drawRect(x + 1, y + 1, size-2, size-2);
			}
		}
	}
}
