//Paul Schmidt, Matrikelnummer: 2657411
package de.smarthome.devices;
import de.smarthome.home.rooms.Room;

/**
 * Stellt einen Lautsprecher da, welcher
 * Lautstärke, Song und Stumm besitzt.
 */
public class Speaker extends Device {

    private int volume;
    private boolean muted;
    private String currentSong;

    /**
     * Konstruktor für einen Lautsprecher mit Name, Raumzuordnung und Leistungsaufnahme.
     *
     * @param name Name des Lautsprechers
     * @param room Raum, in dem sich der Lautsprecher befindet
     * @param powerRating Leistungsaufnahme des Lautsprechers in Watt
     */
    public Speaker(String name, Room room, double powerRating){
        super(name, room, powerRating);
    }

    /**
     *Setzt einen neunén Song ein und gibt diesen zurück
     * @param song Neuer Song
     * @return currentSong gespielter Song
     */
    public String playSong(String song){
        this.currentSong = song;
        return currentSong;
    }

    /**
     *Setzt die Lautstärke auf den neuen Wert
     * @param volume neue Lautstärke
     */
    public void setVolume(int volume){
        if(volume < 0 || volume > 100){
            System.out.println("Volume must be between 0 and 100");
            return;
        }
        this.volume = volume;
    }

    /**
     *Gibt die Lautstärke zurück
     * @return volume momentane Lautstärke
     */
    public int getVolume(){
        return this.volume;
    }

    /**
     *Stummt wenn nicht stumm und entsummt wenn stumm
     */
    public void toggleMuted(){
        this.muted = !this.muted;
        System.out.println("Mute " + (this.muted ? "ON" : "OFF"));
    }

    /**
     *Gibt zurück ob der Lautsprecher stumm ist
     * @return muted Summ oder nicht
     */
    public boolean isMuted(){
        return this.muted;
    }

    /**
     *Gibt die Spezifikationen eins Lautsprechers aus
     * @return Betriebszustand, Name, Raum, Lautstärke und Songname
     */
    @Override
    public String toString(){
        String songName = (currentSong == null) ? "No song playing" : currentSong;
        return super.toString() +
                "\nVolume: " + volume +
                "\nCurrently Playing: " + songName;
    }
}
