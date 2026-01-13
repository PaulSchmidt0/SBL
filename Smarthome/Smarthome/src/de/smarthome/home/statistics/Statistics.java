package de.smarthome.home.statistics;

import java.util.ArrayList;
import de.smarthome.home.rooms.Room;
import de.smarthome.home.devices.Device;

public class Statistics {

    private double energyPricePerKWh;

    public Statistics(double energyPricePerKWh) {
        this.energyPricePerKWh = energyPricePerKWh;
    }

    public Statistics() {
        this.energyPricePerKWh = 0.30;
    }

    public double getEnergyPricePerKWh() {
        return energyPricePerKWh;
    }

    public int parseTimeToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    public double calculateTotalEnergyConsumption(
            ArrayList<Room> rooms,
            String startTime,
            String endTime) {

        double totalConsumption = 0;

        for (Room room : rooms) {
            totalConsumption += calculateRoomEnergyConsumption(
                    room, startTime, endTime);
        }

        return totalConsumption;
    }

    public double calculateTotalCost(
            ArrayList<Room> rooms,
            String startTime,
            String endTime) {

        double consumption = calculateTotalEnergyConsumption(
                rooms, startTime, endTime);

        return consumption * energyPricePerKWh;
    }

    public double calculateRoomEnergyConsumption(
            Room room,
            String startTime,
            String endTime) {

        int startMinutes = parseTimeToMinutes(startTime);
        int endMinutes = parseTimeToMinutes(endTime);
        int durationMinutes = endMinutes - startMinutes;

        double energyConsumption = 0;

        for (Device device : room.getDevices()) {
            if (device.isOn()) {
                double hours = durationMinutes / 60.0;
                energyConsumption += device.getPowerRating() * hours;
            }
        }

        return energyConsumption;
    }

    public double calculateRoomCost(
            Room room,
            String startTime,
            String endTime) {

        double consumption = calculateRoomEnergyConsumption(
                room, startTime, endTime);

        return consumption * energyPricePerKWh;
    }
}
