package dbs.dao;

import dbs.core.AbstractJpaDAO;
import dbs.entities.MedicalRecordEntity;

public class MedicalRecordDao extends AbstractJpaDAO<MedicalRecordEntity> {
    public MedicalRecordDao() {
        super();
        setCurrentClass(MedicalRecordEntity.class);
    }
}
