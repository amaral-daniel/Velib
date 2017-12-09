package Evaluation;


import IHM.*;
import Data.*;
import Simulation.*;
import java.util.ArrayList;

public class EvaluateurScenario 
{
    private Scenario refScenario;
    
	public EvaluateurScenario(Scenario newScenario)
	{
		refScenario = newScenario;
	}
	
    public ArrayList<Station> identifyCriticalStations() 
    { 	
    		ArrayList<Station> criticalStations = ArrayList<Station>();
    		ArrayList<Station> stationList = refScenario.getStationList();
    	
    		
    		for (int i = 0; i < stationList.size(); i++) 
    		{
	    		Station currentStation = stationList.get(i);
	    		int criticalTime = 0;
	    		if(EvaluateurStation.isCritical(currentStation ))
	    		{
	    			criticalStations.add(currentStation);
	    		}   			
	    	}   		    	
	    	return criticalStations;
    }

    //fonction pour dire si le scenario est deja desequilibre, return true si ils est desequilibre.
    //Il n'est pas encore implemente
    
    public int getSecondsTrajets()
    {
    		ArrayList<Trip> trips = refScenario.getTrips();
    		int totalSecondsTrajets = 0;
    		
    		for(int i = 0; i < trips.size(); i++)
    		{
    			Trip currentTrip = trips.get(i);
    			if(trips.get(i).isValid())
    			{
    				long duration = currentTrip.getEndDate().getTime() - currentTrip.getStartDate().getTime();
    				totalSecondsTrajets += (int)duration;
    			}
    			
    		}
    		return totalSecondsTrajets;
    }
    
    //fonction pour dire si le scenario est deja desequilibre, return true si ils est desequilibre.
    //Il n'est pas encore implemente
	public boolean isUnbalanced()
	{
		return true;
	}

}
