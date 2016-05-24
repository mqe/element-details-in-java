// create and populate map
Elements aMap = new Elements();
aMap.populateMap();

// print table for values
aMap.printMap();

// print table for specific value
aMap.printMapValue("In");

// add map entry and replace existing entry
aMap.addMapEntry("Te", "1 Test 1.111");
aMap.addMapEntry("Be", "4 Berytest 9.999");

// delete map entry
aMap.deleteEntry("Te");

// create reduced map based on argument
aMap.createReducedMap(45);

// add value to element
aMap.addValue("Te", "New Test Value");

// delete value to element
aMap.deleteValue("Te", 3);

// create and populate map, then add new entry, then add new value to entry, then delete value
aMap.populateMap();
aMap.addMapEntry("Te", "1 Test 1.111");
aMap.addValue("Te", "New Test Value");
aMap.deleteValue("Te", 2);