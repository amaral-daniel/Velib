package Simulation;

import Data.Result;
import Data.Scenario Resume;
import Data.Station;
import Data.Trip;

import java.util.List;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("458f34b7-cc9d-41e9-a830-5211ea597923")
public class Scenario {
	
	/** declaration of attributes */
    @objid ("c1ad2d97-e7ec-4e8a-aa19-f60219c444ca")
    private boolean regulation;

    @objid ("384d4d84-0c8e-41cf-9a99-9c0fc2a9a0f4")
    private float collaborationRate;

    @objid ("a3d625b4-c2b0-4beb-8d60-17c3a8a97359")
    private float growthParameter;

    @objid ("5eebbdbf-6cfa-44d4-9645-5e9649cf1082")
    public List<Trip> tripList = new ArrayList<Trip> ();

    @objid ("82a9089a-131f-4e0c-b982-8bca7bd87f2b")
    public List<Station> stationList = new ArrayList<Station> ();

    @objid ("a7965491-a9ad-4376-b287-6c1711ebad0b")
    public Scenario scenarioResume;

    @objid ("ca2b6870-101c-402d-a43d-6e7ad9b37f6b")
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
    	
	this.Scenario(regulation, 1, 1)
	
    	return;
    }
    
/** collaborationRate constructor */
public Scenario (float collaborationRate) {
	
	this.Scenario( false, collaborationRate, 1)
	
    	return;
    }
    
/** growthParameter constructor */
public Scenario (float growthParameter) {
	
	this.Scenario( false, 1, growthParameter)
	
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

    
    /** function to execute trips, essential simulation tool */
    @objid ("f01db4d9-90db-4bda-94db-61f6485dda6d")
    public void runTrips() {
    	for(i=0; i < tripList.size(); i++) {
    		
    		tripList.get(i).getStartStation().takeBike();
    		tripList.get(i).getStartStation().returnBike();
    		
    	}
    }

    /** method to simulate a Velib Scenario without regulation */
    @objid ("74708208-9372-4c59-9ef0-e11fb9faeffb")
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

    @objid ("1bc0983b-93f7-4430-a171-4e92a3d9dcf9")
    public void newBehaviour() {
    }

    @objid ("ab774896-c3de-4638-b206-23ac13acb232")
    public void growPopularity() {
    }

}
