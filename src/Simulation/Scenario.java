package Simulation;

import Data.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.*;
import java.text.*;

public class Scenario {
	
	/** declaration of attributes */
    private boolean regulation;

    private float collaborationRate;

    private float growthParameter;
    
    private ArrayList<Trip> tripList = new ArrayList <Trip> ();

    private ArrayList<Station> stationList = new ArrayList <Station> ();
    
    private ArrayList<Trip> waitingTrips = new ArrayList <Trip> ();

    private Scenario scenarioResume;

    private Result result;
    
    
    /** Constructors */
    
   /** Base Scenario Constructor */
    public Scenario (ArrayList <Station> stationList, ArrayList <Trip> tripList) {
	   
	   this.tripList = tripList;
	   this.stationList = stationList;
	   
	   return;
   }
    
    /** the main constructor */
    public Scenario (boolean regulation, float collaborationRate, float growthParameter) {
    	
    	this.regulation = regulation;
    	this.collaborationRate = collaborationRate;
    	this.growthParameter = growthParameter;
    	
    	return;
    }
    
    /** regulation constructor */
/* public Scenario (boolean regulation) {
    	
	this.Scenario(regulation,(float) 1,(float) 1);
	
    	return;
    }
    
/** collaborationRate constructor 
public Scenario (float collaborationRate) {
	
	this.Scenario( false, collaborationRate, (float) 1);
	
    	return;
    }
    
/** growthParameter constructor 
public Scenario (float growthParameter) {
	
	this.Scenario( false,(float) 1, growthParameter);
	
    	return;
    } */

    /** getters */
    
	public ArrayList <Trip> getTripList () {
		return tripList;
	}
	
	public ArrayList <Station> getStationList () {
		return stationList;
	}

    public boolean getRegulation () {
    	return regulation;
    }
    
    public float getCollaborationRate () {
    	return collaborationRate;
    }
    
    public float getGrowthParameter () {
    	return growthParameter;
    }
    
    /** setters */
    
    /* public void setTripList () {
    	return;
    }
    
    public void setStationList () {
    	return;
    }

    public void setTripList () {
	
    }
    */
    
    /** supplementary methods for runTrips()*/
  /*  public void startTrip(Trip trip) { //Operation
    	
    	if (!trip.getStartStation().isOpen()) {
			trip.cancelTrip();
		}
		
		else if (trip.getStartStation().getLatestState().isEmpty())	{
			trip.cancelTrip();
		}
		
		else {
			trip.getStartStation().takeBike(trip);
			waitingTrips.add(trip);
			trip.validateTrip();
		}
    	
    	return;
    }
    
    public Station findNextUsableStation(Station referenceStation) {
    	
    	Station usableStation;
    	for (int i=0; i<referenceStation.getClosestStations().size(); i++) {
    		
    	if (!referenceStation.getClosestStations().get(i).isFull()) {
    		usableStation = referenceStation.getClosestStations().get(i);
    		return usableStation;
    	}
    	
    	}
    	return; // catch all closest stations are full
    }
    
    public void endTrip (Trip trip){ //Operation
    	
    	if (!trip.getEndStation().isOpen() || trip.getEndStation().getLatestState().isFull()) {//is full => find close station
    		
    		// find closest station that is not already full
    		trip.setEndStation(findNextUsableStation(trip.getEndStation()));
    	}
    	
    	trip.getEndStation().returnBike(trip);
    	
    	return;
    }
    
    public Trip findNextTrip(Trip selectedTrip) { //Operation
    	
    	//implementation with isValid??
    	Trip nextTrip = selectedTrip;
    	
    	if	(nextTrip.getStartDate().after(waitingTrips.get(0).getEndDate()))  {
    		nextTrip = waitingTrips.get(0);
    	}
    	
    	return nextTrip;
    }
    */
    
    /** function to execute trips, essential simulation tool */
   
