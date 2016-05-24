import java.util.*;

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
    // create key and new list for "Beryllium"
    this.elementMap.put("Be", new ArrayList<String>());
    this.elementMap.get("Be").add("4");
    this.elementMap.get("Be").add("Beryllium");
    this.elementMap.get("Be").add("9.012");
      
    // create key and new list for "Magnesium"
    this.elementMap.put("Mg", new ArrayList<String>());
    this.elementMap.get("Mg").add("12");
    this.elementMap.get("Mg").add("Magnesium");
    this.elementMap.get("Mg").add("24.305");
      
    // create key and new list for "Sodium"
    this.elementMap.put("Na", new ArrayList<String>());
    this.elementMap.get("Na").add("11");
    this.elementMap.get("Na").add("Sodium");
    this.elementMap.get("Na").add("22.980");
      
    // create key and new list for "Indium"
    this.elementMap.put("In", new ArrayList<String>());
    this.elementMap.get("In").add("49");
    this.elementMap.get("In").add("Indium");
    this.elementMap.get("In").add("114.818");
  }
   
  /**
    * Method for printing element data from map.
    * 
    * @param aKey - symbol of element to print
    * @param aFormat - format for table
    * @param aString - string for error messages
    */
  private void printMapValue(String aKey, String aFormat, StringBuilder aString)
  {
    // check if map has key (element symbol)
    if (this.elementMap.get(aKey) != null)
    {
      // create temporary list for element data
      List<String> tempList = this.elementMap.get(aKey);
         
      // print data for element using format
      System.out.printf(aFormat, aKey, tempList.get(1), tempList.get(0), tempList.get(2));
    }
    else
    {
      // add to string
      aString.append(aKey);
      aString.append(" ");
    }
  }
   
  /**
    * Method for adding a new entry to the map.
    * 
    * @param aKey - key as a String representation of Symbol for an element
    * @param aValue - value as a String representation of properties for the element
    */
  public void addMapEntry(String aKey, String aValue)
  {
    // split string into values for list
    String[] newValues;
    newValues = aValue.split(" ");
      
    // check if map has key
    if (this.elementMap.get(aKey) == null)
    {    
      // create key and new list for element
      this.elementMap.put(aKey, new ArrayList<String>());
         
      // add values to list
      this.elementMap.get(aKey).add(newValues[0]);
      this.elementMap.get(aKey).add(newValues[1]);
      this.elementMap.get(aKey).add(newValues[2]);
    }
    else
    {
      // get and overwrite an existing element
      this.elementMap.get(aKey).set(0, newValues[0]);
      this.elementMap.get(aKey).set(1, newValues[1]);
      this.elementMap.get(aKey).set(2, newValues[2]);
    }
  }
   
  /**
    * Method for deleting an entry in map.
    * 
    * @param aKey - key as a String representation of Symbol for an element
    */
  public boolean deleteEntry(String aKey)
  {
    // check if map has key
    if (this.elementMap.get(aKey) == null)
    {    
      // return false
      return false;
    }
    else
    {
      // remove entry from map and return true
      this.elementMap.remove(aKey);
      return true;
    }
  }

  /* main */
  public static void main(String args[])
  {
    // create and populate map
    Elements aMap = new Elements();
    aMap.populateMap();

    StringBuilder notFound = new StringBuilder("Elements not found: ");

    // variables
    String format = "%-15s%-15s%-15s%-15s\n";
    String separator = new String(new char[60]).replace('\0', '-');

    // print the header for table
    System.out.println("");
    System.out.printf(format, "Symbol", "Name", "Number", "Weight");
    System.out.println(separator);

    // print map values
    for (String s: args) {
      aMap.printMapValue(s, format, notFound);
    }

    // print the footer for table
    System.out.println("");
    System.out.println(notFound.toString());
    System.out.println("");
    System.out.println(separator);
    System.out.println("Powered by github.com/mqe");
    System.out.println("");
  }
}
