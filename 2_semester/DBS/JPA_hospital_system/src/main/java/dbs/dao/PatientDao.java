package dbs.dao;

import dbs.core.AbstractJpaDAO;
import dbs.entities.PatientEntity;


public class PatientDao extends AbstractJpaDAO<PatientEntity> {
    public PatientDao() {
        super();
        setCurrentClass(PatientEntity.class);
    }
}