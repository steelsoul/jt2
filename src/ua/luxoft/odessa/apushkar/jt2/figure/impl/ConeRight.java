package ua.luxoft.odessa.apushkar.jt2.figure.impl;

import java.awt.Color;

import ua.luxoft.odessa.apushkar.jt2.api.IFigure;
import ua.luxoft.odessa.apushkar.jt2.board.impl.Board;

public class ConeRight implements IFigure {
	
	private Position4 mPosition;

	public ConeRight() {
		mPosition = Position4.DOWN;
	}
	
	private Boolean checkSpace(Board b, int x, int y) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (b.getMap(x + i, y + j))
					return false;
		return true;
	}
	
	@Override
	public void checkUp(Board b, int x, int y) {
		if (checkSpace(b, x, y)) 
			mPosition = mPosition.getNext();
	}

	@Override
	public Color getColor() {
		return new Color(128, 32, 70);
	}

	@Override
	public int getStartX() {
		return Board.WIDTH/2 - 2;
	}

	@Override
	public int getStartY() {
		return -1;
	}

	@Override
	public Boolean[][] getPres() {
		Boolean[][] pres = new Boolean[4][4];
		switch (mPosition) {
		case LEFT:
			for (int i = 0; i < 3; i++)
				pres[0][i] = true;
			pres[1][2] = true;
			break;
		case UP:
			for (int i = 0; i < 3; i++)
				pres[i][0] = true;
			pres[0][1] = true;
			break;
		case RIGHT:
			for (int i = 0; i < 3; i++)
				pres[2][i] = true;
			pres[1][0] = true;
			break;
		case DOWN:
			for (int i = 0; i < 3; i++)
				pres[i][2] = true;
			pres[2][1] = true;
			break;
		}
		return pres;
	}

}
