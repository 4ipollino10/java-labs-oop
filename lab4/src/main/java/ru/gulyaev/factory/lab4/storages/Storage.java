package ru.gulyaev.factory.lab4.storages;

import ru.gulyaev.factory.lab4.gear.Gear;

import java.util.ArrayDeque;

public class Storage<T extends Gear> {
    private final ArrayDeque<T> _queue;

    private int _capacity;

    public Storage(int capacity){
        _capacity = capacity;
        _queue = new ArrayDeque<>();
    }





}
