package Evaluation;

import Data.*;

import Simulation.Scenario;

import java.util.ArrayList;
import java.util.Date;

import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.general.SeriesException; 
import org.jfree.data.time.Second; 
import org.jfree.data.time.TimeSeries; 
import org.jfree.data.time.TimeSeriesCollection; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities;

public class GraphScenario extends ApplicationFrame {
	private Scenario scenario;
	private int step;
   public GraphScenario(  Scenario scenario, int step ) {

      super( "grafico" );   
      this.step = step;
      this.scenario = scenario;
      final XYDataset dataset = createDataset( );        
      final JFreeChart chart = createChart( dataset );       
      final ChartPanel chartPanel = new ChartPanel( chart ); 
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 370 ) );  
      chartPanel.setMouseZoomable( true , false );         
      setContentPane( chartPanel );

   }

   private XYDataset createDataset( ) {
      final TimeSeries series = new TimeSeries( "Velib Graph" );         
   //   Second current = new Second( );   
      ArrayList<Station> stations = scenario.getStationList();
      Date initialDate = stations.get(0).getState(0).getDate();	
	   for(int i = 0; step*i < 24*60*60; i++) {
         try {
        	 	int numberOfBadStations = 0;
        	 	Date currentDate = new Date(initialDate.getTime() + i*step*1000);
        	 	Second current = new Second(currentDate);
        		for(int j = 0; j < stations.size() ; j++)
    			{
    				if(EvaluatorStation.isEmptyOrFull(stations.get(j), currentDate))
    				{
    					numberOfBadStations += 1;
    				}
    			}             
            series.add(current, new Double( numberOfBadStations ) );                 
            current = ( Second ) current.next( ); 
         } catch ( SeriesException e ) {
            System.err.println("Error adding to series");
         }
      }

      return new TimeSeriesCollection(series);
   }     
   
   public void showWindow()
   {
	    this.pack( );         
	    RefineryUtilities.positionFrameRandomly( this );         
	    this.setVisible( true );
   }

   private JFreeChart createChart( final XYDataset dataset ) {
      return ChartFactory.createTimeSeriesChart(             
         "Variation of critical stations", 
         "Time",              
         "Number of bikes",              
         dataset,             
         false,              
         false,              
         false);
   }

   
   public static void main( final String[ ] args ) {

   }
}   