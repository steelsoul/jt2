package ua.luxoft.odessa.apushkar.jt2.figure.impl;

import java.util.Random;

import ua.luxoft.odessa.apushkar.jt2.api.IFigure;

public class FigureGenerator {
	public static IFigure generate() {
		Random r = new Random();
		int choice = r.nextInt(4);
		switch (choice) {
		case 0:
			return new Box();
		case 1:
			return new Line();
		case 2:
			return new Triangle();
		case 3:
			return new ConeLeft();
		default:
			return new Line();
		}
	}
}
