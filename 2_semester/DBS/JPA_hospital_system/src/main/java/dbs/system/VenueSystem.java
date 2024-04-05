package dbs.system;

import java.util.List;

import dbs.dao.VenueDao;
import dbs.entities.VenueEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class VenueSystem {
	// readById
	public VenueEntity getVenue(long id) {
       VenueDao venueDao = new VenueDao();
       return venueDao.get(id);
    }
	//create
    public void createVenue(String city, String street, int postcode) {
        VenueDao venueDao = new VenueDao();
        VenueEntity venue = new VenueEntity();
        venue.setCity(city);
        venue.setStreet(street);
        venue.setPostcode(postcode);

        venueDao.save(venue);
        System.out.println("Venue created with parameters: " + "city: "+ venue.getCity()
        + " street: " + venue.getStreet() + " postcode: " + venue.getPostcode());
    }
    //readById
    public void findById(int venueId) {
        VenueDao venueDao = new VenueDao();
        VenueEntity venue = venueDao.get(venueId);

        if (venue != null) {
            System.out.println("Venue found with ID " + venueId + ":"+
"City: " + venue.getCity()+
"Street: " + venue.getStreet()+
"Postcode: " + venue.getPostcode());
        } else {
            System.out.println("Venue with ID " + venueId + " not found.");
        }
    }

    // readAll
    public void getAll() {
    VenueDao venueDao = new VenueDao();
    List<VenueEntity> allVenues = venueDao.getAll();
    System.out.println("All Doctors:");
    for (VenueEntity venue1 : allVenues) {
      System.out.println("Found Venue: "+ " adress_id: " +venue1.getAdressId() + ". city: " 
    		  + venue1.getCity() + ". street: "
       +venue1.getStreet() + " postcode: "+ venue1.getPostcode());
    	}
    }
    //update
    public void updateVenue(int venueId, String city, String street, int postcode) {
        VenueDao venueDao = new VenueDao();
        VenueEntity venueToUpdate = venueDao.get(venueId);

        if (venueToUpdate != null) {
            venueToUpdate.setCity(city);
            venueToUpdate.setStreet(street);
            venueToUpdate.setPostcode(postcode);

            venueDao.update(venueToUpdate);
            System.out.println("Venue with ID " + venueId + " updated successfully!");
        } else {
            System.out.println("Venue with ID " + venueId + " not found.");
        }
    }
    public void deleteVenue1(int venueId) {
        VenueDao venueDao = new VenueDao();
        VenueEntity venueToDelete = venueDao.get(venueId);
        if (venueToDelete != null) {
            venueDao.deleteById(venueId);
            System.out.println("Venue with ID " + venueId + " deleted successfully!");
        } else {
            System.out.println("Venue with ID " + venueId + " not found.");
        }
    }
    public void deleteVenue(long idd) {
    	String url = "";
        String username = "";
        String password = "";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
    	String sql = "DELETE FROM venue WHERE adress_id = " + idd + ";";
        Statement statement = connection.createStatement();

        int rowsDeleted = statement.executeUpdate(sql);
        System.out.println("Venue deleted with id " + idd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

