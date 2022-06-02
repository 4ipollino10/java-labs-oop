package ru.gulyaev.factory.lab4.gear;


public class Body extends Product implements Gear{
    private static final String CAR_BODY_VIN = "Body";


    private static Long _id = 0L;

    private String _vin = CAR_BODY_VIN;

    private Car _car;

    public Body(){
        _id++;
    }


    public Car getCar() {
        return _car;
    }

    @Override
    public void setCar(Car car) {
        _car = car;
    }

    @Override
    public String getFullVin() {
        return _vin + _id;
    }
}