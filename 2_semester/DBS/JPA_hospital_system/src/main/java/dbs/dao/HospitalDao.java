package dbs.dao;

import dbs.core.AbstractJpaDAO;
import dbs.entities.HospitalEntity;

public class HospitalDao extends AbstractJpaDAO<HospitalEntity> {
    public HospitalDao() {
        super();
        setCurrentClass(HospitalEntity.class);
    }
}
