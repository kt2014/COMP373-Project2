package model.facility;

import java.util.*;


public interface Groups extends Facility {
	public List<Facility> listFacility();
	public Facility addNewFacility(Facility facility);
	public void removeFacility(int id);
	public List<Facility> requestAvailFacility();
}