   /* public void runTrips() {
    
    	int i = 0;
    	while (i < tripList.size()){
    	Trip selectedTrip = tripList.get(i);
    	Trip currentTrip = findNextTrip(selectedTrip); //Schleifentyp
    	if (selectedTrip.equals(currentTrip)) {
    		startTrip(currentTrip);
    	}
    	
    	else {
    		endTrip(currentTrip);
    	} 
    	}
    	/* for(int i=0; i < tripList.size(); i++) {
    	Trip selectedTrip = tripList.get(i);
    	Trip currentTrip = findNextTrip(selectedTrip); //Schleifentyp
    	if (selectedTrip.equals(currentTrip)) {
    		startTrip(currentTrip);
    	}
    	
    	else {
    		endTrip(currentTrip);
    	}  	
    	
    	} */
    	/*	return;
    	} 

    /** simple Version of runTrips to run tests */
    public void runTripsTest () {
    	
    	for(int i=0; i < tripList.size(); i++) {
    		
    		Trip currentTrip = tripList.get(i);
    		String dummyNumberBikes =  Integer.toString(currentTrip.getStartStation().getLatestState().getNBikes());
    		System.out.println(dummyNumberBikes + "before: free bikes");
    		String dummyNumberStands =  Integer.toString(currentTrip.getStartStation().getLatestState().getNStands());
    		System.out.println(dummyNumberStands + "before: free places");
    		
    		if(currentTrip.getStartStation().isOpen() && currentTrip.getEndStation().isOpen() && !currentTrip.getStartStation().getLatestState().isEmpty() && !currentTrip.getEndStation().getLatestState().isFull ())	{
    			currentTrip.getStartStation().takeBike(currentTrip);
    			currentTrip.getEndStation().returnBike(currentTrip);
    		}
    		
    		dummyNumberBikes =  Integer.toString(currentTrip.getStartStation().getLatestState().getNBikes());
    		System.out.println(dummyNumberBikes + "after: free bikes");
    		dummyNumberStands =  Integer.toString(currentTrip.getStartStation().getLatestState().getNStands());
    		System.out.println(dummyNumberStands + "after: free places");
    		System.out.println();
    	}
    	return;
    	}
    
    /** method to simulate a Velib Scenario without regulation */
   /* public void noRegulation() {
    
    	ScenarioResume.setSimulationType("Simulation without regulation");
    	ScenarioResume.setRegulation(false);
    	
    	// supression of regulation trips
    	for (i=0; i < tripList.size(); i++) {
    		
    		if (tripList.get(i).getReason() == Reason.REGULATION) {
    			tripList.remove(i);
    		}    		
    	}
    	
    	//trip execution without regulation
    	this.runTrips();
    	
    	Result.setResultStations(stationList); // deep copy!!
    	Result.setResultTrips(tripList);
    	
    }
    */
/*
    public void newBehaviour() {
    }

    public void growPopularity() {
    	
   float helpvar=1/(growthParameter-1);
    			
for (int i=tripList.size(); i > 0; i=((float) i)-helpvar ) { //schleifenkopf überprüfen
    	
	//clone implementation
    	tripList.get((int) i).clone();
    	
    }

}*/
    public static void main (String args[]) {
    	
    	Scenario baseScenario;
    	ArrayList <Trip> testTrips = new ArrayList <Trip> ();
    	ArrayList <Station> testStations = new ArrayList <Station> ();
    	
    	//creation of test stations + states
    	Station station1 = new Station(901,20,"00901 - ALLEE DU BELVEDERE","ALLEE DU BELVEDERE PARIS 19 - 0 75000 Paris - 75000 PARIS",2.391225227186182,48.892795924112306);
    	State state1 = new State(6,"20131030125959",station1.getCapacity());
    	station1.setIsOpen(true);
    	station1.setPrimaryState(state1);
    	testStations.add(station1);
    	    
    	Station station2 = new Station( 903, 20, "00903 - QUAI MAURIAC  / PONT DE BERCY", "FETE DE L\u0027OH (BERCY) - QUAI MAURIAC ANG PONT DE BERCY", 2.374340554605615, 48.83713368945151);
    	State state2 = new State(18,"1383173780727",station2.getCapacity());
    	station2.setIsOpen(true);
    	station2.setPrimaryState(state2);
    	testStations.add(station2);
    	
    	
    	SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmss");
    	try {
    		Date date1 = ft.parse ("20131030125960");
    		Date date2 = ft.parse ("20131030127959");		
        	Date date3 = ft.parse ("20131030125961");	
        	Date date4 = ft.parse ("20131030126959");	
      		Date date5 = new Date ("20131030125965");
      		Date date6 = new Date ("20131030126970");
    	}
    	catch (ParseException e) {
    		System.out.println("Unparseable using" + ft);
    	}
    	Date date2 = new Date ("20131030127959");		
    	Date date3 = new Date ("20131030125961");	
    	Date date4 = new Date ("20131030126959");	
  		Date date5 = new Date ("20131030125965");
  		Date date6 = new Date ("20131030126970");
    			
    	Trip trip1 = new Trip(Reason.RENT, date1, station1, date2, station2); // 2000s
    	
    	Trip trip2 = new Trip (Reason.RENT, date3, station1, date4, station2); // later shorter 1000s
    	
    	Trip trip3 = new Trip (Reason.RENT, date5, station2, date6, station1); //inverse direction
    
    	baseScenario = new Scenario (testStations, testTrips);
    	
    	
    	baseScenario.runTripsTest();
    	for (int i = 0; i < baseScenario.stationList.size(); i++) {
    		System.out.println(baseScenario.stationList.get(i));
    	}
    	
    }

}
