# SmartHome SBL Projekt (Java)

Dieses Projekt ist eine SmartHome-Simulation, die im Rahmen der SBL-Aufgabe entwickelt wurde.  
Es beinhaltet Benutzerverwaltung, Geräteverwaltung, Routinen, und Statistikberechnung.

---

## 📌 Funktionen

### 🔐 Benutzerverwaltung (`de.smarthome.account`)
- Registrierung von Nutzern
- Login / Logout
- Parent- und Child-Accounts
- Passwort-Reset für Childs
- Upgrade von Child → Parent

### 💡 Geräteverwaltung (`de.smarthome.devices`)
Unterstützte Geräte:
- Light (Helligkeit, Farbe)
- Shutter (Position 0–10)
- Speaker (Lautstärke, Mute, Song)

Alle Geräte:
- haben Name, ID, Leistungsaufnahme
- können ein- und ausgeschaltet werden
- unterstützen Scheduling (Zeitpläne)

### 🏠 Raumverwaltung (`de.smarthome.home.rooms`)
- Räume mit Breite, Länge, Höhe
- Berechnung von Fläche & Volumen
- Geräte zu Räumen hinzufügen / entfernen

### 🔁 Routinen (`de.smarthome.home.routines`)
- Routinen erstellen, speichern, laden
- RoutineManager verwaltet Routinen
- Routinen wenden Befehle auf Geräte an

### 📊 Statistiken (`de.smarthome.statistics`)
- Berechnung von Energieverbrauch
- Berechnung von Kosten pro Raum und Zeitraum

---

## 📂 Projektstruktur

