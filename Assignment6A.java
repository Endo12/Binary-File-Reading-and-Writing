import java.io.*;
import java.util.Scanner;
public class Assignment6A //Soham Nagaokar
{
	public static void main ( String [] args ) //The program will read certain data from a text document and copy the data to a binary file
	{
		Data [] dataArray = new Data[13486]; //creates an array of type Data that will hold the pertinent data 
		BufferedReader br = null; 
		DataOutputStream out = null; 
		String line; 
		try
		{
			br = new BufferedReader(
			     new FileReader( "PovertyData.txt" )); //connects PovertyData to the buffer stream
			int f = 0;
			while( ( line = br.readLine() ) != null ) //as long as there is data
			{
				dataArray[f] = new Data(); 
				Scanner stringtoInt = new Scanner( line.substring(0, 3) ); //State code data
				Scanner stringtoInt2 = new Scanner( line.substring(83, 91) ); //Population data
				Scanner stringtoInt3 = new Scanner( line.substring(92, 100) ); //Child population data
				Scanner stringtoInt4 = new Scanner( line.substring(101, 109) ); //Child poverty population data
				dataArray[f].setStateCode( stringtoInt.nextInt() ); 
				dataArray[f].setPopulation( stringtoInt2.nextInt() ); 
				dataArray[f].setChildPopulation( stringtoInt3.nextInt() );
				dataArray[f].setChildPovertyPopulation( stringtoInt4.nextInt() ); 
				f++;
			}
			br.close();
		}
		catch( Exception e ) {System.out.println( "Error occured while reading file." );} 
		finally {System.out.println( "Program finished reading." );} 
		Data [] stateArray = new Data[56]; //creates a new Data array for each state as a whole
		for( int a = 1; a < 57; a++ ) //the loop is repeated for each state code
		{
			stateArray[a-1] = new Data(); 
			stateArray[a-1].setStateCode(a);
			for( int b = 0; b < dataArray.length; b++ ) //the loop is repeated for each member of dataArray
			{
				if( dataArray[b].getStateCode() == a ) /*if the state code of the dataArray member matches the current state code that the first loop is
				testing, then the data member of the stateArray takes the values of the dataArray and adds it to its own*/
				{
					stateArray[a-1].setPopulation( stateArray[a-1].getPopulation() + dataArray[b].getPopulation() );
					stateArray[a-1].setChildPopulation( stateArray[a-1].getChildPopulation() + dataArray[b].getChildPopulation() );
					stateArray[a-1].setChildPovertyPopulation( stateArray[a-1].getChildPovertyPopulation() + dataArray[b].getChildPovertyPopulation() );
				}
			}
			if( stateArray[a-1].getChildPopulation() != 0 ) /*checks to make sure that the state does indeed exist. Certain state codes, such as 3 and 7, do
			not have data, and so the following statement would produce a divide by 0 error. This check will prevent that, as an exsistent state will have a
			value for child population.*/
			{
				stateArray[a-1].setPercentPoverty( (double) stateArray[a-1].getChildPovertyPopulation() / (double) stateArray[a-1].getChildPopulation() );
			}
		}
		try
		{
			out = new DataOutputStream(
			      new BufferedOutputStream(
			      new FileOutputStream( "OrganizedPovertyData.dat" ))); //Connects file to buffered binary output stream
			for( int g = 0; g < stateArray.length; g++ ) //copies data to dat file
			{
				out.writeInt( stateArray[g].getStateCode() ); 
				out.writeInt( stateArray[g].getPopulation() ); 
				out.writeInt( stateArray[g].getChildPopulation() ); 
				out.writeInt( stateArray[g].getChildPovertyPopulation() ); 
				out.writeDouble( stateArray[g].getPercentPoverty() );
			}
			out.close(); 
		}
		catch( Exception e ) {System.out.println( "Error occured while writing file." );} 
		finally
		{
			System.out.println( "Program finished writing." ); 
			System.out.println( out.size() + " bytes written." );
		}
	}
}