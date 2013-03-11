package ua.luxoft.odessa.apushkar.jt2.figure.impl;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import ua.luxoft.odessa.apushkar.jt2.api.IFigureStrategy;
import ua.luxoft.odessa.apushkar.jt2.api.IKeyObserver;
import ua.luxoft.odessa.apushkar.jt2.board.impl.Board;
import ua.luxoft.odessa.apushkar.jt2.board.impl.Cell;

public class TetrisFigure implements IKeyObserver {

	private IFigureStrategy mStrategy;
	private int mX;
	private int mY;
	private Board mBoard;
	
	public TetrisFigure(IFigureStrategy strategy, Board board)
	{
		mStrategy = strategy;
		mBoard = board;
		mX = mStrategy.getStartX();
		mY = mStrategy.getStartY();
	}
	
	private Boolean checkLeft() {
		Boolean pres[][] = mStrategy.getPres();
		for (int y = 0; y < 4; y++)
			for (int x = 0; x < 4; x++)
				if (pres[x][y] != null) {
					if (mBoard.getMap(x + mX - 1, y + mY))
						return false;
					else
						break;
				}
		
		return true;
	}
	
	private Boolean checkRight()
	{
		Boolean[][] pres = mStrategy.getPres();
		for (int y = 0; y < 4; y++)
			for (int x = 4 - 1; x >= 0; x--)
				if (pres[x][y] != null)
				{
					if (mBoard.getMap(x + mX + 1, y + mY))
						return false;
					else
						break;
				}
		return true;
	}
	
	public Boolean checkDown()
	{
		Boolean[][] pres = mStrategy.getPres();
		for (int x = 0; x < 4; x++)
			for (int y = 4 - 1; y  >= 0; y--)
				if (pres[x][y] != null)
				{
					if (mBoard.getMap(x + mX, y + mY + 1))						
						return false;
					else
						break;
				}
		return true;
	}
	
	public void draw(Graphics g, int offsetH, int size)
	{
		Boolean[][] pres = mStrategy.getPres();
		for (int x = 0; x < 4; x++)
			for (int y = 0; y < 4; y++)
				if (pres[x][y] != null)
				{
					Cell tempCell = new Cell();
					tempCell.setChecked(true).setVisible(true).setColor(mStrategy.getColor());
					tempCell.draw(g, mX + x, mY + y + offsetH, size);
				}
	}
	
	private void doFall() {
		while (checkDown()) {
			mY++;
		}
	}
	
	private Boolean checkUp() {
		return mStrategy.checkUp();
	}
	
	
	@Override
	public void notify(KeyEvent e) {
		switch (e.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
			if (checkLeft()) mX--;
			break;
		case KeyEvent.VK_RIGHT:
			if (checkRight()) mX++;
			break;
		case KeyEvent.VK_DOWN:
			doFall();
			break;
		case KeyEvent.VK_UP:
			checkUp();
			break;
		default:
			break;
		}
	}

}
