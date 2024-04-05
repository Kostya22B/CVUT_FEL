package dbs.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "venue", schema = "public", catalog = "bondakos")
public class VenueEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "adress_id")
    private long adressId;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "street")
    private String street;
    @Basic
    @Column(name = "postcode")
    private int postcode;
//    private Collection<AssistantEntity> assistantsByAdressId;
//    private Collection<DoctorEntity> doctorsByAdressId;
//    private Collection<HospitalEntity> hospitalsByAdressId;
//    private Collection<PatientEntity> patientsByAdressId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "adress_id")
    public long getAdressId() {
        return adressId;
    }

    public void setAdressId(long adressId) {
        this.adressId = adressId;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "postcode")
    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VenueEntity that = (VenueEntity) o;

        if (adressId != that.adressId) return false;
        if (postcode != that.postcode) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;

        return true;
    }

//    @Override
//    public int hashCode() {
//        long result = adressId;
//        result = 31 * result + (city != null ? city.hashCode() : 0);
//        result = 31 * result + (street != null ? street.hashCode() : 0);
//        result = 31 * result + postcode;
//        return result;
//    }

//    @OneToMany(mappedBy = "venueByAdressId")
//    public Collection<AssistantEntity> getAssistantsByAdressId() {
//        return assistantsByAdressId;
//    }

//    public void setAssistantsByAdressId(Collection<AssistantEntity> assistantsByAdressId) {
//        this.assistantsByAdressId = assistantsByAdressId;
//    }
//
//    @OneToMany(mappedBy = "venueByAdressId")
//    public Collection<DoctorEntity> getDoctorsByAdressId() {
//        return doctorsByAdressId;
//    }
//
//    public void setDoctorsByAdressId(Collection<DoctorEntity> doctorsByAdressId) {
//        this.doctorsByAdressId = doctorsByAdressId;
//    }
//
//    @OneToMany(mappedBy = "venueByAdressId")
//    public Collection<HospitalEntity> getHospitalsByAdressId() {
//        return hospitalsByAdressId;
//    }
//
//    public void setHospitalsByAdressId(Collection<HospitalEntity> hospitalsByAdressId) {
//        this.hospitalsByAdressId = hospitalsByAdressId;
//    }
//
//    @OneToMany(mappedBy = "venueByAdressId")
//    public Collection<PatientEntity> getPatientsByAdressId() {
//        return patientsByAdressId;
//    }
//
//    public void setPatientsByAdressId(Collection<PatientEntity> patientsByAdressId) {
//        this.patientsByAdressId = patientsByAdressId;
//    }
}
