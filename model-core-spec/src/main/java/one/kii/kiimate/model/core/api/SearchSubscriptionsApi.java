package one.kii.kiimate.model.core.api;

import lombok.Data;
import one.kii.summer.io.context.ReadContext;

import java.util.List;

/**
 * Created by WangYanJiong on 10/05/2017.
 */
public interface SearchSubscriptionsApi {


    List<Subscriptions> search(ReadContext context, QueryForm form);


    @Data
    class Subscriptions {
        String group;
        String name;
    }

    @Data
    class QueryForm {
        private String group;
    }

}