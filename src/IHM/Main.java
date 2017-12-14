package IHM;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import Data.*;
import Evaluation.*;
import Simulation.*;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException
	{
		//File Names
		String stationAddressesFileName = "src/files/stationsAddresses.txt";
		String initialStatesFileName = "src/files/initialstates.txt";
		String tripsFileName = "src/files/trips-2013-10-31.txt";
		
		//Construct reader
		Read read = new Read(stationAddressesFileName, initialStatesFileName, tripsFileName);
		
		//Creates stationList with their initial condition
		ArrayList<Station> stationList = read.createStationList(stationAddressesFileName);
		read.defineInitalStates(initialStatesFileName, stationList);
		
		//Creates trip list
		ArrayList<Trip> tripList = read.createTripsList(tripsFileName, stationList);
		
		Scenario scenario01 = new Scenario (stationList, tripList);
		
		Simulator simul = new Simulator(tripList, stationList);
		simul.analyze();
		
		
		
		
		
	}

}
