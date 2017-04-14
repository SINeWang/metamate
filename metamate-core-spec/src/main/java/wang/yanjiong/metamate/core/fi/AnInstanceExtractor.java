package wang.yanjiong.metamate.core.fi;

import lombok.Data;
import one.kii.summer.io.context.WriteContext;
import wang.yanjiong.metamate.core.dai.IntensionDai;

import java.util.List;
import java.util.Map;

/**
 * Created by WangYanJiong on 3/23/17.
 */
public interface AnInstanceExtractor {

    List<Instance> extract(WriteContext context, String subId, Map<String, List<String>> values, Map<String, IntensionDai.Intension> dict);


    @Data
    class Instance {

        private String id;

        private String ownerId;

        private String subId;

        private String intId;

        private String extId;

        private String operatorId;

        private String field;

        private String[] values;

    }
}
