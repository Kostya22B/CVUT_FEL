package dbs.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "doctor", schema = "public", catalog = "bondakos")
public class DoctorEntity {

    @Id
    @Column(name = "doctor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long doctorId;

    @Basic
    @Column(name = "hospital_id")
    private long hospitalId;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "surname")
    private String surname;

    @Basic
    @Column(name = "adress_id")
    private long adressId;

    public DoctorEntity(long hospitalId, String name, String surname, long adressId) {
        this.hospitalId = hospitalId;
        this.name = name;
        this.surname = surname;
        this.adressId = adressId;
    }

    public DoctorEntity() {}

    public long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getAdressId() {
        return adressId;
    }

    public void setAdressId(long adressId) {
        this.adressId = adressId;
    }
}
