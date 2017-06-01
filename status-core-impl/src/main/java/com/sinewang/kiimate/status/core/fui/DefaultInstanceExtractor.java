package com.sinewang.kiimate.status.core.fui;

import com.google.common.base.CaseFormat;
import one.kii.derid.derid64.Eid64Generator;
import one.kii.kiimate.model.core.dai.IntensionDai;
import one.kii.kiimate.status.core.fui.AnInstanceExtractor;
import one.kii.summer.beans.utils.ValueMapping;
import one.kii.summer.io.context.WriteContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by WangYanJiong on 3/27/17.
 */

@Component
public class DefaultInstanceExtractor implements AnInstanceExtractor {

    private static final Eid64Generator setgen = new Eid64Generator(4);
    private static Logger logger = LoggerFactory.getLogger(DefaultInstanceExtractor.class);

    @Override
    public List<Instance> extract(WriteContext context, long subId, Map<String, List<String>> map, Map<String, IntensionDai.Intension> fieldDict) {
        List<Instance> instances = new ArrayList<>();

        for (String field : map.keySet()) {
            String dictField = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, field);
            IntensionDai.Intension intension = fieldDict.get(dictField);
            if (intension == null) {
                logger.warn("cannot find field [{}]", field);
                continue;
            }
            long intId = intension.getId();

            String[] values = cleanUpValues(map.get(field).toArray(new String[0]));
            Instance instance = ValueMapping.from(Instance.class, context);
            instance.setId(setgen.born());
            instance.setSubId(subId);
            instance.setExtId(intension.getExtId());
            instance.setIntId(intId);
            instance.setField(dictField);
            instance.setValues(values);
            instances.add(instance);
        }
        return instances;
    }

    private String[] cleanUpValues(String[] values) {
        if (values.length == 0) {
            return null;
        }
        List<String> valueList = new ArrayList<>();
        boolean empty = false;
        for (String value : values) {
            if (value == null) {
                continue;
            } else if (value.trim().length() == 0) {
                empty = true;
                continue;
            }
            valueList.add(value);
        }
        if (empty && valueList.size() == 0) {
            valueList.add("");
        }
        return valueList.toArray(new String[]{});
    }


}
