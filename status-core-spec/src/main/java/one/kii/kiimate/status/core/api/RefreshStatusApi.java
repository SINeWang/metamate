package one.kii.kiimate.status.core.api;

import lombok.Data;
import one.kii.summer.io.context.WriteContext;
import one.kii.summer.io.exception.Conflict;
import one.kii.summer.io.exception.NotFound;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;


/**
 * Created by WangYanJiong on 26/03/2017.
 */
public interface RefreshStatusApi {

    Receipt commit(WriteContext context, SubIdForm form) throws NotFound, Conflict;

    Receipt commit(WriteContext context, GroupNameTreeForm form) throws NotFound, Conflict;

    @Data
    class SubIdForm {
        Long subId;
        MultiValueMap<String, String> map;
    }

    @Data
    class GroupNameTreeForm {
        String group;
        String name;
        String tree;
        MultiValueMap<String, String> map;
    }

    @Data
    class Receipt {
        String subId;
        String ownerId;
        List<Intension> intensions;
        Map<String, Object> map;
    }


    @Data
    class Intension {

        private String id;

        private String field;

        private Boolean single;

        private String structure;

        private String refPubSet;

        private String visibility;

        private Boolean required;
    }
}
