package ru.gulyaev.factory.lab4.gear;


public class Accessory extends Product implements Gear{
    private static final String ACCESSORIES_VIN = "Accessory";


    private Long _id;
    private String _vin = ACCESSORIES_VIN;
    private Car _car;

    public Car getCar() {
        return _car;
    }

    @Override
    public void setCar(Car car) {
        _car = car;
    }

    public Long getId() {
        return _id;
    }

    public String getVin() {
        return _vin;
    }

    public void setVin(String vin) {
        _vin = vin;
    }

    @Override
    public String getFullVin() {
        return _vin + getProductID();
    }
}