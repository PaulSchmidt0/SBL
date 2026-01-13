package de.smarthome.home.rooms;

import de.smarthome.devices.Device;

import java.util.ArrayList;

public class Room {

    private String name;
    private double height;
    private double width;
    private double length;
    private ArrayList<Device> devices;

    public Room(String name, double width, double length, double height, ArrayList<Device> devices){
        this.name = name;
        this.height = height;
        this.width = width;
        this.length = length;
        this.devices = devices;
    }

    public Room(String name, double width, double length, double height){
        this.name = name;
        this.height = height;
        this.width = width;
        this.length = length;
        this.devices = new ArrayList<>();
    }

    public String setName(String name){
        this.name = name;
        return this.name;
    }

    public String getName(){
        return name;
    }

    public double getWidth(){
        return width;
    }

    public double getLength(){
        return length;
    }

    public double getHeight(){
        return height;
    }

    public double getArea(){
        return width * length;
    }

    public double getVolume(){
        return width * height * length;
    }

    public ArrayList<Device> getDevices(){
        return devices;
    }

    public void addDevice(Device device){
        devices.add(device);
    }

    public void removeDevice(Device device){
        devices.remove(device);
    }

    @Override
    public String toString(){
        return "Room: " + name + " (" + width + " x " + height + " x " + length + ")"
                + "\nDevices: " + devices.size();
    }
}
