package Simulation;

import Data.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Scenario {
	
	/* declaration of attributes */
    private boolean regulation;

    private float collaborationRate;

    private float growthParameter;
    
    private ArrayList<Trip> tripList = new ArrayList <Trip> ();

    private ArrayList<Station> stationList = new ArrayList <Station> ();
    
    private ArrayList<Trip> waitingTrips = new ArrayList <Trip> ();

    private Scenario scenarioResume;

    private Result result;
    
    
    /* Constructors */
    
   /** Base Scenario Constructor */
    public Scenario (ArrayList <Station> stationList, ArrayList <Trip> tripList) {
	   
	   this.tripList = tripList;
	   this.stationList = stationList;
	   
	   return;
   }
    
    /*
    public Scenario (boolean regulation, float collaborationRate, float growthParameter) {
    	
    	this.regulation = regulation;
    	this.collaborationRate = collaborationRate;
    	this.growthParameter = growthParameter;
    	
    	return;
    } */
    
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

    /* getters */
    
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
    
    /** function to execute trips, essential simulation tool */
    public void runTrips() {
    
    	//	while loop runs through the tripList 
    	int i = 0;
    	while (i < tripList.size())	{
    		
    		// pointer i on the selectedTrip in the tripList
    		Trip selectedTrip = tripList.get(i);
    		
    		// determines the next Trip to execute
    		Trip currentTrip = findNextTrip(selectedTrip);
    	
    		/* if findNextTrip did not change currentTrip
    		=> start Trip i and proceed to the next trip (i++) */
    		if (selectedTrip == currentTrip) {
    			
    			startTrip(currentTrip);
    			i++;
    		}
    	
    		/* if the findNextTrip changes currentTrip
    		=> end currentTrip */
    		else {
    			
    			endTrip(currentTrip);
    		} 
    	} //loop end
    	
    	// terminate trips in the waiting list
    	while (waitingTrips.size() > 0) {
    		
    		Trip tempTrip = waitingTrips.get(0);
    		for (int p = 0; p < waitingTrips.size(); p++) { //can be potentially erased by improving metho findNextStation
        		
        		if (tempTrip.getEndDate().after(waitingTrips.get(p).getEndDate())) {
        			tempTrip = waitingTrips.get(p);
        		}	
    		}
    		
    		endTrip(tempTrip);
    	}
    	
    		return;
    } 

    /** simple Version of runTrips to run tests */
    public void runTripsTest () {
    	
    	// loop runnig through the tripList
    	for(int i=0; i < tripList.size(); i++) {
    		
    		// pointer i
    		Trip currentTrip = tripList.get(i);
    		
    		// printBlock for testing (state comparison before and after bike use)
    		System.out.println("before:");
    		System.out.println("Start station:");
    		String dummyNumberBikes =  Integer.toString(currentTrip.getStartStation().getLatestState().getNBikes());
    		System.out.println(dummyNumberBikes + "free bikes");
    		String dummyNumberStands =  Integer.toString(currentTrip.getStartStation().getLatestState().getNStands());
    		System.out.println(dummyNumberStands + "free stands");
    		System.out.println("End station:");
    		dummyNumberBikes =  Integer.toString(currentTrip.getEndStation().getLatestState().getNBikes());
    		System.out.println(dummyNumberBikes + "free bikes");
    		dummyNumberStands =  Integer.toString(currentTrip.getEndStation().getLatestState().getNStands());
    		System.out.println(dummyNumberStands + "free stands");
    		
    		// bike use under certain conditions
    		// 1. startStation is open
    		// 2. endStation is open
    		// 3. StartStation is not empty
    		// 4. endStation is not full
    		if	(currentTrip.getStartStation().isOpen() && currentTrip.getEndStation().isOpen() && !currentTrip.getStartStation().getLatestState().isEmpty() && !currentTrip.getEndStation().getLatestState().isFull ())	{
    			currentTrip.getStartStation().takeBike(currentTrip);
    			currentTrip.getEndStation().returnBike(currentTrip);
    			currentTrip.validateTrip();
    		}
    		
    		// trip cancellation
    		else {
    			currentTrip.cancelTrip();
    		}
    		
    		// printBlock for testing (state comparison before and after bike use)
    		System.out.println("after:");
    		System.out.println("Start station:");
    		dummyNumberBikes =  Integer.toString(currentTrip.getStartStation().getLatestState().getNBikes());
    		System.out.println(dummyNumberBikes + "free bikes");
    		dummyNumberStands =  Integer.toString(currentTrip.getStartStation().getLatestState().getNStands());
    		System.out.println(dummyNumberStands + "free places");
    		System.out.println("End station:");
    		dummyNumberBikes =  Integer.toString(currentTrip.getEndStation().getLatestState().getNBikes());
    		System.out.println(dummyNumberBikes + "free bikes");
    		dummyNumberStands =  Integer.toString(currentTrip.getEndStation().getLatestState().getNStands());
    		System.out.println(dummyNumberStands + "free stands");
    		System.out.println();
    	}
    	
    	return;
    	}
    
    /* supplementary methods for runTrips() */
    
    /** Starts a Trip */
    public void startTrip(Trip trip) {
    	
    	if (!trip.getStartStation().isOpen()) {
			trip.cancelTrip();
		}
		
		else if (trip.getStartStation().getLatestState().isEmpty())	{
			trip.cancelTrip();
		}
		
		else {
			trip.getStartStation().takeBike(trip);
			waitingTrips.add(trip);
			// waitingTrips.sort(endDate,) //sort List => endDates
			trip.validateTrip();
		}
    	
    	return;
    }
    
    /** Terminates a Trip */
    public void endTrip (Trip trip)	{
    	
    	if (!trip.getEndStation().isOpen() || trip.getEndStation().getLatestState().isFull()) {//is full => find close station
    		
    		// find closest station that is not already full
    		trip.setEndStation(findNextUsableStation(trip.getEndStation()));
    	}
    	
    	trip.getEndStation().returnBike(trip);
    	waitingTrips.remove(trip);
    	
    	return;
    }

    /** Finds the next Trip for execution */
    public Trip findNextTrip(Trip selectedTrip) {
    	
    	// findNextTrip default return is the given Trip selectedTrip
    	Trip nextTrip = selectedTrip;
    	
    	// check for Trips in the waiting list
    	if	(!waitingTrips.isEmpty())	{
    		
    		// find the Trip in the waiting list which finishes next
    		Trip nextEndTrip = waitingTrips.get(0);
    		
    		for (int i = 0; i < waitingTrips.size(); i++) {
    		
    			if (nextEndTrip.getEndDate().after(waitingTrips.get(i).getEndDate())) {
    				nextEndTrip = waitingTrips.get(i);
    			}
    		}
    	
    		/* check if the default Trip starts before the next ending Trip
    		
    		if trips from the waiting list are passed to the method, the check is skipped since trips in the waiting list are always valid
    		trips that have not passed startTrip will always be checked, because the default return of isValid is false
    		
    		it should be avoided to pass trips that have gone through startTrip, and have been cancelled
    		(then the method findNextTrip needs to be adjusted) */
    		if	(!selectedTrip.isValid() && nextTrip.getStartDate().after(nextEndTrip.getEndDate()))  {
    			nextTrip = nextEndTrip;
    		}
    	}
    	
    	return nextTrip;
    }
    
    /** Finds another close station based on the referenceStation*/
    public Station findNextUsableStation(Station referenceStation) {
    	
    	Station usableStation = referenceStation;
    	
    	// search all the closest stations for the reference station
    	// the list should ideally be sorted by distance to the reference station
    	for (int i=0; i<referenceStation.getClosestStations().size(); i++) {
    		
    		// when you find a non full station => select it and break the loop
    		if (!referenceStation.getClosestStations().get(i).getLatestState().isFull()) {
    			usableStation = referenceStation.getClosestStations().get(i);
    			break;
    		}
    	}
    	
    	// catch all closest stations are full => find randomStation
    	/* int j = 0;
    	if (usableStation == referenceStation) {
    		if (j>5) {
    			// break after 5 iterations
    		}
    		
    		
    		int tempMax = stationList.size()-1;
    		int randomIndex = (int) (tempMax * Math.random());
    		Station randomStation = stationList.get(randomIndex);
    		findNextUsableStation(randomStation);	 //recursive!!
    	}
    	*/
    	return usableStation;
    }
   
    
    /* different Simulation Methods */
    
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
    
    // Main method for testing
    public static void main (String args[]) {
    	
    	// declaration of attributes
    	Scenario baseScenario;
    	ArrayList <Trip> testTrips = new ArrayList <Trip> ();
    	ArrayList <Station> testStations = new ArrayList <Station> ();
    	
    	//creation of test stations + states
    	Station station1 = new Station(901,"00901 - ALLEE DU BELVEDERE","ALLEE DU BELVEDERE PARIS 19 - 0 75000 Paris - 75000 PARIS", 20,2.391225227186182,48.892795924112306);
    	State state1 = new State(6,"20131030125959",station1.getCapacity());
    	station1.setIsOpen(true);
    	station1.setPrimaryState(state1);
    	testStations.add(station1);
    	    
    	Station station2 = new Station( 903, "00903 - QUAI MAURIAC  / PONT DE BERCY", "FETE DE L\u0027OH (BERCY) - QUAI MAURIAC ANG PONT DE BERCY", 20, 2.374340554605615, 48.83713368945151);
    	State state2 = new State(18,"1383173780727",station2.getCapacity());
    	station2.setIsOpen(true);
    	station2.setPrimaryState(state2);
    	testStations.add(station2);
    
    	// initializing test dates
    	Date date1 = new GregorianCalendar(2013, 10, 30,13,0,0).getTime();
    	Date date2 = new GregorianCalendar(2013, 10, 30,13,20,0).getTime();		
    	Date date3 = new GregorianCalendar(2013, 10, 30,13,0,1).getTime();	
    	Date date4 = new GregorianCalendar(2013, 10, 30,13,10,0).getTime();	
  		Date date5 = new GregorianCalendar(2013, 10, 30,13,0,5).getTime();
  		Date date6 = new GregorianCalendar(2013, 10, 30,13,10,10).getTime();
    	
  		// initializing test trips
    	Trip trip1 = new Trip(Reason.RENT, date1, station1, date2, station2); // 2000s
    	testTrips.add(trip1);
    	Trip trip2 = new Trip (Reason.RENT, date3, station1, date4, station2); // later shorter 1000s  
    	testTrips.add(trip2);
    	Trip trip3 = new Trip (Reason.RENT, date5, station2, date6, station1); //inverse direction
    	testTrips.add(trip3);
    	
    	// call of the functions to be tested
    	baseScenario = new Scenario (testStations, testTrips);
    	//baseScenario.runTripsTest();
    	baseScenario.runTrips();
    	
    	// print of results
    	// states
    	for (int i = 0; i < baseScenario.stationList.size(); i++) {
    		System.out.println(baseScenario.stationList.get(i));
    	}
    	
    }

}
