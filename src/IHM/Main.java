package IHM;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Data.*;
import Evaluation.*;
import Simulation.*;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException
	{
		//Reader
		Scanner reader = new Scanner(System.in);
		
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
		
	//	Scenario scenario_base = new Scenario (stationList, tripList);
		
		Simulator simul = new Simulator(tripList, stationList);
		simul.analyze();
		
		int mode = 0;
	//	int option;
		
		while(true)
		{
			switch(mode)
			{
				case 0:
					System.out.println("What would you like to do?");
					System.out.println("Analyse base scenario: 1");
					System.out.println("Analyse scenario without regulation: 2");
					System.out.println("Analyse scenario with new user behavior: 3");
					System.out.println("Quit application: 9");
					
					mode = reader.nextInt();
					
					if(mode == 9)
					{
						return;
					}

					break;


				case 1:
					System.out.println("1What would you like to do?");
					System.out.println("See critical stations: 1");
					System.out.println("See specific station: 2");
					System.out.println("Go back: 9");
					
					mode = reader.nextInt();
					
					switch(mode)
					{
						case 1:
							
							mode=1;
							
							
						case 2:
							
							mode=1;
							
							
						case 9:
							
							mode=0;
							
					}
					
				//
				case 2:
					System.out.println("2What would you like to do?");
					System.out.println("See critical stations: 1");
					System.out.println("See specific station: 2");
					System.out.println("Go back: 9");
					
					mode = reader.nextInt();
					
					switch(mode)
					{
						case 1:
							
							mode=2;
							
							
						case 2:
							
							mode=2;
						
							
						case 9:
							
							mode=0;
						
					}
					
				case 3:
					System.out.println("3What would you like to do?");
					System.out.println("See critical stations: 1");
					System.out.println("See specific station: 2");
					System.out.println("Go back: 9");
					
					mode = reader.nextInt();
					
					switch(mode)
					{
						case 1:
							
							mode=3;
							break;
							
						case 2:
							
							mode=3;
							break;
							
						case 9:
							
							mode=0;
							break;
					}
			}
			
		}
		
		
		
		
	}

}
