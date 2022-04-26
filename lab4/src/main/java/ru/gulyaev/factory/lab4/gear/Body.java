package ru.gulyaev.factory.lab4.gear;


public class Body extends Product implements Gear{
    private static final String CAR_BODY_VIN = "Body";


    private Long _id;

    private String _vin = CAR_BODY_VIN;

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