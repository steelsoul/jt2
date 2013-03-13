package ua.luxoft.odessa.apushkar.jt2.figure.impl;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import ua.luxoft.odessa.apushkar.jt2.api.IFigure;
import ua.luxoft.odessa.apushkar.jt2.api.IKeyObserver;
import ua.luxoft.odessa.apushkar.jt2.board.impl.Board;
import ua.luxoft.odessa.apushkar.jt2.board.impl.Cell;

public class TetrisFigure implements IKeyObserver {

	private IFigure mFigure;
	private int mX;
	private int mY;
	private Board mBoard;
	
	public TetrisFigure(IFigure figure, Board board) {
		mFigure = figure;
		mBoard = board;
		mX = figure.getStartX();
		mY = figure.getStartY();
	}
	
	public void stepDown() {
		mY++;
	}
	
	public Boolean checkDown() {
		Boolean[][] pres = mFigure.getPres();
		for (int x = 0; x < 4; x++)
			for (int y = 3; y  >= 0; y--)
				if (pres[x][y] != null)
				{
					if (mBoard.getMap(x + mX, y + mY + 1))						
						return false;
					else
						break;
				}
		return true;
	}
	
	public void stayOnBoard() {
		Boolean[][] pres = mFigure.getPres();
		for (int x = 0; x < 4; x++)
			for (int y = 0; y < 4; y++)
				if (pres[x][y] != null) {
					Cell temp = new Cell();
					temp.setColor(mFigure.getColor()).setChecked(true).setVisible(true);
					mBoard.setMap(mX + x, mY + y, temp);
				}
	}
	
	public void draw(Graphics g, int offsetH, int size) {
		Boolean[][] pres = mFigure.getPres();
		Cell tempCell = new Cell();
		tempCell.setColor(mFigure.getColor());
		tempCell.setChecked(true).setVisible(true);

		for (int x = 0; x < 4; x++)
			for (int y = 0; y < 4; y++)
				if (pres[x][y] != null)
				{
					tempCell.draw(g, 
							(mX + x)*size + offsetH, 
							(mY + y)*size + offsetH, size);
				}
	}
	
	@Override
	public void notify(int event) {
		switch (event)
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
	
	private Boolean checkLeft() {
		Boolean pres[][] = mFigure.getPres();
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
	
	private Boolean checkRight() {
		Boolean[][] pres = mFigure.getPres();
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
	
	private void doFall() {
		while (checkDown()) {
			mY++;
		}
	}
	
	private void checkUp() {
		mFigure.checkUp(mBoard, mX, mY);
	}
}
