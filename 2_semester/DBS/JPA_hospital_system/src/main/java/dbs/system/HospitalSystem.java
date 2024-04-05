package dbs.system;

import java.util.List;

import dbs.dao.VenueDao;
import dbs.dao.HospitalDao;
import dbs.entities.VenueEntity;
import dbs.entities.HospitalEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HospitalSystem {
	public HospitalEntity getHospital(long id) {
        HospitalDao hospitalDao = new HospitalDao();
        return hospitalDao.get(id);
    }

    public void createHospital(String name, String website, long addressId) {
        HospitalDao hospitalDao = new HospitalDao();
        HospitalEntity hospital = new HospitalEntity();
        hospital.setName(name);
        hospital.setWebSite(website);
        hospital.setAdressId(addressId);

        hospitalDao.save(hospital);
        System.out.println("Hospital created with parameters: " +
                "name: " + hospital.getName() +
                " website: " + hospital.getWebSite() +
                " addressId: " + hospital.getAdressId());
    }

    public void findById(long hospitalId) {
        HospitalDao hospitalDao = new HospitalDao();
        HospitalEntity hospital = hospitalDao.get(hospitalId);

        if (hospital != null) {
            System.out.println("Hospital found with ID " + hospitalId + ":" +
                    "Name: " + hospital.getName() +
                    "Website: " + hospital.getWebSite() +
                    "Address ID: " + hospital.getAdressId());
        } else {
            System.out.println("Hospital with ID " + hospitalId + " not found.");
        }
    }

    public void getAll() {
        HospitalDao hospitalDao = new HospitalDao();
        List<HospitalEntity> allHospitals = hospitalDao.getAll();
        System.out.println("All Hospitals:");
        for (HospitalEntity hospital : allHospitals) {
            System.out.println("Found Hospital: " +
                    "addressId: " + hospital.getAdressId() +
                    ". name: " + hospital.getName() +
                    ". website: " + hospital.getWebSite());
        }
    }

    public void updateHospital(int hospitalId, String name, String website, long addressId) {
        HospitalDao hospitalDao = new HospitalDao();
        HospitalEntity hospitalToUpdate = hospitalDao.get(hospitalId);

        if (hospitalToUpdate != null) {
            hospitalToUpdate.setName(name);
            hospitalToUpdate.setWebSite(website);
            hospitalToUpdate.setAdressId(addressId);

            hospitalDao.update(hospitalToUpdate);
            System.out.println("Hospital with ID " + hospitalId + " updated successfully!");
        } else {
            System.out.println("Hospital with ID " + hospitalId + " not found.");
        }
    }

    public void deleteHospital(long id) {
        String url = "";
        String username = "";
        String password = "";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "DELETE FROM hospital WHERE hospital_id = " + id + ";";
            Statement statement = connection.createStatement();

            int rowsDeleted = statement.executeUpdate(sql);
            System.out.println("Hospital deleted with ID " + id);
        } catch (SQLException e) {
            System.out.println("No hospital with that id");
        }
    }
    public void showAddressesOfHospitals() {
        String url = "";
        String username = "";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT hospital.name, hospital.web_site, hospital.adress_id, venue.* "
                    + "FROM hospital "
                    + "LEFT OUTER JOIN venue ON hospital.adress_id = venue.adress_id;";
            System.out.println(sql);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("All hospitals with their addresses from venue ");
            while (resultSet.next()) {
                String hospitalName = resultSet.getString("name");
                String hospitalWebsite = resultSet.getString("web_site");
                long adressId = resultSet.getLong("adress_id");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                int postcode = resultSet.getInt("postcode");
                // Do something with the retrieved data

                System.out.println("Hospital Name: " + hospitalName +
                        " Web-site: " + hospitalWebsite +
                        " Address ID: " + adressId +
                        " City: " + city +
                        " Street: " + street +
                        " Postcode: " + postcode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
