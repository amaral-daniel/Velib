package Data;

import java.util.ArrayList;
import java.util.Date;
//import com.modeliosoft.modelio.javadesigner.annotations.objid;

public class Station {
    /*Attributs essentiels*/
    private ArrayList <State> states;
    private boolean isOpen;
    private int identity;
    private int capacity;
    
    /*Attributs supplémentaires, pour simuler les nouveaux comportements*/
    private int longitude;
    private int latitude;
    private String name;
    private String address;
    
    private ArrayList <Integer> idClosestStations;
    
    /*Constructeurs, informations essentiels*/
    public Station(int identity, int capacity, State primaryState) {
      this.identity = identity;
      this.capacity = capacity;
      this.states.add(primaryState);
    }
    
    /*Constructeurs, plus des informations supplémentraires*/
    public Station(int id, int capacity, State primaryState, String name, String ad, int longitude, int latitude) {
      this(id, capacity, primaryState);
      this.name = name;
      this.address = ad;
      this.longitude = longitude;
      this.latitude = latitude;
    }
    
    
    /*Operations des trips (trajets), on prends qu'un vélo chaque fois*/
    public void takeBike(Date date) {
      int numberOfFreeBikesNew = states.get(states.size()).getNumberOfFreeBikes() - 1;
      states.add(new State(numberOfFreeBikesNew,date));
    }

    public void takeBike(Trip trip) {
      int numberOfFreeBikesNew = states.get(states.size()).getNumberOfFreeBikes() - 1;
      Date date = trip.getStartDate();
      states.add(new State(numberOfFreeBikesNew,date));
    }
    

    public void returnBike(Date date) {
      int numberOfFreeBikesNew = states.get(states.size()).getNumberOfFreeBikes() + 1;
      states.add(new State(numberOfFreeBikesNew, date));
    }

    public void returnBike(Trip trip) {
      int numberOfFreeBikesNew = states.get(states.size()).getNumberOfFreeBikes() + 1;
      Date date = trip.getEndDate();
      states.add(new State(numberOfFreeBikesNew,date));
    }
    
    /* Operations des states*/
    public State getLatestState() {
      return states.get(states.size());
    }
    public void deletePreviousState() {
    }

    /* Operations des stations */
    public void setClosestStations(int n, ArrayList<Station> stations) {
      
    }
    
    
    /* Retournes des situations actuelles */
    public boolean isOpen() {
      return isOpen;
    }
   
    /* 
    public boolean isEmpty() {
      if (states.get(states.size())) {
          int n;
          n = states.get(states.size()).getNumberOfFreeBikes();
          if (n == 0) return true;
          else return false;
        }
      else
        return false; 
    }

    public void isFull() {
      
    }
    */

    public void Getters(String p1) {
      
    }
    
    //Je ne laise vide pour le moment
    public void Setters(final String p1) {
      
    }

    
    public String toString() {
      return name;
      
    }
    

}
