package de.smarthome.home.routines;

import java.util.ArrayList;
import java.io.*;
import de.smarthome.home.rooms.Room;
import de.smarthome.devices.Device;
import de.smarthome.devices.Schedulable;

public class RoutineManager {

    private ArrayList<Routine> routines;
    private static final String FILE_PATH = "data/routines.csv";

    public RoutineManager() {
        routines = new ArrayList<>();
    }

    public ArrayList<Routine> getRoutines() {
        return routines;
    }

    public void addRoutine(Routine routine) {

        if (findRoutineByName(routine.getName()) != null) {
            System.out.println("Routine with this name already exists!");
            return;
        }

        routines.add(routine);
        saveAllRoutines();
    }

    public void removeRoutine(Routine routine) {
        routines.remove(routine);
        saveAllRoutines();
    }

    public Routine findRoutineByName(String routineName) {
        for (Routine routine : routines) {
            if (routine.getName().equals(routineName)) {
                return routine;
            }
        }
        return null;
    }

    public void loadRoutines(ArrayList<Room> rooms) {

        routines.clear();

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts.length != 5) {
                    continue;
                }

                Routine routine = new Routine(
                        parts[0],
                        parts[1],
                        parts[2],
                        parts[3]
                );

                String[] deviceNames = parts[4].split("\\|");

                for (String deviceName : deviceNames) {
                    Schedulable device = findDeviceByName(deviceName, rooms);
                    if (device != null) {
                        routine.addDevice(device);
                    }
                }

                routines.add(routine);
            }

        } catch (IOException e) {
            System.out.println("Error loading routines!");
        }
    }

    public void saveAllRoutines() {

        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (FileWriter writer = new FileWriter(FILE_PATH)) {

            for (Routine routine : routines) {

                StringBuilder deviceNames = new StringBuilder();

                for (Schedulable device : routine.getDevices()) {
                    Device d = (Device) device;
                    deviceNames.append(d.getName()).append("|");
                }

                writer.write(
                        routine.getName() + "," + routine.getDescription() + "," + routine.getStartTime() + "," + routine.getEndTime() + "," + deviceNames + "\n"
                );
            }

        } catch (IOException e) {
            System.out.println("Error saving routines!");
        }
    }

    private Schedulable findDeviceByName(String name, ArrayList<Room> rooms) {

        for (Room room : rooms) {
            for (Device device : room.getDevices()) {
                if (device.getName().equals(name)) {
                    return device;
                }
            }
        }
        return null;
    }
}
