//Adam Benamar, Matrikelnummer: 2679028
package de.smarthome.home.routines;
import de.smarthome.devices.Schedulable;
import java.util.ArrayList;
import de.smarthome.devices.Device;

public class Routine {

    private String name;
    private String description;
    private String startTime;
    private String endTime;
    private ArrayList<Schedulable> devices;

    /**
     * Konstruktor zur Erstellung einer neuen Routine.
     * Initialisiert Name, Beschreibung, Start- und Endzeit
     * sowie eine leere Geräteliste.
     */
    public Routine(String name, String description, String startTime, String endTime) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.devices = new ArrayList<>();
    }


    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }


    public String getStartTime() {
        return startTime;
    }


    public String getEndTime() {
        return endTime;
    }


    public ArrayList<Schedulable> getDevices() {
        return devices;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void addDevice(Schedulable device) {
        devices.add(device);
    }


    public void removeDevice(Schedulable device) {
        devices.remove(device);
    }

    /**
     * Führt die Routine aus, indem alle enthaltenen Geräte
     * eingeplant und ausgeführt werden.
     */
    public void execute() {
        System.out.println("Executing routine: " + name);

        for (Schedulable device : devices) {
            device.schedule(startTime, endTime);

            if (device instanceof Device d) {
                d.switchOn();
            }
            
        }
    }
}
