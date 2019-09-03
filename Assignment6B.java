import java.io.*;
import java.util.Scanner;
public class Assignment6B //Soham Nagaokar
{
	public static void main( String [] args ) //Program reads a binary file and displays the data
	{
		DataInputStream in = null;
		PrintWriter out = null;
		Data [] stateArray = new Data[56]; //stateArray that will hold the data for each state is intialized
		try
		{
			in = new DataInputStream(
			     new BufferedInputStream(
			     new FileInputStream( "OrganizedPovertyData.dat" ))); //program is connected to buffered binary input stream
			for( int f = 0; f < stateArray.length; f++ )
			{
				stateArray[f] = new Data(); 
				stateArray[f].setStateCode( in.readInt() ); 
				stateArray[f].setPopulation( in.readInt() );
				stateArray[f].setChildPopulation( in.readInt() );
				stateArray[f].setChildPovertyPopulation( in.readInt() ); 
				stateArray[f].setPercentPoverty( in.readDouble() );
			}
			in.close(); 
		}
		catch( Exception e ) {System.out.println( "Error occured while reading." );} 
		finally {System.out.println( "Program finished reading." );} 
		try
		{
			out = new PrintWriter(
			      new BufferedWriter(
			      new FileWriter( "Report.txt" ))); //connects OrganizedPovertyData to buffer stream
			//the following statement displays some headers for the data
			out.println( "State Code" + "\t" + "Total Population" + "\t" + "Child Population" + "\t" + "Child Poverty Population" + "\t" + "Percent Child Poverty" );
			for( int g = 0; g < stateArray.length; g++ ) //prints data to txt file
			{
				out.printf("%-3d \t\t %-22d %-23d %-31d %-1.2f", stateArray[g].getStateCode(), stateArray[g].getPopulation(), stateArray[g].getChildPopulation(), stateArray[g].getChildPovertyPopulation(), ( stateArray[g].getPercentPoverty() * 100 ) );
				out.println("%" + "\n");
			}
			out.close();
		}
		catch( Exception e ) {System.out.println( "Error occured while writing file." );}
		finally {System.out.println( "Program finished writing." );} 
	}
}