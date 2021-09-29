import java.util.Observer;

public class QuackCounter implements  Quackable{
    Quackable duck;
    static int numberOfQuacks;

    public QuackCounter(Quackable duck) {
        this.duck = duck;
    }

    @Override
    public void quack() {
        duck.quack();
        numberOfQuacks++;
    }

    public static int getNumberOfQuacks() {
        return  numberOfQuacks;
    }

    @Override
    public void registerObserver(CustomObserver observer) {

    }

    @Override
    public void notifyObservers() {

    }
}
