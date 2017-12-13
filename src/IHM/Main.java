package IHM;

import java.util.ArrayList;

import Data.*;
import Evaluation.*;
import Simulation.*;

public class Main {
	
	public static void main(String[] args)
	{
		//File Names
		String stationAddressesFileName = "src/files/stationsAddresses.txt";
		String initialStatesFileName = "src/files/initialstates.txt";
		String tripsFileName = "src/files/trips-2013-10-31.txt";
		
		//Construct reader
		Read read = new Read(stationAddressesFileName, initialStatesFileName, tripsFileName);
		
		//Create the stationList with their initial condition and also tripList
		ArrayList<Station> stationList = read.createStationList(stationAddressesFileName);
		read.defineInitalStates(initialStatesFileName, stationList);
		ArrayList<Trip> tripList = read.createTripsList(tripsFileName, stationList);
		
		
		
		
		
		
		
		
	}

}
