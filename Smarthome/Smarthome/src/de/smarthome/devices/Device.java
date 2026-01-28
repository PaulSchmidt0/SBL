//Paul Schmidt, Matrikelnummer: 2657411
package de.smarthome.devices;
import de.smarthome.home.rooms.Room;

/**
 * Abstrakte Basisklasse für alle Geräte im Smart-Home-System.
 * Ein Device besitzt eine eindeutige ID, einen Namen, eine Raumzuordnung
 * sowie einen Betriebszustand und unterstützt zeitbasierte Steuerung.
 */
public abstract class Device implements Schedulable {



    private int id;
    private String name;
    private Room room;
    private double powerRating;
    private boolean isOn;
    private String scheduleStart;
    private String scheduleEnd;
    private boolean scheduleActive;
    private static int nextId = 0;

    /**
     * Konstruktor für ein Device mit Name, Raumzuordnung und Leistungsaufnahme.
     *
     * @param name Name des Geräts
     * @param room Raum, in dem sich das Gerät befindet
     * @param powerRating Leistungsaufnahme des Geräts in Watt
     */
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

    /**
     *Generiert eine neue ID für ein Device
     * @return eindeutige ID des Geräts
     */
    private int generateId() {
        return nextId++;
    }

    /**
     *Gibt die ID eines Gerätes zurück
     * @return ID des Gerätes
     */
    public int getId() {
        return id;
    }

    /**
     *Gibt den Namen eines Gerätes zurück
     * @return Name das Gerätes
     */
    public String getName() {
        return name;
    }

    /**
     *Gibt den Raum eines Gerätes zurück
     * @return Raum
     */
    public Room getRoom(){
        return room;
    }

    /**
     *Gibt die Power eines Gerätes zurück
     * @return powerRating des Gerätes
     */
    public double getPowerRating() {
        return powerRating;
    }

    /**
     *Bestimmt die Power eines Gerätes
     * @param powerRating des Gerätes
     */
    public void setPowerRating(double powerRating) {
        this.powerRating = powerRating;
    }

    /**
     *Gibt zurück ob ein Gerät an ist
     * @return isOn Ja oder Nein
     */
    public boolean isOn() {
        return isOn;
    }

    /**
     *Schaltet ein Gerät ein
     */
    public void switchOn() {
        if(isOn == false) {
            isOn = true;
        }
    }

    /**
     *Schaltet ein Gerät aus
     */
    public void  switchOff() {
        if(isOn) {
            isOn = false;
        }

    }

    /**
     *Ändert den Betriebszustand eines Gerätes
     */
    public void switchToggle(){
        isOn = !isOn;
    }

    /**
     *Gibt zurück ob ein Gerät einen aktiven Plan hat
     * @return scheduleActive Ja oder Nein
     */
    public boolean isScheduled() {
        return scheduleActive;
    }

    /**
     *Gibt den Startpunkt des Plans zurück
     * @return die Startuhrzeit
     */
    public String getScheduleStart() {
        return scheduleStart;
    }

    /**
     *Gibt den Endpunkt des Plans zurück
     * @return die Enduhrzeit
     */
    public String getScheduleEnd() {
        return scheduleEnd;
    }

    /**
     *Legt einen neuen Plan fest
     * @param startTime Startzeit des Plans
     * @param endTime Endzeit des Plans
     */
    @Override
    public void schedule(String startTime, String endTime) {
        scheduleStart = startTime;
        scheduleEnd = endTime;
        scheduleActive = true;
        System.out.println("Device scheduled from " + startTime + " to " + endTime);
    }

    /**
     *Löscht einen Plan
     */
    @Override
    public void cancelSchedule() {
        scheduleActive = false;
        scheduleEnd = null;
        scheduleStart = null;
        System.out.println("Schedule canceled for device: " + name);
    }

    /**
     *Gibt die Spezifikationen eine Gerätes aus
     * @return Betriebszustand, Name und Raum
     */
    @Override
    public String toString() {
        String statusText = isOn ? "On" : "Off";
        return "Device: " + name + " " + room.getName() + "\nStatus is on: " + statusText;
    }


}
