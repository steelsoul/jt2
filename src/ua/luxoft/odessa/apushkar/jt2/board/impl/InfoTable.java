package ua.luxoft.odessa.apushkar.jt2.board.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import ua.luxoft.odessa.apushkar.jt2.api.IFigure;

public class InfoTable {
	private IFigure mFigure;
	private int mScores;
	private int mLevel;
	
	public InfoTable(IFigure figure) {
		mFigure = figure;
		mScores = 0;
		mLevel = 0;
	}
	
	public void changeFigure(IFigure figure) {
		mFigure = figure;
	}
	
	public void addScores(int a) {
		mScores += a;
	}
	
	public void changeLevel() {
		mLevel++;
	}
	
	public void draw(Graphics g, int offsetW, int offsetH, int size) {
		g.setColor(Color.BLACK);
		Font f = new Font("Fixedsys", Font.BOLD, 14);
		g.setFont(f);
		int textHeight = g.getFontMetrics().getHeight();

		String tmp = "Scores: " + mScores;
		int textWidth = (int)g.getFontMetrics().getStringBounds(tmp, g).getWidth();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(offsetW + 10, offsetH , textWidth, textHeight);
		g.setColor(Color.BLACK);
		g.drawString(tmp, offsetW + 10, offsetH + textHeight);

		tmp = "Level: " + mLevel;
		textWidth = (int) g.getFontMetrics().getStringBounds(tmp,  g).getWidth();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(offsetW + 10, 2*textHeight + offsetH , textWidth, textHeight);
		g.setColor(Color.BLACK);
		g.drawString(tmp, offsetW + 10,	offsetH + 3*textHeight);

		g.setColor(Cell.FREE_CELL_COLOR);
		g.fillRect(offsetW + 10, offsetH + 5*textHeight, size*4, size*4);
		Boolean[][] pres = mFigure.getPres();
		Cell temp = new Cell();
		temp
		.setVisible(true)
		.setChecked(true)
		.setColor(Color.GRAY);
		for (int x = 0; x < 4; x++)
			for (int y = 0; y < 4; y++) 
				if (pres[x][y] != null)
					temp.draw(g, offsetW + 10 + x*size, 
							offsetH + 5*textHeight + 
							y*size, size);
		g.setColor(Color.BLACK);
		g.drawRect( 
				offsetW + 10 - 1, 
				offsetH + 5*textHeight - 1, 
				size*4 + 1, 
				size*4 + 1);				
	}

}
