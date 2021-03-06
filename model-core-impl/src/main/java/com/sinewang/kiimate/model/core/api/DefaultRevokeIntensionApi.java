package com.sinewang.kiimate.model.core.api;

import one.kii.kiimate.model.core.api.RevokeIntensionApi;
import one.kii.kiimate.model.core.dai.IntensionDai;
import one.kii.kiimate.model.core.fui.AnModelRestoreFui;
import one.kii.summer.beans.utils.ValueMapping;
import one.kii.summer.io.context.WriteContext;
import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.Conflict;
import one.kii.summer.io.exception.NotFound;
import one.kii.summer.io.exception.Panic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by WangYanJiong on 09/05/2017.
 */
@Component
public class DefaultRevokeIntensionApi implements RevokeIntensionApi {

    @Autowired
    private IntensionDai intensionDai;

    @Autowired
    private AnModelRestoreFui modelRestorer;

    @Override
    public Receipt commit(WriteContext context, Form form) throws BadRequest, Conflict, NotFound, Panic {
        IntensionDai.ChannelId channel = ValueMapping.from(IntensionDai.ChannelId.class, form);
        intensionDai.forget(channel);

        IntensionDai.ChannelExtensionId latest = new IntensionDai.ChannelExtensionId();
        latest.setId(form.getExtId());

        List<Intension> intensions = ValueMapping.from(Intension.class, intensionDai.loadLast(latest));
        Map<String, Object> schema = modelRestorer.restoreAsMetaData(latest);
        Receipt receipt = new Receipt();
        receipt.setIntensions(intensions);
        receipt.setSchema(schema);
        return receipt;
    }
}
