//package IHM;

//import com.modeliosoft.modelio.javadesigner.annotations.objid;

//@objid ("13ef917a-3abc-45ed-bde2-178f34720905")
//public class Read {
//    @objid ("58175a03-1e5d-418d-961c-1e04642a1e43")
//    public void loadTrips() {
//    }

//    @objid ("9e38d89f-aca5-49e2-9182-8f1ecba2a130")
//    public void loadStations() {
//    }

//    @objid ("73907320-92d8-485f-bc54-280a64d2791a")
//    public void loadState() {
//  }
//}

package IHM;

import Data.*;
import IO.State;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

 
public class Read 
{
	/* Class Read:
	 * Attributes: 
	 * 2 Strings representing the path of address file and trips file.
	 * Format: "Velib\\src\\files\\filename.txt"
	 * 
	 * Methods: 
	 * readTrips: REads trips file and converts the text in data.
	 * 
	 * */
	private String stationsAddressesFileName;
	private String tripsFileName;
	
	public Read (String stationsAddressesFileName, String tripsFileName)
	{	
		this.stationsAddressesFileName = stationsAddressesFileName;
		this.tripsFileName = tripsFileName;
	}
	
	
	public ArrayList<Station> createStationList(String stationsAddressesFileName)
	{
		File stationsFile = new File(stationsAddressesFileName);
		
		String line = null;
		
		try 
		{
			FileReader fr = new FileReader(stationsFile);
		
			BufferedReader buffer = new BufferedReader(fr);
			
			//List to be returned
			ArrayList <Station> stationList = new ArrayList();
			
			while((line = buffer.readLine())!= null)
			{				
				//How to get info from this stupid file...?
			//	int identity;
			//	int capacity;
			//	String name;
			//	String address;
			//	int longitude; 
			//	int latitude;
			//	State primaryState;	
			//	Station station = new Station(/*only infos in the txt file*/);
			//	stationList.add(station); 
			
			}
			
			buffer.close();
			
			return stationList;
		}
		catch(FileNotFoundException error) 
		{
			System.out.println("File not found");
		}
		catch(IOException error) 
		{
			System.out.println("Something went wrong");
		}
	}
	
	
	public void defineInitalStates(String statesFileName, ArrayList<Station> stationList)
	{
		File statesFile = new File(statesFileName);
		
		String line = null;
		
		try 
		{
			FileReader fr = new FileReader(statesFile);
		
			BufferedReader buffer = new BufferedReader(fr);
			
			while((line = buffer.readLine())!= null)
			{				
				//How to get info from this stupid file...?
				/*Need:
				 * int numOfFreeBikes, Date currentDate, int capacity;
				 */
				State state = new State(/*infos in this file*/);
				/*Define a function that searches a station inside stationList 
				 * and then stationList.setState(state); */
			}
			
			buffer.close();

		}
		catch(FileNotFoundException error) 
		{
			System.out.println("File not found");
		}
		catch(IOException error) 
		{
			System.out.println("Something went wrong");
		}
	}
	
	
	
	public ArrayList<Trip> createTripsList(String tripsFileName)
	{	
		File tripsFile = new File(tripsFileName);
		
		String line = null;
		
		try 
		{
			FileReader fr = new FileReader(tripsFile);
		
			BufferedReader buffer = new BufferedReader(fr);
			
			//List to be returned
			ArrayList <Trip> tripList = new ArrayList();
			
			while((line = buffer.readLine())!= null)
			{	
				//Skip first line
				if(line.startsWith("reason"))
					continue;
				else
				{
				//here the data will be stored in the
				//different objects of the project
				String[] split = line.split("\t");	
				// "\t" or "\\t", not entirely sure
				//Warning: sometimes the items are separated by " ", not "\t"
				
				int reason = Integer.parseInt(split[0]);
				String startTime = split[1];
				int startSation = Integer.parseInt(split[2]); 
			//	int startBikeStand = Integer.parseInt(split[3]);   
				String endTime = split[4];
				int endSation = Integer.parseInt(split[5]); 
			//	int endBikeStand = Integer.parseInt(split[6]);
				Trip trip = new Trip(/*must declare Java.Date objects to construct*/);
				
				tripList.add(trip);
				}
			}
			buffer.close();
			return tripList;
		}
		catch(FileNotFoundException error) 
		{
			System.out.println("File not found");
		}
		catch(IOException error) 
		{
			System.out.println("Something went wrong");
		}
	}
	
	
	
	
	public static void main (String[] args) 
	{
		String tripsFileName = "Velib\\src\\files\\trips-2013-10-31.txt";
		String addressesFileName = "Velib\\src\\files\\stationAddresses.txt";
		String test = "Velib\\src\\files\\writing_test.txt";
		
		
		Read read = new Read(addressesFileName, tripsFileName);
		
		read.createTripsList(tripsFileName);
		
		
	}
	
	

}
