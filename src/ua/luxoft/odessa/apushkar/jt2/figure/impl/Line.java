package ua.luxoft.odessa.apushkar.jt2.figure.impl;

import java.awt.Color;

import ua.luxoft.odessa.apushkar.jt2.api.IFigure;
import ua.luxoft.odessa.apushkar.jt2.board.impl.Board;

public class Line implements IFigure {
	
	private Position mPosition;
	
	public Line() {
		mPosition = Position.HORIZONTAL;
	}

	@Override
	public void checkUp(Board b, int x, int y) {
		switch (mPosition) {
		case HORIZONTAL:
			if (checkSpace(b, x, y))
				mPosition = mPosition.getNext();
			break;
		case VERTICAL:
			if (checkSpace(b, x, y))
				mPosition = mPosition.getNext();
			break;
		}
	}

	@Override
	public Color getColor() {
		return new Color(128, 64, 20);
	}

	@Override
	public int getStartX() {
		return Board.WIDTH/2 - 2;
	}

	@Override
	public int getStartY() {
		return -2;
	}

	@Override
	public Boolean[][] getPres() {
		Boolean[][] pres = new Boolean[4][4];
		switch (mPosition) {
		case HORIZONTAL:
			for (int i = 0; i < 4; i++)
				pres[i][2] = true;
			break;
		case VERTICAL:
			for (int i = 0; i < 4; i++)
				pres[2][i] = true;
			break;
		}
		return pres;
	}
	
	private Boolean checkSpace(Board b, int xs, int ys) {
		for (int x = 0; x < 4; x++)
			for (int y = 0; y < 4; y++)
				if (b.getMap(xs + x, ys + y))
					return false;
		return true;
	}

}
