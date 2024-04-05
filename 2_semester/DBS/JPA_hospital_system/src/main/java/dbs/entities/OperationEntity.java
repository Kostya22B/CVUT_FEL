package dbs.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "operation", schema = "public", catalog = "bondakos")
public class OperationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "operation_id")
    private int operationId;
    @Basic
    @Column(name = "record_code")
    private int recordCode;
    @Basic
    @Column(name = "doctor_id")
    private int doctorId;
    @Basic
    @Column(name = "room_id")
    private int roomId;
    @Basic
    @Column(name = "assistant_id")
    private Integer assistantId;
//    private DoctorEntity doctorByDoctorId;
//    private AssistantEntity assistantByAssistantId;

    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }

    @Basic
    @Column(name = "record_code")
    public int getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(int recordCode) {
        this.recordCode = recordCode;
    }

    @Basic
    @Column(name = "doctor_id")
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    @Basic
    @Column(name = "room_id")
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Basic
    @Column(name = "assistant_id")
    public Integer getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(Integer assistantId) {
        this.assistantId = assistantId;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperationEntity that = (OperationEntity) o;

        if (operationId != that.operationId) return false;
        if (recordCode != that.recordCode) return false;
        if (doctorId != that.doctorId) return false;
        if (roomId != that.roomId) return false;
        if (assistantId != null ? !assistantId.equals(that.assistantId) : that.assistantId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = operationId;
        result = 31 * result + recordCode;
        result = 31 * result + doctorId;
        result = 31 * result + roomId;
        result = 31 * result + (assistantId != null ? assistantId.hashCode() : 0);
        return result;
    }

//    @ManyToOne
//    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", nullable = false)
//    public DoctorEntity getDoctorByDoctorId() {
//        return doctorByDoctorId;
//    }
//
//    public void setDoctorByDoctorId(DoctorEntity doctorByDoctorId) {
//        this.doctorByDoctorId = doctorByDoctorId;
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "assistant_id", referencedColumnName = "assistant_id")
//    public AssistantEntity getAssistantByAssistantId() {
//        return assistantByAssistantId;
//    }
//
//    public void setAssistantByAssistantId(AssistantEntity assistantByAssistantId) {
//        this.assistantByAssistantId = assistantByAssistantId;
//    }
}
