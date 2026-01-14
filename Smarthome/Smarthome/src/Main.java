package de.smarthome;

import de.smarthome.account.AccountManager;
import de.smarthome.account.User;
import de.smarthome.home.rooms.Room;
import de.smarthome.devices.Light;
import de.smarthome.devices.Shutter;
import de.smarthome.devices.Speaker;
import de.smarthome.devices.Device;
import de.smarthome.home.routines.Routine;
import de.smarthome.home.routines.RoutineManager;
import de.smarthome.home.statistics.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== SmartHome Test Start ===");

        // ---------- 1. Benutzerverwaltung testen ----------
        AccountManager accountManager = new AccountManager();

        System.out.println("\n--- Create Users ---");
        accountManager.createUser("parent1", "1234", "Max", "Muster", true);
        accountManager.createUser("child1", "abcd", "Tim", "Muster", false);

        System.out.println("\n--- All Users ---");
        for (User u : accountManager.getAllUsers()) {
            System.out.println(u);
        }

        System.out.println("\n--- Login as parent1 ---");
        User current = accountManager.login("parent1", "1234");
        System.out.println("Current user: " + (current != null ? current.getUsername() : "none"));

        // ---------- 2. Räume & Devices testen ----------
        System.out.println("\n--- Create Room ---");
        ArrayList<Room> rooms = new ArrayList<>();
        Room livingRoom = new Room("Living Room", 5.0, 4.0, 2.5);
        rooms.add(livingRoom);
        System.out.println(livingRoom);

        System.out.println("\n--- Add Devices to Room ---");
        Device light = new Light("Ceiling Light", livingRoom, 60.0);
        Device shutter = new Shutter("Window Shutter", livingRoom, 10.0);
        Device speaker = new Speaker("Living Room Speaker", livingRoom, 30.0);

        livingRoom.addDevice(light);
        livingRoom.addDevice(shutter);
        livingRoom.addDevice(speaker);

        System.out.println("Devices in room:");
        for (Device d : livingRoom.getDevices()) {
            System.out.println(d);
        }

        // ---------- 3. Devices steuern ----------
        System.out.println("\n--- Switch devices on and set properties ---");
        light.switchOn();
        ((Light) light).setBrightness(80);
        ((Light) light).setColorTemperature(3000);

        ((Shutter) shutter).setPosition(5);
        ((Speaker) speaker).setVolume(50);
        ((Speaker) speaker).playSong("Test Song");

        for (Device d : livingRoom.getDevices()) {
            System.out.println(d);
        }

        // ---------- 4. Routine erstellen & ausführen ----------
        System.out.println("\n--- Create and execute Routine ---");
        RoutineManager routineManager = new RoutineManager();
        Routine morningRoutine = new Routine(
                "Morning Routine",
                "Turn on lights and open shutters",
                "07:00",
                "08:00"
        );

        morningRoutine.addDevice(light);    // Device implementiert Schedulable
        morningRoutine.addDevice(shutter);

        routineManager.addRoutine(morningRoutine);

        System.out.println("Executing Morning Routine:");
        morningRoutine.execute();   // hier in deiner Routine: schedule() + ggf. switchOn()

        // ---------- 5. Statistik testen ----------
        System.out.println("\n--- Statistics ---");
        Statistics statistics = new Statistics(0.30);

        String startTime = "07:00";
        String endTime = "09:00";

        double totalConsumption = statistics.calculateTotalEnergyConsumption(rooms, startTime, endTime);
        double totalCost = statistics.calculateTotalCost(rooms, startTime, endTime);

        System.out.println("Total consumption from " + startTime + " to " + endTime + ": " + totalConsumption + " kWh");
        System.out.println("Total cost: " + totalCost + " EUR");

        System.out.println("\n=== SmartHome Test End ===");
    }

}
