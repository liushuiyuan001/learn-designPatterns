import java.util.Observer;

public interface QuackObservable {
    public void registerObserver(CustomObserver observer);
    public void notifyObservers();
}
