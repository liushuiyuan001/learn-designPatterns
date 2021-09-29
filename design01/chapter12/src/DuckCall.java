import java.util.Observer;

public class DuckCall implements Quackable{
    @Override
    public void quack() {
        System.out.println("Kwak...");
    }

    @Override
    public void registerObserver(CustomObserver observer) {

    }

    @Override
    public void notifyObservers() {

    }
}
