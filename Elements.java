import java.util.*;
import java.io.*;

/**
  * Element details in Java.
  * 
  * @author www.github.com/mqe
  * @version 1.1
  */

public class Elements
{
  // instance variables
  private Map<String, List<String>> elementMap;
   
  /**
    * Constructor for objects
    */
  public Elements()
  {  
    // initialise instance variables
    this.elementMap = new HashMap<String, List<String>>();
  }
   
  /**
    * Method for populating map with data
    */
  private void populateMap()
  { 
    // reader
    String csvFile = "data.csv";
    BufferedReader br = null;
    String line = "";

    try
    {
      // read data from csv
      br = new BufferedReader(new FileReader(csvFile));
      while ((line = br.readLine()) != null)
      {

        // split lines
        String[] elementData = line.split(",");

        // populate map with element data
        this.elementMap.put(elementData[2], new ArrayList<String>());
        this.elementMap.get(elementData[2]).add(elementData[1]);
        this.elementMap.get(elementData[2]).add(elementData[0]);
        this.elementMap.get(elementData[2]).add(elementData[3]);
      }
    }

    // exceptions
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    } 
    catch (IOException e) 
    {
      e.printStackTrace();
    } 

    // close reader
    finally {
      if (br != null) 
      {
        try 
        {
          br.close();
        } 
        catch (IOException e) 
        {
          e.printStackTrace();
        }
      }
    }
  }

  /**
    * Method for printing element data from map.
    * 
    * @param aKey - symbol of element to print
    * @param aFormat - format for table
    * @param aString - string for 'not found'
    */
  private void printElementData(String aKey, String aFormat, StringBuilder aString)
  {
    // print all data in map
    if (aKey.equals("all") || aKey.equals("All"))
    {
      // print data for all elements in map
      for (Map.Entry<String, List<String>> element : elementMap.entrySet())
      { 
        // define map variables
        String key = element.getKey();
        List<String> tempList = element.getValue();

        // print element data
        System.out.printf(aFormat, key, tempList.get(0), tempList.get(1), tempList.get(2));
      }
    }
    else
    {
      // print specific element data
      if (this.elementMap.get(aKey) != null)
      {
        // create temporary list for element data
        List<String> tempList = this.elementMap.get(aKey);
         
        // print element data
        System.out.printf(aFormat, aKey, tempList.get(1), tempList.get(0), tempList.get(2));
      }
      else
      {
        // add input key to 'not found' string
        aString.append(aKey);
        aString.append(" ");
      }
    }
  }
   
  /**
    * Method for printing table.
    * 
    * @param aMap - map with element data
    * @param args[] - list of arguments from input
    */
  private static void printElementTable(Elements aMap, String args[])
  {
    // create new StringBuilder for 'not found' string
    StringBuilder notFound = new StringBuilder("Elements not found: ");

    // format and separator for print
    String format = "%-15s%-15s%-15s%-15s\n";
    String separator = new String(new char[60]).replace('\0', '-');

    // print the table header
    System.out.println("");
    System.out.printf(format, "Symbol", "Name", "Number", "Weight");
    System.out.println(separator);

    // print data from map
    for (String input: args) {
      aMap.printElementData(input, format, notFound);
    }

    // convert StringBuilder to string
    String elementsNotFound = notFound.toString();

    // print the table footer
    System.out.println("");
    if (!elementsNotFound.equals("Elements not found: "))
    {
      System.out.println(elementsNotFound);
      System.out.println("");
    }
    System.out.println(separator);
    System.out.println("Powered by github.com/mqe");
    System.out.println("");
  }

  /* init */
  public static void main(String args[])
  {
    // create new map object
    Elements aMap = new Elements();

    // populate map with data
    aMap.populateMap();

    // print table
    printElementTable(aMap, args);
  }
}
