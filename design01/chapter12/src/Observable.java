import java.util.ArrayList;
import java.util.Observer;

public class Observable implements QuackObservable{
    ArrayList<CustomObserver> observables = new ArrayList<>();
    QuackObservable duck;

    public Observable(QuackObservable dick) {
        this.duck = dick;
    }

    @Override
    public void registerObserver(CustomObserver observer) {
        System.out.println("333333333");
        observables.add(observer);
    }

    @Override
    public void notifyObservers() {
        System.out.println("22222222");
        System.out.println(observables.size() + " 222?????22");
        for (CustomObserver observer: observables) {
            System.out.println("update????");
            observer.update(duck);
        }
    }
}
