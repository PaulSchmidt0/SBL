package de.smarthome.home.routines;
import de.smarthome.devices.Schedulable;
import java.util.ArrayList;

public class Routine {

    private String name;
    private String description;
    private String startTime;
    private String endTime;
    private ArrayList<Schedulable> devices;

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

    public void execute() {
        System.out.println("Executing routine: " + name);

        for (Schedulable device : devices) {
            device.schedule(startTime, endTime);
            
        }
    }
}
