package ua.luxoft.odessa.apushkar.jt2.api;

import java.awt.Color;

import ua.luxoft.odessa.apushkar.jt2.board.impl.Board;

public interface IFigure {
	public void checkUp(Board b, int x, int y);
	public Color getColor();
	public int getStartX();
	public int getStartY();
	public Boolean[][] getPres();
}
