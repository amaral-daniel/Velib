package Data;

import java.util.ArrayList;
import java.util.Date;
//import com.modeliosoft.modelio.javadesigner.annotations.objid;

public class Station {
    /*Attributs essentiels*/
    private ArrayList <State> stateList;
    private boolean isOpen;
    private int identity;
    private int capacity;
    
    /*Attributs supplémentaires, pour simuler les nouveaux comportements*/
    private int longitude;
    private int latitude;
    private String name;
    private String address;
    
    private ArrayList <Station> closestStations;
    
    /* Constructeurs, informations essentiels */
    public Station(int identity, int capacity, State primaryState) {
      this.identity = identity;
      this.capacity = capacity;
      this.stateList.add(primaryState);
    }
    
    /* Constructeurs, plus des informations supplémentraires */
    public Station(int identity, int capacity, State primaryState, String name, String address, int longitude, int latitude) {
      this(identity, capacity, primaryState);
      this.name = name;
      this.address = address;
      this.longitude = longitude;
      this.latitude = latitude;
    }
    
    
    /* Operations des trips (trajets), on prends qu'un vélo chaque fois */
    public void takeBike(Date date) {
      int numberOfFreeBikesNew = stateList.get(stateList.size()).getNumberOfFreeBikes() - 1;
      stateList.add(new State(numberOfFreeBikesNew,date,capacity));
    }

    public void takeBike(Trip trip) {
      int numberOfFreeBikesNew = stateList.get(stateList.size()).getNumberOfFreeBikes() - 1;
      Date date = trip.getStartDate();
      stateList.add(new State(numberOfFreeBikesNew,date,capacity));
    }
    

    public void returnBike(Date date) {
      int numberOfFreeBikesNew = stateList.get(stateList.size()).getNumberOfFreeBikes() + 1;
      stateLists.add(new State(numberOfFreeBikesNew, date,capacity));
    }

    public void returnBike(Trip trip) {
      int numberOfFreeBikesNew = stateList.get(stateList.size()).getNumberOfFreeBikes() + 1;
      Date date = trip.getEndDate();
      stateList.add(new State(numberOfFreeBikesNew,date,capacity));
    }
    
    /* Operations des states*/
    public void deleteLatestState() {
      stateList.remove(stateList.size());
    }

    /* Operations des stations */
    public void setClosestStations(int n, ArrayList<Station> stationList) {
      
    }
    
    
    /* Getters */
    public State getStateList(int n) {
      return stateList.get(n);
    }
    public int getNumberOfStates() {
      return stateList.size();
    }
    public State getLatestState() {
      return stateList.get(stateList.size());
    }
    
    public boolean isOpen() {
      return isOpen;
    }
    public int getIdentity() {
      return identity;
    }
    public int getCapacity() {
      return capacity;
    }
    public int getLongitude() {
      return longitude;
    }
    public int getLatitude() {
      return latitude;
    }
    public String getName() {
      return name;
    }
    public String getAddress() {
      return address;
    }
    public ArrayList<Station> getClosestStations() {
      return closestStations;
    }
    
    
    /* Setters */
    public void Setters(final String p1) {
      
    }

    
    public String toString() {
      return name;
      
    }
    

}
