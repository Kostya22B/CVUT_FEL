package dbs.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "operation_room", schema = "public", catalog = "bondakos")
public class OperationRoomEntity {
    @Basic
    @Column(name = "hospital_id")
    private int hospitalId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "room_id")
    private int roomId;
    @Basic
    @Column(name = "room_number")
    private int roomNumber;
//    private Collection<OperationEntity> operationsByRoomId;
//    private HospitalEntity hospitalByHospitalId;

    @Basic
    @Column(name = "hospital_id")
    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "room_id")
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Basic
    @Column(name = "room_number")
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperationRoomEntity that = (OperationRoomEntity) o;

        if (hospitalId != that.hospitalId) return false;
        if (roomId != that.roomId) return false;
        if (roomNumber != that.roomNumber) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hospitalId;
        result = 31 * result + roomId;
        result = 31 * result + roomNumber;
        return result;
    }

//    @OneToMany(mappedBy = "operationRoomByRoomId")
//    public Collection<OperationEntity> getOperationsByRoomId() {
//        return operationsByRoomId;
//    }
//
//    public void setOperationsByRoomId(Collection<OperationEntity> operationsByRoomId) {
//        this.operationsByRoomId = operationsByRoomId;
//    }
//
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
