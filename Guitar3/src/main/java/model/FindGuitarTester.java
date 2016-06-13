package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FindGuitarTester {

  public static void main(String[] args) {
    // Set up Rick's inventory
    Inventory inventory = new Inventory();
    initializeInventory(inventory);

    Map properties = new HashMap();
    properties.put("builder", Builder.GIBSON);
    properties.put("backWood", Wood.MAPLE);
    GuitarSpec whatBryanLikes = new GuitarSpec(properties);

    List matchingGuitar = inventory.search(whatBryanLikes);
    if (!matchingGuitar.isEmpty()) {
      System.out.println("Bryan, you might like these instruments:");
      for (Iterator i = matchingGuitar.iterator(); i.hasNext(); ) {
      	Guitar guitar = (Guitar)i.next();
        GuitarSpec spec = guitar.getSpec();
        System.out.println("We have a " + spec.getProperty("instrumentType") +
          " with the following properties:");
        for (Iterator j = spec.getProperties().keySet().iterator(); 
             j.hasNext(); ) {
          String propertyName = (String)j.next();
          if (propertyName.equals("instrumentType"))
            continue;
          System.out.println("    " + propertyName + ": " +
            spec.getProperty(propertyName));
        }  
        System.out.println("  You can have this " + 
          spec.getProperty("instrumentType") + " for $" + 
          guitar.getPrice() + "\n---");
      }
    } else {
      System.out.println("Sorry, Bryan, we have nothing for you.");
    }
  }

  private static void initializeInventory(Inventory inventory) {
    Map properties = new HashMap();
    properties.put("builder", Builder.COLLINGS);
    properties.put("model", "CJ");
    properties.put("type", Type.ACOUSTIC);
    properties.put("numStrings", 6);
    properties.put("topWood", Wood.INDIAN_ROSEWOOD);
    properties.put("backWood", Wood.SITKA);
    inventory.addGuitar("11277", 3999.95, new GuitarSpec(properties));

    properties.put("builder", Builder.MARTIN);
    properties.put("model", "D-18");
    properties.put("topWood", Wood.MAHOGANY);
    properties.put("backWood", Wood.ADIRONDACK);
    inventory.addGuitar("122784", 5495.95, new GuitarSpec(properties));

    properties.put("builder", Builder.FENDER);
    properties.put("model", "Stratocastor");
    properties.put("type", Type.ELECTRIC);
    properties.put("topWood", Wood.ALDER);
    properties.put("backWood", Wood.ALDER);
    inventory.addGuitar("V95693", 1499.95, new GuitarSpec(properties));
    inventory.addGuitar("V9512", 1549.95, new GuitarSpec(properties));
 
    properties.put("builder", Builder.GIBSON);
    properties.put("model", "Les Paul");
    properties.put("topWood", Wood.MAPLE);
    properties.put("backWood", Wood.MAPLE);
    inventory.addGuitar("70108276", 2295.95, 
      new GuitarSpec(properties));

    properties.put("model", "SG '61 Reissue");
    properties.put("topWood", Wood.MAHOGANY);
    properties.put("backWood", Wood.MAHOGANY);
    inventory.addGuitar("82765501", 1890.95, 
      new GuitarSpec(properties));

    properties.put("type", Type.ACOUSTIC);
    properties.put("model", "F-5G");
    properties.put("backWood", Wood.MAPLE);
    properties.put("topWood", Wood.MAPLE);
    properties.remove("numStrings");
    inventory.addGuitar("9019920", 5495.99, new GuitarSpec(properties));

    properties.put("model", "RB-3 Wreath");
    properties.remove("topWood");
    properties.put("numStrings", 5);
    inventory.addGuitar("8900231", 2945.95, new GuitarSpec(properties));
  }
}
