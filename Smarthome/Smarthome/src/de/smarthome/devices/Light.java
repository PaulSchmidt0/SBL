//Paul Schmidt, Matrikelnummer: 2657411
package de.smarthome.devices;
import de.smarthome.home.rooms.Room;

/**
 * Stellt ein licht da, welches
 * Helligkeit und Farbtemperatur hat.
 */
public class Light extends Device {

    private int brightness;
    private int colorTemperature;

    /**
     * Konstruktor für ein Licht mit Name, Raumzuordnung und Leistungsaufnahme.
     *
     * @param name Name des Lichts
     * @param room Raum, in dem sich das Licht befindet
     * @param powerRating Leistungsaufnahme des Lichts in Watt
     */
    public Light(String name, Room room, double powerRating) {
        super(name, room, powerRating);
    }

    /**
     *Bestimmt die Helligkeit eines Lichts
     * @param brightness Helligkeit des Lichts
     */
    public void setBrightness(int brightness) {
        if (brightness < 0 || brightness > 100) {
            System.out.println("Invalid brightness value!");
            return;
        }
        this.brightness = brightness;
    }

    /**
     *Gibt die Helligkeit eines Lichts zurück
     * @return brightness des Lichts
     */
    public int getBrightness() {
        return brightness;
    }

    /**
     *Bestimmt die Farbe eines Lichts
     * @param colorTemperature Farbe des Lichts
     */
    public void setColorTemperature(int colorTemperature) {
        if (colorTemperature < 1000 || colorTemperature > 10000) {
            System.out.println("Invalid color temperature!");
            return;
        }
        this.colorTemperature = colorTemperature;
    }

    /**
     *Gibt die Farbe eines Lichts zurück
     * @return colorTemperature des Lichts
     */
    public int getColorTemperature() {
        return colorTemperature;
    }

    /**
     *Gibt die Spezifikationen eine Lichts aus
     * @return Betriebszustand, Name, Raum, Helligkeit und Farbe
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nBrightness: " + brightness +
                "\nColor Temperature: " + colorTemperature;
    }
}
