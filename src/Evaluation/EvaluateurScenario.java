package Evaluation;

import Data.*;
import Simulation.*;
import java.util.ArrayList;
import java.util.Date;
import java.io.FileNotFoundException;
import java.util.GregorianCalendar;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class EvaluateurScenario 
{
    private Scenario refScenario;
    private float unbalancedCriteria;
    
	public EvaluateurScenario(Scenario newScenario)
	{
		refScenario = newScenario;
		unbalancedCriteria = (float)0.7;
	}
	
	//fonction qui va identifier tous les stations qui sont restes vide ou pleines pendant plus que 30 minutes
    public ArrayList<Station> identifyCriticalStations() 
    { 	
    		ArrayList<Station> criticalStations = new ArrayList<Station>();
    		ArrayList<Station> stationList = refScenario.getStationList();
    	
    		
    		for (int i = 0; i < stationList.size(); i++) 
    		{
	    		Station currentStation = stationList.get(i);
	    		EvaluateurStation.setMinimalCriticalTime(30*60);
	    		if(EvaluateurStation.isCritical(currentStation ))
	    		{
	    			criticalStations.add(currentStation);
	    		}   			
	    	}   		    	
	    	return criticalStations;
    }

    public void exportCSVCriticalStations(int step) throws FileNotFoundException
    {
    		ArrayList<Station> stations = refScenario.getStationList();
    		Date initialDate = stations.get(0).getState(0).getDate();		
    		
    		PrintWriter out = new PrintWriter( "src/Evaluation/variationOfCriticalStations.csv") ;
    		out.println("Time, CriticalStations");
    		DateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm"); 
    		
    		for(int i = 0; step*i < 24*60*60; i++)
    		{
    			Date currentDate = new Date(initialDate.getTime() + i*step*1000);
    		    String reportDate = df.format(currentDate.getTime());
    			int numberOfBadStations = 0;

    			for(int j = 0; j < stations.size() ; j++)
    			{
    				if(EvaluateurStation.isEmptyOrFull(stations.get(j), currentDate))
    				{
    					numberOfBadStations += 1;
    				}
    			}
    			
    		   	out.println(reportDate + "," + numberOfBadStations);
    		}
    		out.close();
    		
    		
    		
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
    //Il faut calculer la % de trips qui ont ete canceles/ ont eu des problemes
    
	public boolean isUnbalanced()
	{
		ArrayList<Trip> trips = refScenario.getTripList();
		int validTrips = 0;
		for(int i = 0; i < trips.size(); i ++)
		{
			if(trips.get(i).isValid())
			{
				validTrips += 1;
			}
		}
		
		if(validTrips/trips.size() < unbalancedCriteria)
		{
			return true;
		}
		
		return false;
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
		
		Station station1 = new Station(identity1,name1,address1,capacity1,0,0);
		station1.setIsOpen(true);
		Station station2 = new Station(identity2,name2,address2,capacity2,0,0);
		station2.setIsOpen(true);
		ArrayList<Station> stationList = new ArrayList<Station>();
		stationList.add(station1);
		stationList.add(station2);
		
		//Setting primary states....
		
		Date dateState1 = new GregorianCalendar(1995, 02, 31,0,0).getTime();
		Date dateState2 = new GregorianCalendar(1995, 02, 31,0,0).getTime();	
		
		State state1 = new State(numberOfFreeBikes1,dateState1, 20);
		State state2 = new State(numberOfFreeBikes2,dateState2 , 20);
		
		station1.setPrimaryState(state1);
		station2.setPrimaryState(state2);
				
		Date startTrip1 = new GregorianCalendar(1995, 02, 31,1,28).getTime();
		Date endTrip1 = new GregorianCalendar(1995, 02, 31,2,29).getTime();
		Date startTrip2 = new GregorianCalendar(1995, 02, 31,2,30).getTime();
		Date endTrip2 = new GregorianCalendar(1995, 02, 31,2,31).getTime();
		Date startTrip3 = new GregorianCalendar(1995, 02, 31,2,32).getTime();
		Date endTrip3 = new GregorianCalendar(1995, 02, 31,2,33).getTime();
		Date startTrip4 = new GregorianCalendar(1995, 02, 31,2,39).getTime();
		Date endTrip4 = new GregorianCalendar(1995, 02, 31,2,40).getTime();
		//Creating trips.....
		
		Trip trip1 = new Trip(Reason.RENT, startTrip1, station1, endTrip1, station2);
		Trip trip2 = new Trip(Reason.RENT, startTrip2 , station1,endTrip2, station2);
		Trip trip3 = new Trip(Reason.RENT, startTrip3, station1,endTrip3, station2);
		Trip trip4 = new Trip(Reason.RENT, startTrip4 , station1,endTrip4, station2);
		ArrayList<Trip> tripList = new ArrayList<Trip>();
		tripList.add(trip1);
		tripList.add(trip2);
		tripList.add(trip3);
		tripList.add(trip4);
		
		//Creating scenario.....
		
		Scenario scenario = new Scenario(stationList,tripList);
		
		//Starting tests.....
		
		System.out.println("--------------before running trips-------------\n");
		
		ArrayList<Station> stations = scenario.getStationList();
		
		for(int i = 0; i < stations.size(); i++)
		{
			System.out.println(stations.get(i));
		}
		
		//System.out.println("running trips....\n");
		scenario.runTripsTest();
		
		EvaluateurScenario my_evaluateur = new EvaluateurScenario(scenario);
		
		int secondsTrajet  = my_evaluateur.getSecondsTrajets();
		
		System.out.println("total time :" + secondsTrajet);
		
		ArrayList<Station> criticalStations = my_evaluateur.identifyCriticalStations();
		
		System.out.println("-------------------------critical stations -----------------------\n ");
		for(int i = 0; i < criticalStations.size(); i++)
		{
			System.out.println(criticalStations.get(i));
		}
		
		System.out.println("--------------after running trips------------- \n");
		
		
		stations = scenario.getStationList();
		
		for(int i = 0; i < stations.size(); i++)
		{
			System.out.println(stations.get(i));
		}
		
		System.out.println("------------writting to files-----------");
		
		for(int i = 0; i < stations.size(); i++)
		{
			EvaluateurStation.exportCSVStationStates(stations.get(i));
		}
		
		System.out.println("----------testing isEmptyOrFull-------------");
		
		Date date1 = new GregorianCalendar(1995, 02, 31,2,36).getTime();
		boolean isCritical =  EvaluateurStation.isEmptyOrFull(station1, date1);
		System.out.println("Station 1 is critical at" + date1 + " ? " + isCritical);
		
		Date date2 = new GregorianCalendar(1995, 02, 31,2,34).getTime();
		isCritical =  EvaluateurStation.isEmptyOrFull(station2, date2);
		System.out.println("Station 2 is critical at" + date2 + " ? " + isCritical);
		
		System.out.println("--------------testing exportCSVCriticalStations--------");
		
		my_evaluateur.exportCSVCriticalStations(30*60);
		
		System.out.println("-------------testing isUnbalanced-------------");
		
		boolean isUnbalanced = my_evaluateur.isUnbalanced();
		
		System.out.println("is unbalanced" + isUnbalanced);
		
		
	}

}


