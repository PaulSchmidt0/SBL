//Adam Benamar, Matrikelnummer: 2679028
package de.smarthome.home.routines;

import java.util.ArrayList;
import java.io.*;
import de.smarthome.home.rooms.Room;
import de.smarthome.devices.Device;
import de.smarthome.devices.Schedulable;

/**
 * Die Klasse RoutineManager verwaltet alle Routinen im Smart-Home-System.
 * Sie ermöglicht das Hinzufügen, Entfernen und Suchen von Routinen sowie
 * das Laden und Speichern der Routinen in einer CSV-Datei.
*/
public class RoutineManager {

    // Liste aller vorhandenen Routinen
    private ArrayList<Routine> routines;
    // Pfad zur CSV-Datei für die Routinen
    private static final String FILE_PATH = "data/routines.csv";

    /**
     * Konstruktor: Initialisiert eine leere Routinenliste.
     */
    public RoutineManager() {
        routines = new ArrayList<>();
    }

    /**
     * Gibt die Liste aller gespeicherten Routinen zurück.
     */
    public ArrayList<Routine> getRoutines() {
        return routines;
    }

    /**
     * Fügt eine neue Routine hinzu, sofern keine Routine
     * mit demselben Namen bereits existiert.
     * Anschließend werden alle Routinen gespeichert.
     */
    public void addRoutine(Routine routine) {

        if (findRoutineByName(routine.getName()) != null) {
            System.out.println("Routine with this name already exists!");
            return;
        }

        routines.add(routine);
        saveAllRoutines();
    }

    /**
     * Entfernt eine Routine aus der Liste und speichert
     * die aktualisierte Routinenliste in der CSV-Datei.
     */
    public void removeRoutine(Routine routine) {
        routines.remove(routine);
        saveAllRoutines();
    }

    /**
     * Sucht eine Routine anhand ihres Namens.
     * Gibt die gefundene Routine zurück oder null,
     * falls keine passende Routine existiert.
     */
    public Routine findRoutineByName(String routineName) {
        for (Routine routine : routines) {
            if (routine.getName().equals(routineName)) {
                return routine;
            }
        }
        return null;
    }

    /**
     * Lädt alle Routinen aus der CSV-Datei und fügt sie
     * der internen Routinenliste hinzu.
     * Die Geräte werden anhand ihres Namens den
     * übergebenen Räumen zugeordnet.
     */
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

    /**
     * Speichert alle vorhandenen Routinen in der CSV-Datei.
     * Das Verzeichnis wird erstellt, falls es nicht existiert.
     */
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

    /**
     * Sucht ein Gerät anhand seines Namens in allen
     * übergebenen Räumen.
     * Gibt das gefundene Gerät zurück oder null.
     */
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
