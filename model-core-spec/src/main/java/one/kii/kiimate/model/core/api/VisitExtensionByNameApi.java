package one.kii.kiimate.model.core.api;

import lombok.Data;
import one.kii.summer.asdf.api.VisitApi;
import one.kii.summer.io.context.ReadContext;
import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.NotFound;
import one.kii.summer.io.exception.Panic;
import one.kii.summer.zoom.ZoomInByName;

import java.util.List;
import java.util.Map;

/**
 * Created by WangYanJiong on 4/5/17.
 */

public interface VisitExtensionByNameApi extends VisitApi<VisitExtensionByNameApi.Receipt, ReadContext, ZoomInByName> {


    Receipt visit(ReadContext context, ZoomInByName form) throws BadRequest, NotFound, Panic;


    @Data
    class Receipt {
        String id;
        String ownerId;
        String group;
        String name;
        String tree;
        String visibility;
        List<Intension> intensions;
        Map<String, Object> schema;
    }

    @Data
    class Intension {

        private String id;

        private String field;

        private Boolean single;

        private String structure;

        private String refSet;

        private String visibility;

        private Boolean required;
    }

}
