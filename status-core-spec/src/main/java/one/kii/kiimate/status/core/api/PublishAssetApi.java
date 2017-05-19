package one.kii.kiimate.status.core.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import one.kii.summer.io.context.WriteContext;
import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.Conflict;
import one.kii.summer.io.exception.NotFound;

import java.util.Date;
import java.util.List;

/**
 * Created by WangYanJiong on 19/05/2017.
 */
public interface PublishAssetApi {


    Receipt commit(WriteContext context, Form form) throws BadRequest, Conflict, NotFound;

    @Data
    @EqualsAndHashCode(callSuper = false)
    class Form {

        private String stability;

        private String providerId;

        private String subId;

        private String version;

        private String visibility;

    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    class Receipt {

        List<Instance> instances;

        private String pubSet;

        private String providerId;

        private String ownerId;

        private String version;

        private Date beginTime;

    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    class Instance {

        private String field;

        private String[] value;
    }
}