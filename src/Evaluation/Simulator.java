package Evaluation;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import Simulation.*;

import Data.*;

public class Simulator {
	private ArrayList<Trip> base_trips;
	private ArrayList<Station> stations;
	Scenario scenario;
	
	

	public Simulator(ArrayList<Trip> base_trips, ArrayList<Station> stations)
	{
		this.base_trips = base_trips;
		this.stations = stations;
	}
	
	public void analyze() throws FileNotFoundException
	{
	//	ArrayList<Station> stations_copy = getCopyOfStations();

		scenario = new Scenario(stations,base_trips);

		scenario.runTrips();

		
		EvaluatorScenario evaluatorScenario = new EvaluatorScenario(scenario);

	//	evaluatorScenario.exportCSVCriticalStationsNames("src/Evaluation/criticalStations.csv");

	//	evaluatorScenario.exportCSVCriticalStationsVariation(10*60,"src/Evaluation/variationOfCriticalStations.csv");
		
		evaluatorScenario.exportCSVStationStates(1023, "states_");
		
		evaluatorScenario.visualizeStationStates(1023);
		evaluatorScenario.visualizeStationStates(1008);
		evaluatorScenario.visualizeCriticalStationsVariation(15*60);
		System.out.println("csvs exported");
		
	}
	
	private ArrayList<Station> getCopyOfStations()
	{
		ArrayList<Station> newStationList = new ArrayList<Station>();
		for(int i =0; i < stations.size(); i++)
		{
			newStationList.add(new Station(stations.get(i)));
		}
		
		return newStationList;
	}
	
	public void simulateRegulationOff() throws FileNotFoundException
	{
		ArrayList<Station> stations_copy = getCopyOfStations();
		TripGenerator tripGenerator = new TripGenerator(this.base_trips,false,0);
		
		ArrayList<Trip> noRegulationTrips = tripGenerator.getTrips();
		
		scenario = new Scenario(stations_copy,noRegulationTrips);
		
		scenario.runTrips();
		
		EvaluatorScenario evaluatorScenario = new EvaluatorScenario(scenario);
		
		evaluatorScenario.exportCSVCriticalStationsNames("src/Evaluation/criticalStationsNoRegulation.csv");
		
		evaluatorScenario.exportCSVCriticalStationsVariation(10*60,"src/Evaluation/variationOfCriticalStationsNoRegulation.csv");
		
	}

	public void simulateGrowthPopularity(float popularity_growth) throws FileNotFoundException
	{
		ArrayList<Station> stations_copy = getCopyOfStations();
		TripGenerator tripGenerator = new TripGenerator(this.base_trips,true,popularity_growth);
		
		ArrayList<Trip> noRegulationTrips = tripGenerator.getTrips();
		
		scenario = new Scenario(stations_copy,noRegulationTrips);
		
		scenario.runTrips();
		
		EvaluatorScenario evaluatorScenario = new EvaluatorScenario(scenario);
		
		evaluatorScenario.exportCSVCriticalStationsNames("src/Evaluation/criticalStationsPopularityGrowth.csv");
		
		evaluatorScenario.exportCSVCriticalStationsVariation(10*60,"src/Evaluation/variationOfCriticalStationsPopularityGrowth.csv");
	
	}

	public void exportStationStates(int identity) throws FileNotFoundException
	{
		EvaluatorScenario evaluatorScenario = new EvaluatorScenario(scenario);
		
		evaluatorScenario.exportCSVStationStates(identity, "src/Evaluation/states_");
		
	}
	
	public void exportStationStates(String stationName) throws FileNotFoundException
	{
		EvaluatorScenario evaluatorScenario = new EvaluatorScenario(scenario);
		
		evaluatorScenario.exportCSVStationStates(stationName, "src/Evaluation/states_");
		
	}
	

	
	
	
}
