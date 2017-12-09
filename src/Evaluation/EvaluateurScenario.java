package Evaluation;


import IHM.*;
import Data.*;
import Simulation.*;
import java.util.ArrayList;

public class EvaluateurScenario {
    private Scenario refScenario;
	
	
	/** The Method evaluates for a given Scenario the critical States of all Stations and writes it to file **/
    public ArrayList<Station> identifyCriticalStations(Scenario refScenario) 
    {

    	
    	ArrayList<Station> criticalStations;
    	ArrayList<Station> stationList = refScenario.getStations();
    	
    	for (int i; i < stationList.size(); i++) 
    	{
    		
    		Station currentStation = stationList.get(i);
    		ArrayList<State> stationStates = currentStation.getStates();
    		int criticalTime = 0;
    		

    		if(EvaluateurStation.isCritical(currentStation ))
    		{
    			criticalStations.add(currentStation);
    		}
    			
    	}   	
    	
    	return criticalStations;
    }

}
