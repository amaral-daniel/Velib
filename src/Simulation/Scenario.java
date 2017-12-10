package Simulation;

import Data.Result;
import Data.ScenarioResume;
import Data.Station;
import Data.Trip;

import java.util.ArrayList;

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
    		
    		String dummyNumberBikes =  Station.getLatestState().getNBikes().toString();
    		System.out.println(dummyNumberBikes + "before: free bikes");
    		String dummyNumberStands =  Station.getLatestState().getNStands();
    		System.out.println(dummyNumberStands + "before: free places");
    		
    		Trip currentTrip = tripList.get(i);
    		if(currentTrip.getStartStation().isOpen() && currentTrip.getEndStation().isOpen() && !currentTrip.getStartStation().getLatestState().isEmpty() && !currentTrip.getEndStation().getLatestState().isFull ())	{
    			currentTrip.getStartStation().takeBike(currentTrip);
    			currentTrip.getEndStation().returnBike(currentTrip);
    		}
    		
    		dummyNumberBikes =  Station.getLatestState().getNBikes();
    		System.out.println(dummyNumberBikes + "after: free bikes");
    		dummyNumberStands =  Station.getLatestState().getNStands();
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
    	ArrayList <Trip> testTrips;
    	ArrayList <Station> testStations;
    	
    	
    	for (int i = 0; i< 10; i++) {
    		
    	}
    	baseScenario = new Scenario (testTrips, testStations);
    }

}
