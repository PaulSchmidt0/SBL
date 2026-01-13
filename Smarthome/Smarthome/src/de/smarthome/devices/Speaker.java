package de.smarthome.devices;
import de.smarthome.home.rooms.Room;

public class Speaker extends Device {

    private int volume;
    private boolean muted;
    private String currentSong;

    public Speaker(String name, Room room, double powerRating){
        super(name, room, powerRating);
    }

    public String playSong(String song){
        this.currentSong = song;
        return currentSong;
    }

    public void setVolume(int volume){
        if(volume < 0 || volume > 100){
            System.out.println("Volume must be between 0 and 100");
            return;
        }
        this.volume = volume;
    }

    public int getVolume(){
        return this.volume;
    }

    public void toggleMuted(){
        this.muted = !this.muted;
        System.out.println("Mute " + (this.muted ? "ON" : "OFF"));
    }

    public boolean isMuted(){
        return this.muted;
    }

    @Override
    public String toString(){
        String songName = (currentSong == null) ? "No song playing" : currentSong;
        return super.toString() +
                "\nVolume: " + volume +
                "\nCurrently Playing: " + songName;
    }
}
