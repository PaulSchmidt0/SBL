package de.smarthome;

import de.smarthome.account.*;
import de.smarthome.devices.*;
import de.smarthome.home.rooms.Room;
import de.smarthome.home.routines.*;
import de.smarthome.home.statistics.Statistics;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        AccountManager accountManager = new AccountManager();
        RoutineManager routineManager = new RoutineManager();

        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Device> allDevices = new ArrayList<>();

        boolean running = true;

        System.out.println("=== SmartHome System ===");

        while (running) {

            System.out.println("""
                    
                    1 Benutzer anlegen
                    2 Login
                    3 Raum erstellen
                    4 Device erstellen & Raum zuweisen
                    5 Räume & Devices anzeigen
                    6 Routine erstellen & speichern
                    7 Routinen laden & anzeigen
                    8 Statistik berechnen
                    9 Devices Ein-/Ausschalten
                    0 Beenden
                    """);

            System.out.print("Auswahl: ");
            String choice = scanner.nextLine();

            switch (choice) {

                case "1" -> {
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Passwort: ");
                    String pw = scanner.nextLine();
                    System.out.print("Vorname: ");
                    String fn = scanner.nextLine();
                    System.out.print("Nachname: ");
                    String ln = scanner.nextLine();
                    System.out.print("Parent? (y/n): ");
                    boolean isParent = scanner.nextLine().equalsIgnoreCase("y");

                    accountManager.createUser(username, pw, fn, ln, isParent);
                }

                case "2" -> {
                    System.out.print("Username: ");
                    String u = scanner.nextLine();
                    System.out.print("Passwort: ");
                    String p = scanner.nextLine();
                    User user = accountManager.login(u, p);
                    System.out.println(user != null ? "Login erfolgreich" : "Login fehlgeschlagen");
                }

                case "3" -> {
                    System.out.print("Raumname: ");
                    String name = scanner.nextLine();
                    System.out.print("Breite: ");
                    double w = Double.parseDouble(scanner.nextLine());
                    System.out.print("Länge: ");
                    double l = Double.parseDouble(scanner.nextLine());
                    System.out.print("Höhe: ");
                    double h = Double.parseDouble(scanner.nextLine());

                    Room room = new Room(name, w, l, h);
                    rooms.add(room);
                    System.out.println("Raum erstellt.");
                }

                case "4" -> {
                    if (rooms.isEmpty()) {
                        System.out.println("Bitte zuerst einen Raum erstellen.");
                        break;
                    }

                    System.out.println("Device-Typ wählen:");
                    System.out.println("1 = Light");
                    System.out.println("2 = Shutter");
                    System.out.println("3 = Speaker");
                    String type = scanner.nextLine();

                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Leistungsaufnahme (Watt): ");
                    double power = Double.parseDouble(scanner.nextLine());

                    System.out.println("Raum wählen:");
                    for (int i = 0; i < rooms.size(); i++) {
                        System.out.println(i + ": " + rooms.get(i).getName());
                    }
                    int roomIndex = Integer.parseInt(scanner.nextLine());
                    Room room = rooms.get(roomIndex);

                    Device device = null;

                    // ---------- LIGHT ----------
                    if (type.equals("1")) {
                        Light l = new Light(name, room, power);

                        System.out.print("Helligkeit (0-100): ");
                        l.setBrightness(Integer.parseInt(scanner.nextLine()));

                        System.out.print("Farbtemperatur (1000-10000): ");
                        l.setColorTemperature(Integer.parseInt(scanner.nextLine()));

                        device = l;
                    }

                    // ---------- SHUTTER ----------
                    else if (type.equals("2")) {
                        Shutter s = new Shutter(name, room, power);

                        System.out.print("Startposition (0-10): ");
                        s.setPosition(Integer.parseInt(scanner.nextLine()));

                        device = s;
                    }

                    // ---------- SPEAKER ----------
                    else if (type.equals("3")) {
                        Speaker sp = new Speaker(name, room, power);

                        System.out.print("Startlautstärke (0-100): ");
                        sp.setVolume(Integer.parseInt(scanner.nextLine()));

                        System.out.print("Stumm schalten? (y/n): ");
                        if (scanner.nextLine().equalsIgnoreCase("y")) {
                            sp.toggleMuted();
                        }

                        device = sp;
                    }

                    if (device != null) {
                        room.addDevice(device);
                        allDevices.add(device);
                        System.out.println("Device erfolgreich erstellt.");
                    } else {
                        System.out.println("Ungültiger Device-Typ.");
                    }
                }


                case "5" -> {
                    for (Room r : rooms) {
                        System.out.println(r);
                        for (Device d : r.getDevices()) {
                            System.out.println("  - " + d);
                        }
                    }
                }

                case "6" -> {
                    System.out.print("Routine-Name: ");
                    String n = scanner.nextLine();
                    System.out.print("Beschreibung: ");
                    String d = scanner.nextLine();
                    System.out.print("Start (HH:mm): ");
                    String s = scanner.nextLine();
                    System.out.print("Ende (HH:mm): ");
                    String e = scanner.nextLine();

                    Routine routine = new Routine(n, d, s, e);

                    for (Device dev : allDevices) {
                        System.out.print("Device '" + dev.getName() + "' hinzufügen? (y/n): ");
                        if (scanner.nextLine().equalsIgnoreCase("y")) {
                            routine.addDevice(dev);
                        }
                    }

                    routineManager.addRoutine(routine);
                    System.out.println("Routine gespeichert.");
                }

                case "7" -> {
                    routineManager.loadRoutines(rooms);
                    for (Routine r : routineManager.getRoutines()) {
                        System.out.println(r.getName());
                    }
                }

                case "8" -> {

                    if (routineManager.getRoutines().isEmpty()) {
                        System.out.println("Keine Routinen vorhanden.");
                        break;
                    }

                    System.out.println("Routine auswählen:");
                    for (int i = 0; i < routineManager.getRoutines().size(); i++) {
                        System.out.println(i + ": " + routineManager.getRoutines().get(i).getName());
                    }

                    int index = Integer.parseInt(scanner.nextLine());
                    Routine r = routineManager.getRoutines().get(index);

                    r.execute();

                    System.out.print("Preis pro kWh: ");
                    double price = Double.parseDouble(scanner.nextLine());

                    Statistics stats = new Statistics(price);

                    double consumption = stats.calculateTotalEnergyConsumption(
                            rooms,
                            r.getStartTime(),
                            r.getEndTime()
                    );

                    double cost = stats.calculateTotalCost(
                            rooms,
                            r.getStartTime(),
                            r.getEndTime()
                    );

                    System.out.println("Verbrauch: " + consumption + " kWh");
                    System.out.println("Kosten: " + cost + " €");
                }


                case "9" -> {

                    if (rooms.isEmpty()) {
                        System.out.println("Keine Räume vorhanden.");
                        break;
                    }

                    // ---------- Raum auswählen ----------
                    System.out.println("Raum auswählen:");
                    for (int i = 0; i < rooms.size(); i++) {
                        System.out.println(i + ": " + rooms.get(i).getName());
                    }

                    int roomIndex = Integer.parseInt(scanner.nextLine());
                    Room selectedRoom = rooms.get(roomIndex);

                    if (selectedRoom.getDevices().isEmpty()) {
                        System.out.println("Keine Devices in diesem Raum.");
                        break;
                    }

                    // ---------- Device auswählen ----------
                    System.out.println("Device auswählen:");
                    for (int i = 0; i < selectedRoom.getDevices().size(); i++) {
                        Device d = selectedRoom.getDevices().get(i);
                        System.out.println(i + ": " + d.getName() + " (Status: " + (d.isOn() ? "On" : "Off") + ")");
                    }

                    int deviceIndex = Integer.parseInt(scanner.nextLine());
                    Device device = selectedRoom.getDevices().get(deviceIndex);

                    // ---------- Schalten ----------
                    System.out.print("Device ein- oder ausschalten? (on/off): ");
                    String action = scanner.nextLine();

                    if (action.equalsIgnoreCase("on")) {
                        device.switchOn();
                        System.out.println(device.getName() + " wurde eingeschaltet.");
                    }
                    else if (action.equalsIgnoreCase("off")) {
                        device.switchOff();
                        System.out.println(device.getName() + " wurde ausgeschaltet.");
                    }
                    else {
                        System.out.println("Ungültige Eingabe.");
                    }
                }



                case "0" -> {
                    running = false;
                    System.out.println("Programm beendet.");
                }

                default -> System.out.println("Ungültige Eingabe.");
            }
        }

        scanner.close();
    }
}
