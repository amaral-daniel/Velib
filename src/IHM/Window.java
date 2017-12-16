package IHM;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import Data.*;
import Evaluation.*;
import Simulation.Simulator;
import Simulation.*;

//Imports pour la fenetre
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.*;



public class Window extends JFrame implements ActionListener
{	
	//Objets du layout de la fenetre
	private JButton button_run, button_show, button_growth, button_collaboration, button_crit_export;
	private JTextField input_collaboration_rate, input_regulation, input_popularity_growth, input_station;
	JLabel label_collaboration_rate, label_regulation, label_popularity_growth, label_station, 
	label_cancelled_trips, label_time_unbalanced;
	
	//Objects used in the simulation
	private static ArrayList<Station> baseStationList;
	private static ArrayList<Trip> baseTripList;
	private static Simulator simulation; 
	
	
	//User input
	private static double collaboration_rate = 0.0;
	private static boolean regulation = true;
	private static double popularity_growth = 0.0;
	private static int stationID = 901;
	
	//Defense mechanisms
	static boolean ready = false;
	static boolean alreadyRun = false;
	
	public static void main(String[] args) throws FileNotFoundException
	{
		
		Window window = new Window(500, 400, "Projet Velib");
		
		//File Names
		String stationAddressesFileName = "src/files/stationsAddresses.txt";
		String initialStatesFileName = "src/files/initialstates.txt";
		String tripsFileName = "src/files/trips-2013-10-31.txt";
		
		//Construct reader
		Read read = new Read();
		
		//Creates stationList with their initial condition
		baseStationList = read.createStationList(stationAddressesFileName);
		read.defineInitalStates(initialStatesFileName, baseStationList);
		
		//Creates trip list
		baseTripList = read.createTripsList(tripsFileName, baseStationList);
	
		ready=true;
		System.out.println("ok");
		
		
	//	Scenario scenario_base = new Scenario (stationList, tripList);
		
	//	Simulator simul = new Simulator(tripList, stationList);
	//	simul.analyze();
		
		
		
		

	}
	
	
	public Window(int width, int height, String title)
	{
		this.setSize(width, height);
		this.setTitle(title);
		this.setResizable(false);
        this.setLocation(400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		this.add(panel);
		
		
		button_run = new JButton("Run");
		button_run.addActionListener(this);
		button_run.setBounds(50, 120, 80, 30); //x,y,width,height
		
		button_show = new JButton("Show");
		button_show.setBounds(140, 120, 80, 30);
		button_show.addActionListener(this);
		
		button_growth = new JButton("Pop. Growth");
		button_growth.setBounds(230, 120, 80, 30);
		button_growth.addActionListener(this);
		
		button_collaboration = new JButton("Collaboration");
		button_collaboration.setBounds(320, 120, 80, 30);
		button_collaboration.addActionListener(this);
		
		button_crit_export = new JButton("Export Station");
		button_crit_export.setBounds(320, 200, 80, 30);
		button_crit_export.addActionListener(this);
		
		
		input_collaboration_rate = new JTextField();
		input_collaboration_rate.setBounds(50, 30, 110, 20);
		input_collaboration_rate.addActionListener(this);
		
		input_regulation = new JTextField();
		input_regulation.setBounds(200, 30, 110, 20);
		input_regulation.addActionListener(this);
		
		input_popularity_growth = new JTextField();
		input_popularity_growth.setBounds(350, 30, 110, 20);
		input_popularity_growth.addActionListener(this);
		
		input_station = new JTextField();
		input_station.setBounds(250, 300, 110, 20);
		input_station.addActionListener(this);
		
		
		label_collaboration_rate = new JLabel("Collaboration Rate");
		label_collaboration_rate.setBounds(210, 80, 300, 40);
		
		panel.add(button_run);
		panel.add(button_show);
		panel.add(button_growth);
		panel.add(button_collaboration);
		panel.add(button_crit_export);
		
		panel.add(input_collaboration_rate);
		panel.add(input_regulation);
		panel.add(input_popularity_growth);
		panel.add(input_station);
		
	//	panel.add(label_collaboration_rate);
		
	}
	
	
		public void actionPerformed(ActionEvent e)
		{
			if(!ready)
			{
				System.out.println("Please wait");
			}
			else
			{
				//Event from button "run"
				if(e.getSource() == button_run)
				{
					simulation = new Simulator(baseTripList, baseStationList,regulation, 
							collaboration_rate, popularity_growth);
					try {
						
						simulation.simulate();
						alreadyRun = true;
							
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
						System.out.println("File not found");
					}
					
				}
				
				if(e.getSource() == input_collaboration_rate)
				{
					String collab_input = input_collaboration_rate.getText();
					input_collaboration_rate.selectAll();
					collaboration_rate = Double.parseDouble(collab_input)/100;
					System.out.println("Collaboration rate = "+collaboration_rate);
					
				}
				
				if(e.getSource() == input_regulation)
				{
					String reg_input = input_regulation.getText();
					input_regulation.selectAll();
					
					regulation = Integer.parseInt(reg_input)==1 ? true : false;
					System.out.println(regulation);
					
				}
				
				if(e.getSource() == input_popularity_growth)
				{
					String pop_input = input_popularity_growth.getText();
					input_popularity_growth.selectAll();
					popularity_growth = Double.parseDouble(pop_input)/100;
					System.out.println(popularity_growth);
					
				}
				
				if(!alreadyRun)
				{
					System.out.println("Must first run");
				}
				else
				{
					if(e.getSource() == button_show)
					{
						simulation.visualizeCriticalStationsVariation();
						simulation.visualizeCancelledTrips10days();
					}
					
					if(e.getSource() == button_growth)
					{
						simulation.visualizeImpactGrowth();
					}
					
					if(e.getSource() == button_collaboration)
					{
						simulation.visualizeImpactCollaboration();
					}
					
					
					if(e.getSource() == input_station)
					{
						String sta_input = input_station.getText();
						input_station.selectAll();
						stationID = Integer.parseInt(sta_input);
						simulation.visualizeStationStates(stationID);
						System.out.println("StationID = "+stationID);
						
					}
					
					if(e.getSource() == button_crit_export)
					{
						System.out.println("ue");
						try {
							System.out.println("ue2");
							simulation.exportStationStates(stationID);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					
					
				}
				
				
			}
			
			
		}
		
}