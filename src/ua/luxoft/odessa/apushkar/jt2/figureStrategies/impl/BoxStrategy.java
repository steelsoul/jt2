package ua.luxoft.odessa.apushkar.jt2.figureStrategies.impl;

import java.awt.Color;

import ua.luxoft.odessa.apushkar.jt2.api.IFigureStrategy;
import ua.luxoft.odessa.apushkar.jt2.board.impl.Board;

public class BoxStrategy implements IFigureStrategy {

	@Override
	public Boolean checkUp() {
		return true;
	}

	@Override
	public Boolean[][] getPres() {
		Boolean pres[][] = new Boolean[4][4];
		for (int x = 0; x < 2; x++)
			for (int y = 0; y < 2; y++)
				pres[x][y] = true;
		return null;
	}

	@Override
	public int getStartX() {
		// TODO Auto-generated method stub
		return Board.WIDTH / 2 + 1;
	}

	@Override
	public int getStartY() {
		// TODO Auto-generated method stub
		return -3;
	}

	@Override
	public Color getColor() {
		return new Color(128, 64, 200);
	}
	
	

}
