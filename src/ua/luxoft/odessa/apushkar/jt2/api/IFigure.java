package ua.luxoft.odessa.apushkar.jt2.api;

import java.awt.Color;

import ua.luxoft.odessa.apushkar.jt2.board.impl.Board;

public interface IFigure {
	public enum Position4 {
		LEFT {
			public Position4 getNext() {
				return Position4.UP;
			}
		},
		UP {
			public Position4 getNext() {
				return Position4.RIGHT;
			}
		},
		RIGHT {
			public Position4 getNext() {
				return Position4.DOWN;
			}
		},
		DOWN {
			public Position4 getNext() {
				return Position4.LEFT;
			}
		};
		
		public abstract Position4 getNext();
	}
	
	public enum Position {
		VERTICAL {
			public Position getNext() {
				return HORIZONTAL;
			}
		},
		
		HORIZONTAL {
			public Position getNext() {
				return VERTICAL;
			}
		};
		abstract public Position getNext();
	};
	
	public void checkUp(Board b, int x, int y);
	public Color getColor();
	public int getStartX();
	public int getStartY();
	public Boolean[][] getPres();
}
