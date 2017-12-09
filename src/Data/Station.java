package Data;

import java.util.ArrayList;
import java.util.Date;
//import com.modeliosoft.modelio.javadesigner.annotations.objid;

public class Station {
    /* Attributs statiques */
    private int identity;
    private int capacity;
    private double longitude;
    private double latitude;
    private String name;
    private String address;
    /* Attributs dynamiques */
    private ArrayList <State> stateList;
    private boolean isOpen;
    /* Info supplémentaires */
    private ArrayList <Station> closestStations;
    
    
    /* Constructeurs, informations essentiels */
    /* Veuillez utiliser le setPrimaryState et setIsOpen après la création d'une station impérativement */
    public Station(int identity, int capacity) {
      this.identity = identity;
      this.capacity = capacity; 
    }
    
    /* Constructeurs, plus des informations supplémentraires */
    /* Veuillez utiliser le setPrimaryState et setIsOpen après la création d'une station impérativement */
    public Station(int identity, int capacity, String name, String address, int longitude, int latitude) {
      this(identity, capacity);
      this.name = name;
      this.address = address;
      this.longitude = longitude;
      this.latitude = latitude;
    }
    
    /* Operations des trips (trajets), on prends qu'un vélo chaque fois */
    public void takeBike(Date date) {
      int numberOfFreeBikesNew = stateList.get(stateList.size()).getNBikes() - 1;
      stateList.add(new State(numberOfFreeBikesNew,date,capacity));
    }

    public void takeBike(Trip trip) {
      int numberOfFreeBikesNew = stateList.get(stateList.size()).getNBikes() - 1;
      Date date = trip.getStartDate();
      stateList.add(new State(numberOfFreeBikesNew,date,capacity));
    }
    
    public void returnBike(Date date) {
      int numberOfFreeBikesNew = stateList.get(stateList.size()).getNBikes() + 1;
      stateList.add(new State(numberOfFreeBikesNew, date,capacity));
    }

    public void returnBike(Trip trip) {
      int numberOfFreeBikesNew = stateList.get(stateList.size()).getNBikes() + 1;
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
    public State getState(int n) {
      return stateList.get(n);
    }
    public ArrayList<State> getStateList(){
      return stateList;
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
    public double getLongitude() {
      return longitude;
    }
    public double getLatitude() {
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
    public void setPrimaryState(State primaryState) {
      this.stateList.add(primaryState);
    }
    //Open-1, Closed-0
    public void setIsOpen(boolean isOpen) {
      this.isOpen = isOpen;
    }

    
    public String toString() {
      String info = "id: "+getIdentity()+"\tnm: "+getName()+"\tad: "+getAddress()+"\tcpct: "+getCapacity()+"\tlog: "+getLongitude()+"\tlat: "+getLatitude();
      info = info + "\n Latest state:" + getLatestState().toString()+isOpen();
      return name;
      
    }
    
    
    
    
    public static void main (String [] args) {
      
    }

}
