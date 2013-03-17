package ua.luxoft.odessa.apushkar.jt2.api;

public interface IGameObservable {
	public void addObserver(IGameObserver o);
	public void removeObserver(IGameObserver o);
}
