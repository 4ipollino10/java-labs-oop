package ru.gulyaev.factory.lab4.gear;


public class Accessory extends Product implements Gear{
    private static final String ACCESSORIES_VIN = "Accessory";


    private static Long _id = 0L;
    private String _vin = ACCESSORIES_VIN;
    private Car _car;

    public Accessory(){
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