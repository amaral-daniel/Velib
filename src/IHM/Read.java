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

import java.io.*;

// 
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
	private String addressesFileName;
	private String tripsFileName;
	
	public Read (String addressesFileName, String tripsFileName)
	{	
		this.addressesFileName = addressesFileName;
		this.tripsFileName = tripsFileName;
	}
	
	
	public void readAddresses(String addressesFileName)
	{
		
	}
	
	public void readTrips(String tripsFileName)
	{	
		File tripsFile = new File(tripsFileName);
		
		String line = null;
		
		try 
		{
			FileReader fr = new FileReader(tripsFile);
		
			BufferedReader buffer = new BufferedReader(fr);
		
			while((line = buffer.readLine())!= null) 
			{
				//here the data will be stored in the
				//different objects of the project
				System.out.println(line);
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
	
	
	
	
	public static void main (String[] args) 
	{
		String tripsFileName = "Velib\\src\\files\\trips-2013-10-31.txt";
		String addressesFileName = "Velib\\src\\files\\stationAddresses.txt";
		String test = "Velib\\src\\files\\writing_test.txt";
		
		
		Read read = new Read(addressesFileName, tripsFileName);
		
		read.readTrips(test);
		
		
	}
	
	

}
