//Paul Schmidt, Matrikelnummer: 2657411
package de.smarthome.home.rooms;

import de.smarthome.devices.Device;

import java.util.ArrayList;

/**
 * Stellt einen Raum da, welcher
 * Name, Höhe, Breite, Länge und eine Liste mit Geräten besitzt
 */
public class Room {

    private String name;
    private double height;
    private double width;
    private double length;
    private ArrayList<Device> devices;

    /**
     * Konstruktor für einen Raum mit Name, Höhe, Breite, Länge und Geräten.
     *
     * @param name Name des Raums
     * @param width Breite des Raumes
     * @param length Länge des Raums
     * @param height Höhe des Raumes
     * @param devices Geräte im Raum
     */
    public Room(String name, double width, double length, double height, ArrayList<Device> devices){
        this.name = name;
        this.height = height;
        this.width = width;
        this.length = length;
        this.devices = devices;
    }

    /**
     * Konstruktor für einen Raum mit Name, Höhe, Breite und Länge.
     *
     * @param name Name des Raums
     * @param width Breite des Raumes
     * @param length Länge des Raums
     * @param height Höhe des Raumes
     */
    public Room(String name, double width, double length, double height){
        this.name = name;
        this.height = height;
        this.width = width;
        this.length = length;
        this.devices = new ArrayList<>();
    }

    /**
     *Setzt den Namen auf den neuen Wert
     * @param name neuer Name
     */
    public String setName(String name){
        this.name = name;
        return this.name;
    }

    /**
     *Gibt den Namen zurück
     * @return name Name des Raumes
     */
    public String getName(){
        return name;
    }

    /**
     *Gibt die Breite zurück
     * @return widht Breite des Raumes
     */
    public double getWidth(){
        return width;
    }

    /**
     *Gibt die Länge zurück
     * @return length Länge des Raumes
     */
    public double getLength(){
        return length;
    }

    /**
     *Gibt die Höhe zurück
     * @return height Höhe des Raumes
     */
    public double getHeight(){
        return height;
    }

    /**
     *Gibt die Fläche zurück
     * @return area = wight * lenght, Fläche = Breite * Länge
     */
    public double getArea(){
        return width * length;
    }

    /**
     *Gibt das Volumen zurück
     * @return volume = wight * height * length, Volumen = Breite * Höhe * Länge
     */
    public double getVolume(){
        return width * height * length;
    }

    /**
     *Gibt die Geräte eines Raumes zurück
     * @return devices Liste der Geräte
     */
    public ArrayList<Device> getDevices(){
        return devices;
    }

    /**
     *Fügt ein Gerät hinzu
     * @param device zur Liste der Geräte
     */
    public void addDevice(Device device){
        devices.add(device);
    }

    /**
     *Löscht ein Gerät aus der Liste
     * @param device von der Liste der Geräte
     */
    public void removeDevice(Device device){
        devices.remove(device);
    }

    /**
     *Gibt die Spezifikationen eins Raumes aus
     * @return Name, Breite, Höhe, Länge und Anzahl der Geräte
     */
    @Override
    public String toString(){
        return "Room: " + name + " (" + width + " x " + height + " x " + length + ")"
                + "\nDevices: " + devices.size();
    }
}
