//Paul Schmidt, Matrikelnummer: 2657411
package de.smarthome.devices;
import de.smarthome.home.rooms.Room;

/**
 * Stellt einen Rolladen da, welcher
 * eine Position hat.
 */
public class Shutter extends Device {

    private int position;

    /**
     * Konstruktor für einen Rolladen mit Name, Raumzuordnung und Leistungsaufnahme.
     *
     * @param name Name des Rolladens
     * @param room Raum, in dem sich der Rolladens befindet
     * @param powerRating Leistungsaufnahme des Rolladenss in Watt
     */
    public Shutter(String name, Room room, double powerRating){
        super(name, room, powerRating);
    }

    /**
     *Legt die Position des Rolladens fest
     * @param position Position eines Rolladens
     */
    public void setPosition(int position){
        if(position < 0 || position > 10){
            System.out.println("Position has to be in 0-10 rate.");
            return;
        }
        this.position = position;
    }

    /**
     *Gibt die Position eines Rolladens zurück
     * @return position des Rolladens
     */
    public int getPosition(){
        return position;
    }

    /**
     *Öffnet einen Rolladen
     */
    public void open(){
        this.position = 10;
        System.out.println("Opening shutter.");
    }

    /**
     *Schließt einen Rolladen
     */
    public void close(){
        this.position = 0;
        System.out.println("Closing shutter.");
    }

    /**
     *Gibt die Spezifikationen eine Rollandens aus
     * @return Betriebszustand, Name, Raum, und Position
     */
    @Override
    public String toString(){
        return super.toString() +
                "\nPosition: " + position;
    }
}
