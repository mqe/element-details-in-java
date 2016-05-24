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
  private Map<Integer, List<String>> elementMap;
   
  /**
    * Constructor for objects
    */
  public Elements()
  {  
    // initialise instance variables
    this.elementMap = new HashMap<Integer, List<String>>();
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

        // initialise element key as integer
        int mapKey = Integer.parseInt(elementData[0]);

        // populate map with element data
        this.elementMap.put(mapKey, new ArrayList<String>());
        this.elementMap.get(mapKey).add(elementData[1]);
        this.elementMap.get(mapKey).add(elementData[2]);
        this.elementMap.get(mapKey).add(elementData[3]);
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
    // local variables
    boolean foundElement = false;
    SortedSet<Integer> allElements = new TreeSet<Integer>(elementMap.keySet());

    // print all data in map
    if (aKey.equals("all") || aKey.equals("All"))
    {
      for (Integer element : allElements)
      { 
        // define element data as list
        List<String> elementData = elementMap.get(element);

        // print element data
        System.out.printf(aFormat, elementData.get(1), elementData.get(0), element, elementData.get(2));
      }
    }
    // find specific element in map
    else
    {
      for (Integer element : allElements)
      {  
        // define element data as list
        List<String> elementData = elementMap.get(element);

        // define element data as list
        if (elementData.get(1).equals(aKey))
        {
          System.out.printf(aFormat, elementData.get(1), elementData.get(0), element, elementData.get(2));
          foundElement = true;
        }
      }

      // add input to 'not found' string
      if (foundElement != true)
      {
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
    if (!elementsNotFound.equals("Elements not found: "))
    {
      System.out.println("");
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
