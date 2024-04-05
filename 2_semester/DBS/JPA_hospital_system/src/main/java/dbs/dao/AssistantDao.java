package dbs.dao;

import dbs.core.AbstractJpaDAO;
import dbs.entities.AssistantEntity;

public class AssistantDao extends AbstractJpaDAO<AssistantEntity> {
    public AssistantDao() {
        super();
        setCurrentClass(AssistantEntity.class);
    }
}
