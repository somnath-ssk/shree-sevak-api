package shreesevak.api.model;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.StandardBasicTypes;

import org.hibernate.type.Type;

public class BaithakIdGenerator extends SequenceStyleGenerator {

    public static final String PREFIX = "INMH12AM1_";

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        return PREFIX + String.format("%03d", (Integer) super.generate(session, object));
    }

//    @Override
//    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) {
//        super.configure(LongType.INSTANCE, params, serviceRegistry);
//        if (params.getProperty(SEQUENCE_PARAM) == null || params.getProperty(SEQUENCE_PARAM).isEmpty()) {
//            params.setProperty(SEQUENCE_PARAM, "your_sequence_name"); // Replace with your database sequence name
//        }
//        super.configure(type, params, serviceRegistry);
//    }
}

