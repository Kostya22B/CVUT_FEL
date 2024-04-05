package dbs.dao;

import dbs.core.AbstractJpaDAO;
import dbs.entities.OperationRoomEntity;

public class OperationRoomDao extends AbstractJpaDAO<OperationRoomEntity> {
    public OperationRoomDao() {
        super();
        setCurrentClass(OperationRoomEntity.class);
    }
}
