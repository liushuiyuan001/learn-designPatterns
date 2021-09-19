package com.study;

public class HelloWorld {
    public  static void  main(String[] args) {
        System.out.println("Hello World");
        Duck mallard = new MallarDuck();
        mallard.performQuack();
        mallard.performFly();
        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyBehavior(new FlyRockPowered());
        model.performFly();
    }
}
