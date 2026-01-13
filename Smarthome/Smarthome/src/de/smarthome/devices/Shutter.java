package de.smarthome.devices;
import de.smarthome.home.rooms.Room;

public class Shutter extends Device {

    private int position;

    public Shutter(String name, Room room, double powerRating){
        super(name, room, powerRating);
    }

    public void setPosition(int position){
        if(position < 0 || position > 10){
            System.out.println("Position has to be in 0-10 rate.");
            return;
        }
        this.position = position;
    }

    public int getPosition(){
        return position;
    }

    public void open(){
        this.position = 10;
        System.out.println("Opening shutter.");
    }

    public void close(){
        this.position = 0;
        System.out.println("Closing shutter.");
    }

    @Override
    public String toString(){
        return super.toString() +
                "\nPosition: " + position;
    }
}
