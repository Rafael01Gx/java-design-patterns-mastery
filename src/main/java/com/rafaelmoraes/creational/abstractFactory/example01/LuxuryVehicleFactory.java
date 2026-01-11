package com.rafaelmoraes.creational.abstractFactory.example01;

import com.rafaelmoraes.creational.abstractFactory.example01.luxury.CarLuxury;
import com.rafaelmoraes.creational.abstractFactory.example01.luxury.MotorcycleLuxury;

public class LuxuryVehicleFactory implements VehicleFactory{
    @Override
    public Car createCar() {
        return new CarLuxury();
    }

    @Override
    public Motorcycle createMotorcycle() {
        return new MotorcycleLuxury();
    }
}
