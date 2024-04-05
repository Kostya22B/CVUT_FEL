package dbs.dao;

import dbs.core.AbstractJpaDAO;
import dbs.entities.VenueEntity;

public class VenueDao extends AbstractJpaDAO<VenueEntity> {
    public VenueDao() {
        super();
        setCurrentClass(VenueEntity.class);
    }
}
