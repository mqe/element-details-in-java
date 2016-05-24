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
  private void populateMap(String aFile)
  { 
    // reader
    // String csvFile = "data.csv";
    String csvFile = aFile;
    BufferedReader aReader = null;
    String line = "";

    try
    {
      // read data from csv
      aReader = new BufferedReader(new FileReader(csvFile));
      while ((line = aReader.readLine()) != null)
      {

        // split lines
        String[] elementData = line.split(";");

        // initialise element key as integer
        int mapKey = Integer.parseInt(elementData[0]);

        // initialise other element properties
        String elementName = elementData[1];
        String elementSymbol = elementData[2];
        String elementWeight = elementData[3];
        String elementPhase = elementData[6];
        String elementCategory = elementData[8];
        String elementEnergy = elementData[12];
        String elementDiscoveredBy = elementData[17];

        // populate map with element data
        this.elementMap.put(mapKey, new ArrayList<String>());
        this.elementMap.get(mapKey).add(elementSymbol);
        this.elementMap.get(mapKey).add(elementName);
        this.elementMap.get(mapKey).add(elementWeight);
        this.elementMap.get(mapKey).add(elementEnergy);
        this.elementMap.get(mapKey).add(elementPhase);
        this.elementMap.get(mapKey).add(elementCategory);
        // this.elementMap.get(mapKey).add(elementDiscoveredBy);
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
      if (aReader != null) 
      {
        try 
        {
          aReader.close();
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
        System.out.printf(aFormat, element, elementData.get(0), elementData.get(1), elementData.get(2), elementData.get(3), elementData.get(4), elementData.get(5));
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
        if (elementData.get(0).equals(aKey))
        {
          System.out.printf(aFormat, element, elementData.get(0), elementData.get(1), elementData.get(2), elementData.get(3), elementData.get(4), elementData.get(5));
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
    String format = "%-8s%-8s%-16s%-16s%-16s%-16s%-24s\n";
    String separator = new String(new char[104]).replace('\0', '-');

    // print the table header
    System.out.println("");
    System.out.printf(format, "Num.", "Sym.", "Name", "Weight", "eV", "Phase", "Category");
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

    // create a scanner and get path to file
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter path to file (eg. data/data.csv): ");
    String aFile = scanner.next();

    // populate map with data from file
    aMap.populateMap(aFile);

    // print table
    printElementTable(aMap, args);
  }
}
