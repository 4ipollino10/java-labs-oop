package ru.gulyaev.factory.lab4.gear;

public abstract class Product {
    private long _id;

    public void setProductID(long id) {
        _id = id;
    }

    public long getProductID() {
        return _id;
    }

    abstract public String getFullVin();

}
