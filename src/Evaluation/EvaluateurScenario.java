package Evaluation;


import IHM.*;
import Data.*;
import Simulation.*;
import java.util.ArrayList;
import java.util.Date;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.*;
import java.util.GregorianCalendar;
import java.io.PrintWriter;

public class EvaluateurScenario 
{
    private Scenario refScenario;
    
	public EvaluateurScenario(Scenario newScenario)
	{
		refScenario = newScenario;
	}
	
	//fonction qui va identifier tous les stations qui sont restes vide ou pleines pendant plus que 30 minutes
    public ArrayList<Station> identifyCriticalStations() 
    { 	
    		ArrayList<Station> criticalStations = new ArrayList<Station>();
    		ArrayList<Station> stationList = refScenario.getStationList();
    	
    		
    		for (int i = 0; i < stationList.size(); i++) 
    		{
	    		Station currentStation = stationList.get(i);
	    		int criticalTime = 0;
	    		EvaluateurStation.setMinimalCriticalTime(30*60);
	    		if(EvaluateurStation.isCritical(currentStation ))
	    		{
	    			criticalStations.add(currentStation);
	    		}   			
	    	}   		    	
	    	return criticalStations;
    }

    public void exportCSVCriticalStations()
    {
    	
    }
    
    public void exportCSVStationStates(int identity) throws FileNotFoundException
    {
    		ArrayList<Station> stationList = refScenario.getStationList();
    		Station searchedStation = null;
    		
    		
    		for(int i = 0; i < stationList.size(); i++)
    		{
    			if(stationList.get(i).getIdentity() == identity )
    			{
    				searchedStation = stationList.get(i);
    				i = stationList.size(); //ugly solution to quit the loop
    			}
    		}
    		if(searchedStation == null)
    		{
    			System.out.println("station " + identity + " not found!!!!!!!!!!!111 \n");
    			return;
    		}
    		
    		EvaluateurStation.exportCSVStationStates(searchedStation);	
    }
    
    
    
    //fonction qui va donner la somme du temps de tous les trajets valides
    public int getSecondsTrajets()
    {
    		ArrayList<Trip> trips = refScenario.getTripList();
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
    		return totalSecondsTrajets/1000; //Converts miliseconds into seconds
    }
    
    //fonction pour dire si le scenario est deja desequilibre, return true si ils est desequilibre.
    //Il n'est pas encore implemente
	public boolean isUnbalanced()
	{
		return true;
	}


	public static void main(String[] args) throws FileNotFoundException
	{
		
		//Creating stations......
		
		int identity1 = 1;
		int capacity1 = 20;
		String name1 = "Meuh";
		String address1 = "Rue Saint Jacques";
		int numberOfFreeBikes1 = 5;
		
		int identity2 = 1;
		int capacity2 = 20;
		String name2 = "Mines";
		String address2 = "Boulevard St. Michel";
		int numberOfFreeBikes2 = 6;
		
		Station station1 = new Station(identity1,capacity1,name1,address1,0,0);
		station1.setIsOpen(true);
		Station station2 = new Station(identity2,capacity2,name2,address2,0,0);
		station2.setIsOpen(true);
		ArrayList<Station> stationList = new ArrayList<Station>();
		stationList.add(station1);
		stationList.add(station2);
		
		//Setting primary states....
		
		Date dateState1 = new GregorianCalendar(1995, 02, 31,0,0).getTime();
		Date dateState2 = new GregorianCalendar(1995, 02, 31,0,0).getTime();	
		
		State state1 = new State(1,dateState1, 20);
		State state2 = new State(19,dateState2 , 20);
		
		station1.setPrimaryState(state1);
		station2.setPrimaryState(state2);
				
		Date startTrip1 = new GregorianCalendar(1995, 02, 31,1,0).getTime();
		Date endTrip1 = new GregorianCalendar(1995, 02, 31,2,30).getTime();
		Date startTrip2 = new GregorianCalendar(1995, 02, 31,2,31).getTime();
		Date endTrip2 = new GregorianCalendar(1995, 02, 31,2,49).getTime();
				
		//Creating trips.....
		
		Trip trip = new Trip(Reason.RENT, startTrip1, station1,endTrip1, station2);
		Trip trip2 = new Trip(Reason.RENT, startTrip2 , station1,endTrip2, station2);
		ArrayList<Trip> tripList = new ArrayList<Trip>();
		tripList.add(trip);
		
		//Creating scenario.....
		
		Scenario scenario = new Scenario(stationList,tripList);
		
		//Starting tests.....
		
		System.out.print("--------------before running trips-------------\n");
		
		ArrayList<Station> stations = scenario.getStationList();
		
		for(int i = 0; i < stations.size(); i++)
		{
			System.out.println(stations.get(i));
		}
		
		//System.out.println("running trips....\n");
		scenario.runTripsTest();
		
		EvaluateurScenario my_evaluateur = new EvaluateurScenario(scenario);
		
		int secondsTrajet  = my_evaluateur.getSecondsTrajets();
		
		//System.out.println("total time :" + secondsTrajet);
		
		ArrayList<Station> criticalStations = my_evaluateur.identifyCriticalStations();
		
		System.out.print("-------------------------critical stations -----------------------\n ");
		for(int i = 0; i < criticalStations.size(); i++)
		{
			System.out.println(criticalStations.get(i));
		}
		
		System.out.print("--------------after running trips------------- \n");
		
		
		stations = scenario.getStationList();
		
		for(int i = 0; i < stations.size(); i++)
		{
			System.out.println(stations.get(i));
		}
		
		System.out.print("------------writting to files-----------");
		
		for(int i = 0; i < stations.size(); i++)
		{
			EvaluateurStation.exportCSVStationStates(stations.get(i));
		}
		
		
	}

}


