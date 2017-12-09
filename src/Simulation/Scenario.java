package Simulation;

import Data.Result;
import Data.ScenarioResume;
import Data.Station;
import Data.Trip;

import java.util.ArrayList;
import java.util.List;

public class Scenario {
	
	/** declaration of attributes */
    private boolean regulation;

    private float collaborationRate;

    private float growthParameter;
    
    private ArrayList<Trip> tripList = new ArrayList <Trip> ();

    private ArrayList<Station> stationList = new ArrayList <Station> ();
    
    private ArrayList<Trip> waitingTrips = new ArrayList <Trip> ();

    public Scenario scenarioResume;

    public Result result;
    
    /** the main constructor */
    public Scenario (boolean regulation, float collaborationRate, float growthParameter) {
    	
    	this.regulation = regulation;
    	this.collaborationRate = collaborationRate;
    	this.growthParameter = growthParameter;
    	
    	return;
    }
    
    /** regulation constructor */
public Scenario (boolean regulation) {
    	
	this.Scenario(regulation,(float) 1,(float) 1);
	
    	return;
    }
    
/** collaborationRate constructor */
public Scenario (float collaborationRate) {
	
	this.Scenario( false, collaborationRate, (float) 1);
	
    	return;
    }
    
/** growthParameter constructor */
public Scenario (float growthParameter) {
	
	this.Scenario( false,(float) 1, growthParameter);
	
    	return;
    }

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

    //public void tripFlowRegulation() { // Operation
    	
   // }
    
    public void startTrip(Trip trip) { //Operation
    	
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
    
    public void endTrip (Trip trip){ //Operation
    	
    	trip.getStartStation().returnBike(trip);
    	
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
    
    
    /** function to execute trips, essential simulation tool */
    public void runTrips() {
    	for(int i=0; i < tripList.size(); i++) {
    	Trip selectedTrip = tripList.get(i);
    	Trip currentTrip = findNextTrip(selectedTrip); //Schleifentyp
    	if (selectedTrip.equals(currentTrip)) {
    		startTrip(currentTrip);
    	}
    	else {
    		endTrip(currentTrip);
    	}  	
    	//is full => find close station
    	}
    		return;
    	}

    /** method to simulate a Velib Scenario without regulation */
    public void noRegulation() {
    	
    	// supression of regulation trips
    	for (i=0; i < tripList.size(); i++) {
    		
    		if (tripList.get(i).getReason() = enum regulation) {
    			tripList.remove(i);
    		}    		
    	}
    	
    	//trip execution without regulation
    	this.runTrips();
    	
    }

    public void newBehaviour() {
    }

    public void growPopularity() {
    	
   float helpvar=1/(growthParameter-1);
    			
for (int i=tripList.size(); i > 0; i=((float) i)-helpvar ) { //schleifenkopf �berpr�fen
    	
	//clone implementation
    	tripList.get((int) i).clone();
    	
    }

}
    public static void main (String args[]) {
    	
    }

}

