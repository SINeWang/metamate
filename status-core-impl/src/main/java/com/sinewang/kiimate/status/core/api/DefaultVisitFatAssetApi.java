package com.sinewang.kiimate.status.core.api;

import one.kii.kiimate.model.core.dai.IntensionDai;
import one.kii.kiimate.model.core.dai.ModelSubscriptionDai;
import one.kii.kiimate.status.core.api.VisitFatAssetApi;
import one.kii.kiimate.status.core.dai.AssetDai;
import one.kii.kiimate.status.core.dai.InstanceDai;
import one.kii.kiimate.status.core.fui.InstanceTransformer;
import one.kii.summer.beans.utils.ValueMapping;
import one.kii.summer.io.context.ReadContext;
import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.NotFound;
import one.kii.summer.io.exception.Panic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by WangYanJiong on 21/05/2017.
 */
@Component
public class DefaultVisitFatAssetApi implements VisitFatAssetApi {

    @Autowired
    private AssetDai assetDai;

    @Autowired
    private InstanceDai instanceDai;

    @Autowired
    private IntensionDai intensionDai;

    @Autowired
    private ModelSubscriptionDai modelSubscriptionDai;

    @Autowired
    private InstanceTransformer instanceTransformer;


    @Override
    public Asset visit(ReadContext context, GroupNameForm form) throws BadRequest, NotFound, Panic {
        AssetDai.ChannelGroupName channel = ValueMapping.from(AssetDai.ChannelGroupName.class, form, context);
        AssetDai.Assets record = assetDai.load(channel);
        return transform(context, record);
    }

    @Override
    public Asset visit(ReadContext context, OwnerIdForm form) throws BadRequest, NotFound, Panic {
        AssetDai.ChannelOwnerId channel = ValueMapping.from(AssetDai.ChannelOwnerId.class, form, context);
        AssetDai.Assets record = assetDai.load(channel);
        return transform(context, record);
    }

    private Asset transform(ReadContext context, AssetDai.Assets assetDb) throws BadRequest, Panic {
        InstanceDai.ChannelStatusPubSet statusPubSet = ValueMapping.from(InstanceDai.ChannelStatusPubSet.class, assetDb);
        List<InstanceDai.Instance> instances = instanceDai.loadInstances(statusPubSet);
        Asset asset = ValueMapping.from(Asset.class, assetDb);


        ModelSubscriptionDai.ChannelSubId modelSubId = ValueMapping.from(ModelSubscriptionDai.ChannelSubId.class, context, assetDb);
        ModelSubscriptionDai.ModelPubSet model = modelSubscriptionDai.getModelPubSetByOwnerSubscription(modelSubId);
        Map<String, Object> map = instanceTransformer.toTimedValue(instances, model);

        IntensionDai.ChannelLastExtension rootExtension = ValueMapping.from(IntensionDai.ChannelLastExtension.class, model);
        rootExtension.setId(model.getRootExtId());
        List<IntensionDai.Record> recordList = intensionDai.loadLast(rootExtension);
        List<Intension> intensions = ValueMapping.from(Intension.class, recordList);
        asset.setIntensions(intensions);
        asset.setMap(map);
        asset.setOwnerId(context.getOwnerId());
        return asset;
    }

    @Override
    public Object visit(ReadContext readContext, Object o) throws BadRequest, NotFound, Panic {
        if (o instanceof OwnerIdForm) {
            return visit(readContext, (OwnerIdForm) o);
        } else {
            return visit(readContext, (GroupNameForm) o);
        }
    }
}
