//Adam Benamar, 2679028

package de.smarthome.home.statistics;

import java.util.ArrayList;
import de.smarthome.home.rooms.Room;
import de.smarthome.devices.Device;

public class Statistics {

    // Energiepreis pro Kilowattstunde
    private double energyPricePerKWh;

    /**
     * Konstruktor mit frei wählbarem Energiepreis.
     */
    public Statistics(double energyPricePerKWh) {
        this.energyPricePerKWh = energyPricePerKWh;
    }

    /**
     * Standardkonstruktor mit einem festen Energiepreis von 0.30 €/kWh.
     */
    public Statistics() {
        this.energyPricePerKWh = 0.30;
    }

    /**
     * Gibt den aktuell verwendeten Energiepreis pro kWh zurück.
     */
    public double getEnergyPricePerKWh() {
        return energyPricePerKWh;
    }

    /**
     * Wandelt eine Zeitangabe im Format "HH:MM" in Minuten um.
     */
    public int parseTimeToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    /**
     * Berechnet den gesamten Energieverbrauch aller eingeschalteten
     * Geräte in allen übergebenen Räumen für einen bestimmten Zeitraum.
     */
    public double calculateTotalEnergyConsumption(
            ArrayList<Room> rooms,
            String startTime,
            String endTime) {

        double totalConsumption = 0;

        // Summiert den Energieverbrauch aller Räume
        for (Room room : rooms) {
            totalConsumption += calculateRoomEnergyConsumption(
                    room, startTime, endTime);
        }

        return totalConsumption;
    }

    /**
     * Berechnet die Gesamtkosten für den Energieverbrauch
     * aller übergebenen Räume.
     */
    public double calculateTotalCost(
            ArrayList<Room> rooms,
            String startTime,
            String endTime) {

        double consumption = calculateTotalEnergyConsumption(
                rooms, startTime, endTime);

        return consumption * energyPricePerKWh;
    }

    /**
     * Berechnet den Energieverbrauch aller eingeschalteten Geräte
     * in einem einzelnen Raum für einen bestimmten Zeitraum.
     */
    public double calculateRoomEnergyConsumption(
            Room room,
            String startTime,
            String endTime) {

        int startMinutes = parseTimeToMinutes(startTime);
        int endMinutes = parseTimeToMinutes(endTime);

        // Dauer des Zeitraums in Minuten
        int durationMinutes = endMinutes - startMinutes;

        double energyConsumption = 0;

        // Berücksichtigt nur eingeschaltete Geräte
        for (Device device : room.getDevices()) {
            if (device.isOn()) {
                double hours = durationMinutes / 60.0;
                energyConsumption += device.getPowerRating() * hours;
            }
        }

        return energyConsumption;
    }

    /**
     * Berechnet die Kosten für den Energieverbrauch eines einzelnen Raums.
     */
    public double calculateRoomCost(
            Room room,
            String startTime,
            String endTime) {

        double consumption = calculateRoomEnergyConsumption(
                room, startTime, endTime);

        return consumption * energyPricePerKWh;
    }
}
