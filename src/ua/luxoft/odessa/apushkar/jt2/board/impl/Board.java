package ua.luxoft.odessa.apushkar.jt2.board.impl;

import java.awt.Graphics;

public class Board {
	public static final int WIDTH = 10;
	public static final int HEIGHT = 20;
	
	private Cell[][] mBoard = new Cell[WIDTH + 2][HEIGHT + 1];
	
	public Board() {
		for (int i = 0; i < HEIGHT + 1; i++)
		{
			mBoard[0][i] = new Cell();
			mBoard[0][i].setChecked(true);
			mBoard[WIDTH+1][i] = new Cell();
			mBoard[WIDTH+1][i].setChecked(true);
		}
		for (int i = 0; i < WIDTH + 2; i++)
		{
			mBoard[i][HEIGHT] = new Cell();
			mBoard[i][HEIGHT].setChecked(true);
		}
		for (int i = 1; i < WIDTH + 1; i++)
			for (int j = 0; j < HEIGHT; j++)
			{
				mBoard[i][j] = new Cell();
				mBoard[i][j].setVisible(true);
			}
	}
	
	public void clear() {
		for (int i = 0; i < WIDTH + 1; i++)
			for (int j = 0; j < HEIGHT; j++)
				mBoard[i][j].setChecked(false);
	}
	
	public Boolean getMap(int x, int y)	{
		if (y < 0)
			return false;
		else if (x >= 0 && x < WIDTH + 1 && y >= 0 && y < HEIGHT)
			return mBoard[x + 1][y].isChecked();
		else
			return true;
	}
	
	public void setMap(int x, int y) {
		if (x >= 0 && x < WIDTH + 1 && y >= 0 && y < HEIGHT)
			mBoard[x + 1][y].setChecked(true);
	}
	
	public void setMap(int x, int y, Cell cell)
	{
		if (x >= 0 && x < WIDTH + 1 && y >= 0 && y < HEIGHT)
			mBoard[x + 1][y] = cell;
	}
	
	public void draw(Graphics g, int offsetH, int size) {
		for (int i = 1; i < WIDTH + 1; i++)
			for (int j = 0; j < HEIGHT; j++)
				mBoard[i][j].draw(g, 
						offsetH + (i-1)*size, 
						offsetH + j*size, 
						size);
	}
	
	public void checkBoard() {
		for (int i = 0; i < HEIGHT; i++) 
		{
			if (checkLine(i)) {
				// remove this line 
				// and move down all above it
				for (int y = i; y > 0; y--)
					for (int x = 1; x < WIDTH + 1; x++)
						mBoard[x][y] = mBoard[x][y-1];
				// regenerate first line from the top
				for (int x = 1; x < WIDTH + 1; x++){
					Cell tmp = new Cell();
					tmp.setVisible(true);
					mBoard[x][0] = tmp;
				}
			}
		}
	}
	
	private Boolean checkLine(int h) {
		for (int x = 1; x < WIDTH + 1; x++)
			if (!mBoard[x][h].isChecked()) 
				return false; 
		return true;
	}
	
}
