//package dbs.entities;
//
//import jakarta.persistence.Basic;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "workers1", schema = "public", catalog = "bondakos")
//public class Workers1Entity {
//    @Basic
//    @Column(name = "hospital_id")
//    private Integer hospitalId;
//    @Basic
//    @Column(name = "doctor_id")
//    private Integer doctorId;
//    @Basic
//    @Column(name = "name")
//    private String name;
//    @Basic
//    @Column(name = "surname")
//    private String surname;
//    @Basic
//    @Column(name = "adress_id")
//    private Integer adressId;
//
//    public Integer getHospitalId() {
//        return hospitalId;
//    }
//
//    public void setHospitalId(Integer hospitalId) {
//        this.hospitalId = hospitalId;
//    }
//
//    public Integer getDoctorId() {
//        return doctorId;
//    }
//
//    public void setDoctorId(Integer doctorId) {
//        this.doctorId = doctorId;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSurname() {
//        return surname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }
//
//    public Integer getAdressId() {
//        return adressId;
//    }
//
//    public void setAdressId(Integer adressId) {
//        this.adressId = adressId;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Workers1Entity that = (Workers1Entity) o;
//
//        if (hospitalId != null ? !hospitalId.equals(that.hospitalId) : that.hospitalId != null) return false;
//        if (doctorId != null ? !doctorId.equals(that.doctorId) : that.doctorId != null) return false;
//        if (name != null ? !name.equals(that.name) : that.name != null) return false;
//        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
//        if (adressId != null ? !adressId.equals(that.adressId) : that.adressId != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = hospitalId != null ? hospitalId.hashCode() : 0;
//        result = 31 * result + (doctorId != null ? doctorId.hashCode() : 0);
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (surname != null ? surname.hashCode() : 0);
//        result = 31 * result + (adressId != null ? adressId.hashCode() : 0);
//        return result;
//    }
//}
