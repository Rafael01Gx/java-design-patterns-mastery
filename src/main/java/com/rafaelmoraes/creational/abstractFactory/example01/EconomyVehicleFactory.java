package com.rafaelmoraes.creational.abstractFactory.example01;

import com.rafaelmoraes.creational.abstractFactory.example01.economy.CarEconomy;
import com.rafaelmoraes.creational.abstractFactory.example01.economy.MotorcycleEconomy;

public class EconomyVehicleFactory implements VehicleFactory {

    @Override
    public Car createCar() {
        return new CarEconomy();
    }

    @Override
    public Motorcycle createMotorcycle() {
        return new MotorcycleEconomy();
    }
}
