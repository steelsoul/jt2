package ua.luxoft.odessa.apushkar.jt2.impl;

import java.awt.Canvas;

import ua.luxoft.odessa.apushkar.jt2.api.IGameObservable;


public abstract class BaseScreen extends Canvas implements IGameObservable {
	private static final long serialVersionUID = 1L;
	private Boolean mActive;
	
	public BaseScreen() {
		mActive = false;
	}
	
	public void setActive(Boolean active) {
		mActive = active;
	}
	
	public Boolean isActive() {
		return mActive;
	}
}
