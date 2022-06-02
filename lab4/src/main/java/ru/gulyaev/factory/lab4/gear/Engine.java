package ru.gulyaev.factory.lab4.gear;

public class Engine extends Product implements Gear{
    private static final String ENGINE_VIN = "Engine";
    private static Long _id = 0L;
    private String _vin = ENGINE_VIN;
    private Car _car;

    public Engine(){
        _id++;
    }

    public Car getCar() {
        return _car;
    }

    @Override
    public void setCar(Car car) {
        _car = car;
    }

    public String getFullVin() {
        return _vin + _id;
    }
}