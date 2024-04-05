package dbs.dao;

import dbs.core.AbstractJpaDAO;
import dbs.entities.OperationEntity;

public class OperationDao extends AbstractJpaDAO<OperationEntity> {
    public OperationDao() {
        super();
        setCurrentClass(OperationEntity.class);
    }
}
