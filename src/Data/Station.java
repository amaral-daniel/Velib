package Data;

import java.util.ArrayList;
import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("d42b5685-924b-4b40-9dc5-8ed169ffbbdd")
public class Station {
    @objid ("68ee1461-3bbe-4155-b417-f902d8605348")
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
    
    
 
    
    /*Operations des trips (trajets) */
    @objid ("208aa6a5-e5e3-4d4f-9161-32193d04546a")
    public void takeBike(Date date,int numberOfBikes) {
      
    }

    public void takeBike(Trip trip) {
      
    }
    
    @objid ("48456b51-019a-4d69-9b11-208f4282e47e")
    public void returnBike(Date date,int numberOfBikes) {
      
    }

    public void returnBike(Trip trip) {
      
    }
    
    /*Operations des states*/
    @objid ("fe05dec9-dc31-4647-a9be-e89fbb4f6bd5")
    public void deletePreviousState() {
      
    }

    /*Operations des */
    
    @objid ("6f005cd5-c18f-4ed0-be61-9025735acc52")
    public void setClosestStations(int n, ArrayList<Station> stations) {
      
    }
    
    
    
    /*Retournes des situations actuelles*/
    public boolean isOpen() {
      return isOpen;
    }
    
    @objid ("6d655b90-b3ee-42c1-9b40-8fa3b6a9e33f")
    public boolean isEmpty() {
      if (states.get(states.size()).isOpen()) {
          int n;
          n = states.get(states.size()).getNumberOfFreeBikes();
          if (n == 0) return true;
          else return false;
        }
      else
        return false; 
    }

    @objid ("5c5ec993-f63c-438b-adb8-2cce230790cf")
    public void isFull() {
      
    }

    @objid ("521a4dbd-fcb9-46ff-9c1c-380f4866f216")
    public void Getters(String p1) {
      
    }
    
    //Je ne laise vide pour le moment
    @objid ("9facc074-c02c-4524-9695-eff0d4a1fa07")
    public void Setters(final String p1) {
      
    }


    
    
    
    public String toString() {
      return name;
      
    }
    

}
