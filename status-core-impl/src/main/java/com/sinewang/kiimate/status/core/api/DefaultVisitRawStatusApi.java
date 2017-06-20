package com.sinewang.kiimate.status.core.api;

import one.kii.kiimate.model.core.dai.ModelSubscriptionDai;
import one.kii.kiimate.status.core.api.VisitRawStatusApi;
import one.kii.kiimate.status.core.dai.GlimpsesDai;
import one.kii.kiimate.status.core.dai.InstanceDai;
import one.kii.kiimate.status.core.fui.InstanceTransformer;
import one.kii.summer.beans.utils.ValueMapping;
import one.kii.summer.io.context.ReadContext;
import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.NotFound;
import one.kii.summer.io.exception.Panic;
import one.kii.summer.zoom.InsideView;
import one.kii.summer.zoom.ZoomInById;
import one.kii.summer.zoom.ZoomInByName;
import one.kii.summer.zoom.ZoomOutBySet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by WangYanJiong on 20/6/17.
 */

@Component
public class DefaultVisitRawStatusApi implements VisitRawStatusApi {

    @Autowired
    private InstanceDai instanceDai;

    @Autowired
    private GlimpsesDai glimpsesDai;

    @Autowired
    private ModelSubscriptionDai modelSubscriptionDai;

    @Autowired
    private InstanceTransformer instanceTransformer;

    @Override
    public Map<String, Object> visit(ReadContext context, ZoomOutBySet form) throws NotFound, BadRequest, Panic {

        GlimpsesDai.Glimpse glimpse = glimpsesDai.load(form);

        ZoomInByName channel = ValueMapping.from(ZoomInByName.class, glimpse);

        InsideView modelSub = modelSubscriptionDai.loadModelSubByName(channel);

        ZoomInById instanceChannel = ValueMapping.from(ZoomInById.class, modelSub);

        List<InstanceDai.Record> records = instanceDai.loadInstances(instanceChannel);

        return instanceTransformer.toRawValue(records, modelSub);
    }

}