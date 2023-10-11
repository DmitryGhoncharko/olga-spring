package org.example.proxy;

public class SecondCar implements Car{
    private FirstCar firstCar;
    @Override
    public void drive() {
        //
        firstCar.drive();
        //
        System.out.println("some logic");
    }
}
