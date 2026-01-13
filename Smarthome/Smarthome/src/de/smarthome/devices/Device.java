package de.smarthome.devices;
import de.smarthome.home.rooms.Room;

public abstract class Device implements Schedulable {

    // room.addDevice(this) !?

    private int id;
    private String name;
    private Room room;
    private double powerRating;
    private boolean isOn;
    private String scheduleStart;
    private String scheduleEnd;
    private boolean scheduleActive;
    private static int nextId = 0;

    public Device(String name, Room room, double powerRating) {
        this.name = name;
        this.room = room;
        this.powerRating = powerRating;
        this.id = generateId();
        this.isOn = false;
        this.scheduleActive = false;
        this.scheduleStart = null;
        this.scheduleEnd = null;
    }

    private int generateId() {
        return nextId++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Room getRoom(){
        return room;
    }

    public double getPowerRating() {
        return powerRating;
    }

    public void setPowerRating(double powerRating) {
        this.powerRating = powerRating;
    }

    public boolean isOn() {
        return isOn;
    }

    public void switchOn() {
        if(isOn == false) {
            isOn = true;
        }
    }

    public void  switchOff() {
        if(isOn) {
            isOn = false;
        }

    }

    public void switchToggle(){
        isOn = !isOn;
    }

    public boolean isScheduled() {
        return scheduleActive;
    }

    public String getScheduleStart() {
        return scheduleStart;
    }

    public String getScheduleEnd() {
        return scheduleEnd;
    }

    @Override
    public void schedule(String startTime, String endTime) {
        scheduleStart = startTime;
        scheduleEnd = endTime;
        scheduleActive = true;
        System.out.println("Device scheduled from " + startTime + " to " + endTime);
    }

    @Override
    public void cancelSchedule() {
        scheduleActive = false;
        scheduleEnd = null;
        scheduleStart = null;
        System.out.println("Schedule canceled for device: " + name);
    }

    @Override
    public String toString() {
        String statusText = isOn ? "On" : "Off";
        return "Device: " + name + " " + room.getName() + "\nStatus is on: " + statusText;
    }


}
