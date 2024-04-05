package dbs.system;

import dbs.dao.*;
import dbs.entities.*;

import static java.sql.Date.valueOf;

import java.sql.Date;
import java.util.List;

public class PatientSystem {

    public PatientEntity getPatient(long id) {
        PatientDao patientDao = new PatientDao();
        return patientDao.get(id);
    }
    public void createPatient(String name, String surname, Date dateOfBirth, String phone, String email, int adressId) {
        PatientDao patientDao = new PatientDao();
        PatientEntity patient = new PatientEntity();
        
        VenueDao venueDao = new VenueDao();
        VenueEntity venue = venueDao.get(adressId);

        patient.setDateOfBirth(dateOfBirth);
        patient.setPhone(phone);
        patient.setEmail(email);
        patient.setName(name);
        patient.setSurname(surname);
        patient.setAdressId(adressId);

        patientDao.save(patient);
        System.out.println("Patient created with parameters: " + "cislo_zdravotni_pojistovny: " + patient.getCisloZdravotniPojistovny()
                + " date_of_birth: " + patient.getDateOfBirth() + " phone: " + patient.getPhone()
                + " email: " + patient.getEmail() + " name: " + patient.getName()
                + " surname: " + patient.getSurname() + " adress_id: " + patient.getAdressId());
    }

    public void findById(long cisloZdravotniPojistovny) {
        PatientDao patientDao = new PatientDao();
        PatientEntity patient = patientDao.get(cisloZdravotniPojistovny);

        if (patient != null) {
            System.out.println("Patient found with cislo_zdravotni_pojistovny " + cisloZdravotniPojistovny + ":");
            System.out.println("Date of Birth: " + patient.getDateOfBirth());
            System.out.println("Phone: " + patient.getPhone());
            System.out.println("Email: " + patient.getEmail());
            System.out.println("Name: " + patient.getName());
            System.out.println("Surname: " + patient.getSurname());
            System.out.println("Adress ID: " + patient.getAdressId());
        } else {
            System.out.println("Patient with cislo_zdravotni_pojistovny " + cisloZdravotniPojistovny + " not found.");
        }
    }

    public void getAllPatients() {
        PatientDao patientDao = new PatientDao();
        List<PatientEntity> allPatients = patientDao.getAll();
        System.out.println("All Patients:");
        for (PatientEntity patient : allPatients) {
            System.out.println("Found Patient: " + "cislo_zdravotni_pojistovny: " + patient.getCisloZdravotniPojistovny()
                    + ". date_of_birth: " + patient.getDateOfBirth() + ". phone: " + patient.getPhone()
                    + ". email: " + patient.getEmail() + ". name: " + patient.getName()
                    + ". surname: " + patient.getSurname() + ". adress_id: " + patient.getAdressId());
        }
    }

    public void updatePatient(int cisloZdravotniPojistovny, Date date, String phone, String email, String name, String surname, int adressId) {
        PatientDao patientDao = new PatientDao();
        PatientEntity patientToUpdate = patientDao.get(cisloZdravotniPojistovny);

        if (patientToUpdate != null) {
            patientToUpdate.setDateOfBirth(date);
            patientToUpdate.setPhone(phone);
            patientToUpdate.setEmail(email);
            patientToUpdate.setName(name);
            patientToUpdate.setSurname(surname);
            patientToUpdate.setAdressId(adressId);

            patientDao.update(patientToUpdate);
            System.out.println("Patient with cislo_zdravotni_pojistovny " + cisloZdravotniPojistovny + " updated successfully!");
        } else {
            System.out.println("Patient with cislo_zdravotni_pojistovny " + cisloZdravotniPojistovny + " not found.");
        }
    }

//    public void deletePatient(long cisloZdravotniPojistovny) {
//        PatientDao patientDao = new PatientDao();
//        PatientEntity patientToDelete = patientDao.get(cisloZdravotniPojistovny);
//
//        if (patientToDelete != null) {
//            patientDao.delete(patientToDelete);
//            System.out.println("Patient with cislo_zdravotni_pojistovny " + cisloZdravotniPojistovny + " deleted successfully!");
//        } else {
//            System.out.println("Patient with cislo_zdravotni_pojistovny " + cisloZdravotniPojistovny + " not found.");
//        }
//    }

}