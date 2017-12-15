package Simulation;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.jfree.ui.RefineryUtilities;

import Evaluation.*;

import Data.*;

public class Simulator {
	private ArrayList<Trip> base_trips;
	private ArrayList<Station> stations;
	private Scenario scenario;
	private EvaluatorScenario evaluatorScenario;
	private ArrayList<Double> cancelledTrips;

	public Simulator(ArrayList<Trip> base_trips, ArrayList<Station> stations)
	{
		this.base_trips = base_trips;
		this.stations = stations;
		cancelledTrips = new ArrayList<Double>();
	}
	
	public void simulate(boolean regulation,double collaborationRate, double popularityGrowth) throws FileNotFoundException
	{
		TripGenerator tripGenerator = new TripGenerator(this.base_trips,regulation,popularityGrowth);	
		ArrayList<Trip> simulationTrips = tripGenerator.getTrips();
		
		scenario = new Scenario(stations,simulationTrips,collaborationRate);

		scenario.runTrips();
	
		evaluatorScenario = new EvaluatorScenario(scenario);

		evaluatorScenario.exportCSVCriticalStationsNames("output/criticalStations.csv");

	//	evaluatorScenario.visualizeCriticalStationsVariation(15*60*1000, 0);
		System.out.println("% cancelled trips analyze: " + evaluatorScenario.getCancelledTrips());
		this.cancelledTrips.add(evaluatorScenario.getCancelledTrips());
		simulate10days(regulation,collaborationRate,popularityGrowth);
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
	
	public void visualizeCriticalStationsVariation()
	{
		
	}
	
	public void visualizeCancelledTrips10days()
	{
		System.out.println("visualizing cancelled trips....");
		GraphCancelledTrips graph = new GraphCancelledTrips(1,"Days",this.cancelledTrips);
		graph.showWindow();
	}
	
	public void visualizeImpactGrowth()
	{
		TripGenerator tripGenerator;
		ArrayList<Trip> simulationTrips;
		Scenario simulationScenario;
		ArrayList<Double> cancelledTrips = new ArrayList<Double>();
		for(int i = 0; i < 10; i++)
		{
			tripGenerator = new TripGenerator(this.base_trips,false,i*0.15);	
			simulationTrips = tripGenerator.getTrips();
			simulationScenario = new Scenario(stations,simulationTrips,0);
			simulationScenario.runTrips();
			evaluatorScenario = new EvaluatorScenario(simulationScenario);
			cancelledTrips.add(evaluatorScenario.getCancelledTrips());
		}
		evaluatorScenario = new EvaluatorScenario(scenario);
		
		for(int i = 0; i < 5; i++)
		{
			scenario.startNewDay();
			scenario.runTrips();
			cancelledTrips.add(evaluatorScenario.getCancelledTrips());
		}
		
	}
	
	public void visualizeImpactCollaboration()
	{
		
	}
	
	private void simulate10days(boolean regulation,double collaborationRate, double popularityGrowth)
	{
		TripGenerator tripGenerator = new TripGenerator(this.base_trips,false,popularityGrowth);	
		ArrayList<Trip> simulationTrips = tripGenerator.getTrips();

		scenario = new Scenario(stations,simulationTrips,collaborationRate);

		evaluatorScenario = new EvaluatorScenario(scenario);
		
		for(int i = 0; i < 5; i++)
		{
			scenario.startNewDay();
			scenario.runTrips();
			cancelledTrips.add(evaluatorScenario.getCancelledTrips());
		}
		
		
		
	}
	
}
