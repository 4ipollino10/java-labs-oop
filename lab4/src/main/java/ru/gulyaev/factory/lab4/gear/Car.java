package ru.gulyaev.factory.lab4.gear;


public class Car extends Product {
    private Long _id;

    private String _vin;

    private Body _body;

    private Engine _engine;

    private Accessory _accessory;


    public Car() {

    }

    private void generateVin() {
        _vin = _body.getFullVin() + _engine.getFullVin() + _accessory.getFullVin();
    }

    public Car(Body carBody, Engine engine, Accessory accessories) {
        _body = carBody;
        _engine = engine;
        _accessory = accessories;
        generateVin();
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

    public Body getCarBody() {
        return _body;
    }

    public void setCarBody(Body carBody) {
        _body = carBody;
    }

    public Engine getEngine() {
        return _engine;
    }

    public void setEngine(Engine engine) {
        engine = engine;
    }

    public Accessory getAccessory() {
        return _accessory;
    }

    public void setAccessories(Accessory accessory) {
        _accessory = accessory;
    }

    @Override
    public String toString() {
        return _vin;
    }

    @Override
    public String getFullVin() {
        return _vin;
    }
}
