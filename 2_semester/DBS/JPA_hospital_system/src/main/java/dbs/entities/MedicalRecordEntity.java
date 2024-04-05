package dbs.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "medical_record", schema = "public", catalog = "bondakos")
public class MedicalRecordEntity {
    @Basic
    @Column(name = "hospital_id")
    private int hospitalId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "code")
    private int code;
    @Basic
    @Column(name = "cislo_zdravotni_pojistovny_patient")
    private String cisloZdravotniPojistovnyPatient;
    @Basic
    @Column(name = "date_time")
    private Timestamp dateTime;
//    private HospitalEntity hospitalByHospitalId;

    @Basic
    @Column(name = "hospital_id")
    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Basic
    @Column(name = "cislo_zdravotni_pojistovny_patient")
    public String getCisloZdravotniPojistovnyPatient() {
        return cisloZdravotniPojistovnyPatient;
    }

    public void setCisloZdravotniPojistovnyPatient(String cisloZdravotniPojistovnyPatient) {
        this.cisloZdravotniPojistovnyPatient = cisloZdravotniPojistovnyPatient;
    }

    @Basic
    @Column(name = "date_time")
    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicalRecordEntity that = (MedicalRecordEntity) o;

        if (hospitalId != that.hospitalId) return false;
        if (code != that.code) return false;
        if (cisloZdravotniPojistovnyPatient != null ? !cisloZdravotniPojistovnyPatient.equals(that.cisloZdravotniPojistovnyPatient) : that.cisloZdravotniPojistovnyPatient != null)
            return false;
        if (dateTime != null ? !dateTime.equals(that.dateTime) : that.dateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hospitalId;
        result = 31 * result + code;
        result = 31 * result + (cisloZdravotniPojistovnyPatient != null ? cisloZdravotniPojistovnyPatient.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        return result;
    }

//    @ManyToOne
//    @JoinColumn(name = "hospital_id", referencedColumnName = "hospital_id", nullable = false)
//    public HospitalEntity getHospitalByHospitalId() {
//        return hospitalByHospitalId;
//    }
//
//    public void setHospitalByHospitalId(HospitalEntity hospitalByHospitalId) {
//        this.hospitalByHospitalId = hospitalByHospitalId;
//    }
}
