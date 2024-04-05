package dbs.system;

import dbs.dao.*;
import dbs.entities.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;

public class DoctorSystem {
	public void createDoctor(long hospitalId, String name, String surname, long addressId) {
	    DoctorDao doctorDao = new DoctorDao();
	    DoctorEntity doctor = new DoctorEntity();
	    doctor.setHospitalId(hospitalId);
	    doctor.setName(name);
	    doctor.setSurname(surname);
	    doctor.setAdressId(addressId);

	    doctorDao.save(doctor);
	    System.out.println("Doctor created with parameters: "
	            + "doctorId: " + doctor.getDoctorId()
	            + " hospitalId: " + doctor.getHospitalId()
	            + " name: " + doctor.getName()
	            + " surname: " + doctor.getSurname()
	            + " addressId: " + doctor.getAdressId());
	}
	public void findByIdDoctor(long doctorId) {
	    DoctorDao doctorDao = new DoctorDao();
	    DoctorEntity doctor = doctorDao.get(doctorId);

	    if (doctor != null) {
	        System.out.println("Doctor found with doctorId " + doctorId + ":");
	        System.out.println("HospitalId: " + doctor.getHospitalId());
	        System.out.println("Name: " + doctor.getName());
	        System.out.println("Surname: " + doctor.getSurname());
	        System.out.println("AddressId: " + doctor.getAdressId());
	    } else {
	        System.out.println("Doctor with doctorId " + doctorId + " not found.");
	    }
	}

	public void getAllDoctors() {
	    DoctorDao doctorDao = new DoctorDao();
	    List<DoctorEntity> allDoctors = doctorDao.getAll();
	    System.out.println("All Doctors:");
	    for (DoctorEntity doctor : allDoctors) {
	        System.out.println("Found Doctor: "
	                + "doctorId: " + doctor.getDoctorId()
	                + ". hospitalId: " + doctor.getHospitalId()
	                + ". name: " + doctor.getName()
	                + ". surname: " + doctor.getSurname()
	                + ". addressId: " + doctor.getAdressId());
	    }
	}

	public void updateDoctor(long doctorId, long hospitalId, String name, String surname, long addressId) {
	    DoctorDao doctorDao = new DoctorDao();
	    DoctorEntity doctorToUpdate = doctorDao.get(doctorId);

	    if (doctorToUpdate != null) {
	        doctorToUpdate.setHospitalId(hospitalId);
	        doctorToUpdate.setName(name);
	        doctorToUpdate.setSurname(surname);
	        doctorToUpdate.setAdressId(addressId);

	        doctorDao.update(doctorToUpdate);
	        System.out.println("Doctor with doctorId " + doctorId + " updated successfully!");
	    } else {
	        System.out.println("Doctor with doctorId " + doctorId + " not found.");
	    }
	}
	public void exchangingExperience(long fromId, long toId) {
	    DoctorDao doctorDao = new DoctorDao();
	    
	    DoctorEntity sender = doctorDao.get(fromId);
        DoctorEntity recipient = doctorDao.get(toId);
        if (sender.getDoctorId() == recipient.getDoctorId()) {
            System.out.println("It's same doctor. This function is not needed");
            return;
        }
        long a = sender.getHospitalId();
        sender.setHospitalId(recipient.getHospitalId());
        recipient.setHospitalId(a);

        doctorDao.update(sender);
        doctorDao.update(recipient);

        System.out.println("doctors "+sender.getName()+ " " + sender.getSurname()+
        		" and " + recipient.getName()+ " " + recipient.getSurname() +" exchanged succesfully");
	}
	public void deleteDoctor(long idd) {
    	String url = "";
        String username = "";
        String password = "";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
    	String sql = "DELETE FROM doctor WHERE doctor_id = " + idd + ";";
    	System.out.println(sql);
        Statement statement = connection.createStatement();

        int rowsDeleted = statement.executeUpdate(sql);
        System.out.println("Doctor deleted: " + rowsDeleted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public void AllDoctorsInHospital(long hospid) {
		String url = "";
        String username = "";
        String password = "";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM doctor WHERE hospital_id = " + hospid;
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("All doctors from hospital "+hospid);
            while (resultSet.next()) {
                long doctorId = resultSet.getLong("doctor_id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                long addressId = resultSet.getLong("adress_id");

                // Do something with the retrieved data
                
                System.out.println("Doctor ID: " + doctorId + 
                " Name: " + name +
               " Surname: " + surname +
                " Address ID: " + addressId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
}}