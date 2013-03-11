package ua.luxoft.odessa.apushkar.jt2.api;

import java.awt.Color;

public interface IFigureStrategy {
	public Boolean checkUp();
	public Boolean[][] getPres();
	public int getStartX();
	public int getStartY();
	public Color getColor();
}
