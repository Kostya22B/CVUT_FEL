package dbs.entities;

import jakarta.persistence.*;

//import java.util.Collection;

@Entity
@Table(name = "hospital", schema = "public", catalog = "bondakos")
public class HospitalEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "hospital_id")
    private long hospitalId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "web_site")
    private String webSite;
    @Basic
    @Column(name = "adress_id")
    private long adressId;
    @OneToMany(mappedBy = "hospitalByHospitalId")
    public long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(long hospitalId) {
        this.hospitalId = hospitalId;
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
    @Column(name = "web_site")
    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    @Basic
    @Column(name = "adress_id")
    public long getAdressId() {
        return adressId;
    }

    public void setAdressId(long adressId) {
        this.adressId = adressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HospitalEntity that = (HospitalEntity) o;

        if (hospitalId != that.hospitalId) return false;
        if (adressId != that.adressId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (webSite != null ? !webSite.equals(that.webSite) : that.webSite != null) return false;

        return true;
    }

//    @Override
//    public int hashCode() {
//        long result = hospitalId;
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (webSite != null ? webSite.hashCode() : 0);
//        result = 31 * result + adressId;
//        return result;
//    }
//  private Collection<AssistantEntity> assistantsByHospitalId;
//  private Collection<DoctorEntity> doctorsByHospitalId;
//    public Collection<AssistantEntity> getAssistantsByHospitalId() {
//        return assistantsByHospitalId;
//    }
//
//    public void setAssistantsByHospitalId(Collection<AssistantEntity> assistantsByHospitalId) {
//        this.assistantsByHospitalId = assistantsByHospitalId;
//    }
//
//    @OneToMany(mappedBy = "hospitalByHospitalId")
//    public Collection<DoctorEntity> getDoctorsByHospitalId() {
//        return doctorsByHospitalId;
//    }
//
//    public void setDoctorsByHospitalId(Collection<DoctorEntity> doctorsByHospitalId) {
//        this.doctorsByHospitalId = doctorsByHospitalId;
//    }
}
