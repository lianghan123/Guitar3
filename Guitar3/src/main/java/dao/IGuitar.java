package dao;

import java.util.List;

import model.*;

public interface IGuitar {
	public List<Guitar> getAllGuitar();
	void addGuitar(Guitar guitar);

	void deleteGuitar(String serialNumber);

}
