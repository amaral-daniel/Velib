package Simulation;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import Evaluation.*;

import Data.*;

public class Simulator {
	private ArrayList<Trip> base_trips;
	private ArrayList<Station> stations;
	private Scenario scenario;
	private EvaluatorScenario evaluatorScenario;
	

	public Simulator(ArrayList<Trip> base_trips, ArrayList<Station> stations)
	{
		this.base_trips = base_trips;
		this.stations = stations;
	}
	
	public void simulate(boolean regulation,double collaborationRate, double popularityGrowth) throws FileNotFoundException
	{
		TripGenerator tripGenerator = new TripGenerator(this.base_trips,false,popularityGrowth);	
		ArrayList<Trip> simulationTrips = tripGenerator.getTrips();
		
		scenario = new Scenario(stations,simulationTrips,collaborationRate);

		scenario.runTrips();
	
		evaluatorScenario = new EvaluatorScenario(scenario);

		evaluatorScenario.exportCSVCriticalStationsNames("output/criticalStations.csv");

		evaluatorScenario.visualizeCriticalStationsVariation(15*60*1000, 0);
		System.out.println("% cancelled trips analyze: " + evaluatorScenario.getCancelledTrips());

	}

	public void simulate7days(boolean regulation,double collaborationRate, double popularityGrowth)
	{
		TripGenerator tripGenerator = new TripGenerator(this.base_trips,false,popularityGrowth);	
		ArrayList<Trip> simulationTrips = tripGenerator.getTrips();
		
		ArrayList<Double> cancelledTrips;
		scenario = new Scenario(stations,simulationTrips,collaborationRate);

		evaluatorScenario = new EvaluatorScenario(scenario);
		
		for(int i = 0; i < 7; i++)
		{
			scenario.runTrips();
		//	evaluatorScenario.getCancelledTrips
		//	scenario.startNewDay();
		}
		
		scenario.runTrips();
		
		
	}
	
 	public void exportStationStates(int identity) throws FileNotFoundException
	{
		EvaluatorScenario evaluatorScenario = new EvaluatorScenario(scenario);
		
		evaluatorScenario.exportCSVStationStates(identity, "output/states_");		
	}

	public void exportStationStates(String stationName) throws FileNotFoundException
	{
		EvaluatorScenario evaluatorScenario = new EvaluatorScenario(scenario);
		
		evaluatorScenario.exportCSVStationStates(stationName, "output/states_");
	}
	
	public void visualizeStationStates(int identity)
	{
		evaluatorScenario.visualizeStationStates(identity);
	}
	

	
	
	
}
