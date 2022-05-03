package ru.gulyaev.factory.lab4.gear;


public class Car extends Product {

    private String _vin;

    private final Body _body;

    private final Engine _engine;

    private final Accessory _accessory;

    private void generateVin() {
        _vin = _body.getFullVin() + _engine.getFullVin() + _accessory.getFullVin();
    }

    public Car(Body carBody, Engine engine, Accessory accessories) {
        _body = carBody;
        _engine = engine;
        _accessory = accessories;
        generateVin();
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
