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
    private ArrayList <Station> closestStationsList;
    // RADIUS_OF_EARTH Ressource: https://rechneronline.de/earth-radius/ 
    // using 48.83713368945151 latitude, 50 meters above sea level, 
    private static double RADIUS_OF_EARTH = 6366111; 
    
 
    /**
     * Constructeur qui prends des informations essentiels 
     * Veuillez utiliser le setPrimaryState et setIsOpen après la création d'une station impérativement
     * @param identity
     * @param capacity
     */
    public Station(int identity, int capacity) {
      this.identity = identity;
      this.capacity = capacity;
      stateList = new ArrayList<State> ();
      this.isOpen = true;
    }
    

    /**
     * Constructeur qui prend à la fois des informations essentiels et supplémentraires 
     * Veuillez utiliser impérativement le setPrimaryState() et setIsOpen() après la création d'une station
     * @param identity
     * @param capacity
     * @param name
     * @param address
     * @param longitude
     * @param latitude
     */
    public Station(int identity, String name, String address, int capacity, double longitude, double latitude) {
      this.identity = identity;
      this.capacity = capacity;
      this.name = name;
      this.address = address;
      this.longitude = longitude;
      this.latitude = latitude;
      stateList = new ArrayList<State> ();
      this.isOpen = true;
    }
    
    /* Opération des trips */
    /**
     * On prends qu'un vélo chaque fois
     * Cette méthode prend une instance de Date comme entrée 
     * @param date
     */
    public void takeBike(Date date) {
      int numberOfFreeBikesNew = stateList.get(stateList.size()-1).getNBikes() - 1;
      stateList.add(new State(numberOfFreeBikesNew,date,capacity));
    }

    /**
     * On prends qu'un vélo chaque fois 
     * Cette méthode prend une indtance de Trip comme entrée
     * @param trip
     */
    public void takeBike(Trip trip) {
      int numberOfFreeBikesNew = stateList.get(stateList.size()-1).getNBikes() - 1;
      Date date = trip.getStartDate();
      stateList.add(new State(numberOfFreeBikesNew,date,capacity));
    }
    
    /**
     * On retourne qu'un vélo chaque fois 
     * Cette méthode prend une instance de Date comme entrée
     * @param date
     */
    public void returnBike(Date date) {
      int numberOfFreeBikesNew = stateList.get(stateList.size()-1).getNBikes() + 1;
      stateList.add(new State(numberOfFreeBikesNew, date,capacity));
    }

    /**
     * On retourne qu'un vélo chaque fois 
     * Cette méthode prend une indtance de Trip comme entrée
     * @param trip
     */
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
    /**
     * La méthode pour set toutes les stations dans un rayon de certaines mètre
     * @param radius en mètre
     * @param stationList
     */
    public void setClosestStations(int radius, ArrayList<Station> stationList) {
      for (int i = 0; i < stationList.size(); i++) {
        Station st = stationList.get(i);
        if (Station.getDistanceBetweenTwoStations(this, st) <= radius) {
          closestStationsList.add(st);
        }
      }
    }
    
    /**
     * Sources des formules: https://andrew.hedges.name/experiments/haversine/
     * @param st1
     * @param st2
     * @return la distance entre les deux stations en question en mètre
     */
    public static int getDistanceBetweenTwoStations(Station st1, Station st2) {
      double lon1 = Math.toRadians(st1.getLongitude());
      double lon2 = Math.toRadians(st2.getLongitude());
      double lat1 = Math.toRadians(st1.getLatitude());
      double lat2 = Math.toRadians(st2.getLatitude());
      
      double dlon = lon2 - lon1;
      double dlat = lat2 - lat1;
      double a = Math.pow(Math.sin(dlat/2), 2) 
          + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon/2), 2);
      double c = 2 * Math.atan2( Math.sqrt(a), Math.sqrt(1-a));
      int d = (int) Math.round(RADIUS_OF_EARTH * c);
      return d;
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
      return closestStationsList;
    }
    
    
    /* Setters */
    /**
     * Setter pour l'état primaire
     * @param primaryState
     */
    public void setPrimaryState(State primaryState) {
      this.stateList.add(primaryState);
    }

    /**
     * Setter pour isOpen
     * Open-true, Closed-false
     * @param isOpen
     */
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
      // Création de la 1e station
      Station station1 = new Station(901,"00901 - ALLEE DU BELVEDERE","ALLEE DU BELVEDERE PARIS 19 - 0 75000 Paris - 75000 PARIS",
          20,2.391225227186182,48.892795924112306);
      State state1 = new State(6,"20131030125959",station1.getCapacity());
      station1.setIsOpen(true);
      station1.setPrimaryState(state1);
//      station1.getStateList().add(state1);
      System.out.println("Création de la 1e station");
      System.out.println(station1);
      
      // Test de takeBike() et returnBike()
      Date date1 = new Date();
      station1.takeBike(date1);
      System.out.println();
      System.out.println("After taking a bike:");
      System.out.println(station1);
      try        
      {
          Thread.sleep(1000); //après 1 seconde
      } 
      catch(InterruptedException ex) 
      {
          Thread.currentThread().interrupt();
      }
      
      Date date2 = new Date();
      System.out.println();
      station1.returnBike(date2);
      System.out.println("After returning a bike:");
      System.out.println(station1);
      
      //Creation de la 2e station
      Station station2 = new Station(903,"00903 - QUAI MAURIAC  / PONT DE BERCY","FETE DE L\u0027OH (BERCY) - QUAI MAURIAC ANG PONT DE BERCY",
          20,2.374340554605615,48.83713368945151);
      State state2 = new State(15,"20131030125959",station2.getCapacity());
      station2.setIsOpen(true);
      station2.setPrimaryState(state2);
      System.out.println();      
      System.out.println("Creation de la 2e station");
      System.out.println(station2);
      
      
      //Creation de la 3e station
      Station station3 = new Station(904,"00904 - PLACE JOFFRE / ECOLE MILITAIRE","ECOLE MILITAIRE-AVENUE DE LA MOTTE PICQUET - 75007 PARIS",
          30,2.301961227213259,48.85213620522547);
      State state3 = new State(24,"20131030125959",station3.getCapacity());
      station3.setIsOpen(true);
      station3.setPrimaryState(state3);
      System.out.println();      
      System.out.println("Creation de la 3e station");
      System.out.println(station3);
      
      
      //Creation de la 4e station
      Station station4 = new Station(905,"00905 - GARE DE BERCY (STATION MOBILE 5)","GARE DE BERCY - ANGLE RUE CORBINEAU - 75012 PARIS",
          20,2.382472269083633,48.83966087889425);
      State state4 = new State(19,"20131030125959",station4.getCapacity());
      station4.setIsOpen(true);
      station4.setPrimaryState(state4);
      System.out.println();
      System.out.println("Creation de la 4e station");
      System.out.println(station4);
      
      //Creation d'une ArrayList de stations
      ArrayList<Station> stationList = new ArrayList<Station> ();
      stationList.add(station1);
      stationList.add(station2);
      stationList.add(station3);
      stationList.add(station4);
      station1.setClosestStations(10000, stationList);
      
      System.out.println(Station.getDistanceBetweenTwoStations(station1, station2));
      
      
    }
}
