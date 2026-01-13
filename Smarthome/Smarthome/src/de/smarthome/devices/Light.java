package de.smarthome.devices;
import de.smarthome.home.rooms.Room;

public class Light extends Device {

    private int brightness;
    private int colorTemperature;

    public Light(String name, Room room, double powerRating) {
        super(name, room, powerRating);
    }

    public void setBrightness(int brightness) {
        if (brightness < 0 || brightness > 100) {
            System.out.println("Invalid brightness value!");
            return;
        }
        this.brightness = brightness;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setColorTemperature(int colorTemperature) {
        if (colorTemperature < 1000 || colorTemperature > 10000) {
            System.out.println("Invalid color temperature!");
            return;
        }
        this.colorTemperature = colorTemperature;
    }

    public int getColorTemperature() {
        return colorTemperature;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nBrightness: " + brightness +
                "\nColor Temperature: " + colorTemperature;
    }
}
