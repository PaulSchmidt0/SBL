//Paul Schmidt, Matrikelnummer: 2657411
package de.smarthome.devices;

/**
 * Interface für zeitbasierte Steuerung von Geräten im Smart-Home-System.
 * Klassen, die dieses Interface implementieren, können zeitlich geplant
 * ein- und ausgeschaltet werden.
 */
public interface Schedulable {

    /**
     *Legt einen neuen Plan fest
     * @param startTime Startzeit des Plans
     * @param endTime Endzeit des Plans
     */
    void schedule(String startTime, String endTime);

    /**
     *Löscht einen Plan
     */
    void cancelSchedule();
}
