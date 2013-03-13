package ua.luxoft.odessa.apushkar.jt2.figure.impl;

import java.awt.Color;

import ua.luxoft.odessa.apushkar.jt2.api.IFigure;
import ua.luxoft.odessa.apushkar.jt2.board.impl.Board;

public class Box implements IFigure {

	@Override
	public void checkUp(Board b, int x, int y) {
	}

	@Override
	public Color getColor() {
		return new Color(128, 64, 200);
	}

	@Override
	public int getStartX() {
		return Board.WIDTH / 2 - 1;
	}

	@Override
	public int getStartY() {
		return 0;
	}

	@Override
	public Boolean[][] getPres() {
		Boolean[][] pres = new Boolean[4][4];
		for (int x = 0; x < 2; x++)
			for (int y = 0; y < 2; y++)
				pres[x][y] = true;
		return pres;
	}

}
