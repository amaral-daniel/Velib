package Simulation;

import Data.Result;
import Data.ScenarioResume;
import Data.Station;
import Data.Trip;

import java.util.ArrayList;
import java.util.List;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

public class Scenario {
	
	/** declaration of attributes */
    private boolean regulation;

    private float collaborationRate;

    private float growthParameter;
    
    public ArrayList<Trip> tripList = new ArrayList <Trip> ();

    public ArrayList<Station> stationList = new ArrayList <Station> ();
    
    public ArrayList<Trip> waitingTrips = new ArrayList <Trip> ();

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
		
		else if (trip.getStartStation().getLastState.isEmpty())	{
			trip.cancelTrip();
		}
		
		else {
			trip.getStartStation().takeBike();
			waitingTrips.add(trip);
		}
    	
    	return;
    }
    
    public void endTrip { //Operation
    	tripList.get(i).getStartStation().returnBike();
    }
    
    public Trip findNextTrip() { //Operation
    	//
    	//implementation with isValid??
    	Trip nextTrip = //;
    	if	(nextTrip.getDate.after(waitingTrips.get(0)))  {
    		nextTrip = waitingTrips.get(0)
    	}
    	
    	return nextTrip;
    }
    
    
    /** function to execute trips, essential simulation tool */
    public void runTrips() {
    	// for(int i=0; i < tripList.size(); i++) { // überarbeiten
    	Trip currenttrip;
    	currentTrip = findNextTrip();
    	startTrip(currentTrip);
    	findnext
    	endTrip(currenttrip);
    		
    			//is full => find close station
    		
    		
    	}
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
    			
for (int i=tripList.size(); i > 0; ((float) i)-helpvar ) { //schleifenkopf überprüfen
    	
	//clone implementation
    	tripList.get((int) i).clone();
    	
    }

}
    public static void main (String args[]) {
    	
    }

}

