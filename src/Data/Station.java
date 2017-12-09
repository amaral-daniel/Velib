package Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
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
    /* Veuillez utiliser le setPrimaryState() et setIsOpen() après la création d'une station impérativement */
    public Station(int identity, int capacity, String name, String address, double longitude, double latitude) {
      this(identity, capacity);
      this.name = name;
      this.address = address;
      this.longitude = longitude;
      this.latitude = latitude;
      stateList = new ArrayList<State> ();
    }
    
    /* Operations des trips (trajets), on prends qu'un vélo chaque fois */
    public void takeBike(Date date) {
      int numberOfFreeBikesNew = stateList.get(stateList.size()-1).getNBikes() - 1;
      stateList.add(new State(numberOfFreeBikesNew,date,capacity));
    }

    public void takeBike(Trip trip) {
      int numberOfFreeBikesNew = stateList.get(stateList.size()-1).getNBikes() - 1;
      Date date = trip.getStartDate();
      stateList.add(new State(numberOfFreeBikesNew,date,capacity));
    }
    
    public void returnBike(Date date) {
      int numberOfFreeBikesNew = stateList.get(stateList.size()-1).getNBikes() + 1;
      stateList.add(new State(numberOfFreeBikesNew, date,capacity));
    }

    public void returnBike(Trip trip) {
      int numberOfFreeBikesNew = stateList.get(stateList.size()-1).getNBikes() + 1;
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
      return stateList.get(stateList.size()-1);
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
    //Open-true, Closed-false
    public void setIsOpen(boolean isOpen) {
      this.isOpen = isOpen;
    }

    public String toString() {
      String info = "id: "+getIdentity()+"\tnm: "+getName()+"\tad: "+getAddress();
      info = info + "\ncpct: "+getCapacity()+"\tlog: "+getLongitude()+"\tlat: "+getLatitude();
      info = info + "\nLatest state: " + getLatestState().toString();
      if (isOpen()) {
        info = info + "\tOpen";
        }
      else {
        info = info + "\tClosed";
      }
      return info;      
    }
    

    public static void main (String [] args) {
      Station station1 = new Station(901,20,"00901 - ALLEE DU BELVEDERE","ALLEE DU BELVEDERE PARIS 19 - 0 75000 Paris - 75000 PARIS",2.391225227186182,48.892795924112306);
      State state1 = new State(6,"20131030125959",station1.getCapacity());
      station1.setIsOpen(true);
      station1.getStateList().add(state1);
      System.out.println(station1);
      Date date1 = new Date();
      station1.takeBike(date1);
      System.out.println(station1);
      

      Date date2 = new Date();
      station1.returnBike(date2);
      System.out.println(station1);
      
      
     // Station station2 = new Station(903,20,"00903 - QUAI MAURIAC  / PONT DE BERCY","FETE DE L\u0027OH (BERCY) - QUAI MAURIAC ANG PONT DE BERCY",2.374340554605615,48.83713368945151);
      
    }
}
