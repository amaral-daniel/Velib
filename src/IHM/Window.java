package IHM;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Data.*;
import Evaluation.*;
import Simulation.Simulator;
import Simulation.*;

//Window imports
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.*;

public class Window extends JFrame implements ActionListener
{
	//Window's variables to be used
	
	//Window's layout objects
	private JButton button_run, button_show;
	
	private JTextField input_collaboration_rate;
	private JTextField input_regulation;
	private JTextField input_popularity_growth;
	private JTextField input_station;
	private JLabel label_collaboration_rate;
	private JLabel label_regulation;
	private JLabel label_popularity_growth;
	private JLabel label_station;
	private JLabel label_cancelled_trips;
	private JLabel label_time_unbalanced;
	
	//Objects used in the simulation
	private static ArrayList<Station> baseStationList;
	private static ArrayList<State> stateList;
	private static ArrayList<Trip> baseTripList;
	
	
	//User input
	private static double collaboration_rate = 0.0;
	private static int regulation = 1;
	private static double popularity_growth = 0.0;
	
	
	//Simple boolean to prevent a Run 
	static boolean ready = false;
	
	public static void main(String[] args) throws FileNotFoundException
	{
		
		Window window = new Window(500, 400, "Projet Velib");
		
		
		//Reader (pra input no console): excluir se for inutil
		Scanner reader = new Scanner(System.in);
		
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
		button_run.setBounds(230, 200, 80, 30); //x,y,width,height
		
		button_show = new JButton("Show");
		button_show.setBounds(330, 200, 80, 30);
		button_show.addActionListener(this);
		
		
		
		input_collaboration_rate = new JTextField();
		input_collaboration_rate.setBounds(50, 30, 110, 20);
		
		input_regulation = new JTextField();
		input_regulation.setBounds(200, 30, 110, 20);
		
		input_popularity_growth = new JTextField();
		input_popularity_growth.setBounds(320, 30, 110, 20);
		
		input_station = new JTextField();
		input_station.setBounds(250, 250, 110, 20);

		
		
		label_collaboration_rate = new JLabel("Collaboration Rate");
		label_collaboration_rate.setBounds(210, 80, 300, 40);
		
		panel.add(button_run);
		panel.add(button_show);
		
		panel.add(input_collaboration_rate);
		panel.add(input_regulation);
		panel.add(input_popularity_growth);
		panel.add(input_station);
		
		panel.add(label_collaboration_rate);
		
	}
	
	
		public void actionPerformed(ActionEvent e)
		{
			//Event from button "run"
			if(e.getSource() == button_run)
			{
				if(!ready) {
					System.out.println("Please wait");
				}
				else {
					Simulator simulas = new Simulator(baseTripList, baseStationList);
					try {
						simulas.simulate(false,0,0);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("File not found");
					}
				}
			}
			
			if(e.getSource() == button_show)
			{
				System.out.println("button_show");
			}
			
			if(e.getSource() == input_collaboration_rate)
			{
				String collab_input = input_collaboration_rate.getText();
				input_collaboration_rate.selectAll();
				System.out.println(collab_input);
				
			}
			
			
		}
}