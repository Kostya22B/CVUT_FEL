package dbs.dao;

import dbs.core.AbstractJpaDAO;
import dbs.entities.DoctorEntity;

public class DoctorDao extends AbstractJpaDAO<DoctorEntity> {
    public DoctorDao() {
        super();
        setCurrentClass(DoctorEntity.class);
    }
}
