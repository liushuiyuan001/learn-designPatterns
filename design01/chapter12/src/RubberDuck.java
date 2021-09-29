import java.util.Observer;

public class RubberDuck implements Quackable{
    @Override
    public void quack() {
        System.out.println("Squeak");
    }

    @Override
    public void registerObserver(CustomObserver observer) {

    }

    @Override
    public void notifyObservers() {

    }
}
