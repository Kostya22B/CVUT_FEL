package dbs.core;

import dbs.entities.AssistantEntity;
import dbs.entities.DoctorEntity;
import dbs.entities.PatientEntity;
import org.eclipse.persistence.descriptors.ClassExtractor;
import org.eclipse.persistence.sessions.Record;
import org.eclipse.persistence.sessions.Session;

public abstract class PersonClassExtractor extends ClassExtractor {
    public Class<?> extractClassFromRow(Record databaseRow, Session session) {
        if (databaseRow.containsKey("cisloZdravotniPojistovny")) {
            return PatientEntity.class;
        } else if (databaseRow.containsKey("doctor_id")) {
            return DoctorEntity.class;
        } else if (databaseRow.containsKey("assistant_id")){
            return AssistantEntity.class;
        }
        return null;
    }
}