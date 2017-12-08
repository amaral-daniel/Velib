package Evaluation;

// import com.modeliosoft.modelio.javadesigner.annotations.objid;

// @objid ("4405897e-9ae0-4032-a51f-ca9500a53a88")

import IHM.*;
import Data.*;
import Simulation.*;
import java.util.ArrayList;

public class Regulation {
//    @objid ("54816119-b5c9-409b-9c78-093d919120ee")
    
	/** The Method evaluates for a given Scenario the critical States of all Stations and writes it to file **/
    public void identifyCriticalStations(Scenario refScenario) {
    	
    	ArrayList<Station> stationList = refScenario.getStations();
    	// loop running through all Stations of the given Scenario
    	for (int i; i < stationList.size();i++) {
    		
    		Station currentStation = stationList.get(i);
    		ArrayList<State> stationStates = currentStation.getStates();
    		if
    		
    		//loop running through all States j of the selected Station i
    		for	(int j; j < stationStates.size(); j++) {
    			
    			State currentState = stationStates.get(j);
    					
    			//check if the station i is empty in State j
    			if (currentState.isEmpty() || currentState.isFull()) {
    				
    				/* write and/or fill a List e.g. write.toFile()
    				 * 
    				 * idea
    				 * List <Station> criticalStation = new List <Station> (stationId, date, isEmpty or isFull)
    				 * Result.criticalStations.add(criticalStation)
    				 * outofloop: write(Result.criticalStations()...)
    				 * afficher
    				 */
    				
    			}
    					
    		}
    			
    	}
    	
    	
    	return;
    }

}
