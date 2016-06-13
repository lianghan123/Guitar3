package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import dao.IGuitar;
import dao.dataAccess;

public class Inventory {

  private List inventory;

  public Inventory() {
    inventory = new LinkedList();
  }
  private void addGuitar(Guitar guitar) {
		// TODO Auto-generated method stub
		
	}
  public void addGuitar(String serialNumber, double price,
                            GuitarSpec spec) {
    Guitar guitar = new Guitar(serialNumber, price, spec);
    inventory.add(guitar);
  }

  public Guitar get(String serialNumber) {
    for (Iterator i = inventory.iterator(); i.hasNext(); ) {
    	Guitar guitar = (Guitar)i.next();
      if (guitar.getSerialNumber().equals(serialNumber)) {
        return guitar;
      }
    }
    return null;
  }

  public List<Guitar> search(GuitarSpec searchSpec) {
    List matchingGuitar = new LinkedList<Guitar>();
    for (Iterator<Guitar> i = inventory.iterator(); i.hasNext(); ) {
    	Guitar guitar = (Guitar)i.next();
      if (guitar.getSpec().matches(searchSpec))
        matchingGuitar.add(guitar);
    }
    return matchingGuitar;
  }
  public void initialize(){
		IGuitar ig = dataAccess.createGuitarDao();
		List<Guitar> allGuitars = ig.getAllGuitar();
		for (Iterator<Guitar> i = allGuitars.iterator(); i.hasNext();) {
			Guitar guitar = (Guitar) i.next();
			addGuitar(guitar);
}
}
}