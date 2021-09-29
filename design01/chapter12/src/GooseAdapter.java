import java.util.Observer;

public class GooseAdapter implements Quackable{
    Goose goose;

    public GooseAdapter(Goose goose) {
        this.goose = goose;
    }

    @Override
    public void quack() {
        goose.honk();
    }

    @Override
    public void registerObserver(CustomObserver observer) {

    }

    @Override
    public void notifyObservers() {

    }
}
