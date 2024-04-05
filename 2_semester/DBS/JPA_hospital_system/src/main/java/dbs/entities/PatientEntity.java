package dbs.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "patient", schema = "public", catalog = "bondakos")
public class PatientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cislo_zdravotni_pojistovny")
    private long cisloZdravotniPojistovny;
    @Basic
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "surname")
    private String surname;
    @Basic
    @Column(name = "adress_id")
    private int adressId;
//    private Collection<MedicalRecordEntity> medicalRecordsByCisloZdravotniPojistovny;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cislo_zdravotni_pojistovny")
    public long getCisloZdravotniPojistovny() {
        return cisloZdravotniPojistovny;
    }

    public void setCisloZdravotniPojistovny(long cisloZdravotniPojistovny) {
        this.cisloZdravotniPojistovny = cisloZdravotniPojistovny;
    }

    @Basic
    @Column(name = "date_of_birth")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth2) {
        this.dateOfBirth = dateOfBirth2;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "adress_id")
    public int getAdressId() {
        return adressId;
    }

    public void setAdressId(int adressId) {
        this.adressId = adressId;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        PatientEntity that = (PatientEntity) o;
//
//        if (adressId != that.adressId) return false;
//        if (cisloZdravotniPojistovny != null ? !cisloZdravotniPojistovny.equals(that.cisloZdravotniPojistovny) : that.cisloZdravotniPojistovny != null)
//            return false;
//        if (dateOfBitrh != null ? !dateOfBitrh.equals(that.dateOfBitrh) : that.dateOfBitrh != null) return false;
//        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
//        if (email != null ? !email.equals(that.email) : that.email != null) return false;
//        if (name != null ? !name.equals(that.name) : that.name != null) return false;
//        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
//
//        return true;
//    }

//    @Override
//    public int hashCode() {
//        int result = cisloZdravotniPojistovny != null ? cisloZdravotniPojistovny.hashCode() : 0;
//        result = 31 * result + (dateOfBitrh != null ? dateOfBitrh.hashCode() : 0);
//        result = 31 * result + (phone != null ? phone.hashCode() : 0);
//        result = 31 * result + (email != null ? email.hashCode() : 0);
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (surname != null ? surname.hashCode() : 0);
//        result = 31 * result + adressId;
//        return result;
//    }

//    @OneToMany(mappedBy = "patientByCisloZdravotniPojistovnyPatient")
//    public Collection<MedicalRecordEntity> getMedicalRecordsByCisloZdravotniPojistovny() {
//        return medicalRecordsByCisloZdravotniPojistovny;
//    }
//
//    public void setMedicalRecordsByCisloZdravotniPojistovny(Collection<MedicalRecordEntity> medicalRecordsByCisloZdravotniPojistovny) {
//        this.medicalRecordsByCisloZdravotniPojistovny = medicalRecordsByCisloZdravotniPojistovny;
//    }
}
