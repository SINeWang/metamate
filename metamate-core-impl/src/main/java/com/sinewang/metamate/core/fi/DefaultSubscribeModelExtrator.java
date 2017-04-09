package com.sinewang.metamate.core.fi;

import com.google.common.base.CaseFormat;
import one.kii.summer.codec.utils.HashTools;
import org.springframework.stereotype.Component;
import wang.yanjiong.metamate.core.api.SubscribeModelApi;
import wang.yanjiong.metamate.core.fi.AnSubscribeModelExtractor;

/**
 * Created by WangYanJiong on 4/6/17.
 */

@Component
public class DefaultSubscribeModelExtrator implements AnSubscribeModelExtractor {


    @Override
    public String hashId(String subscriberId, String pubSetHash, String group, String name, String tree) {
        return HashTools.hashHex(subscriberId, pubSetHash, group, name, tree);
    }

    @Override
    public ModelSubscription extract(SubscribeModelApi.Form form, String subscriberId, String operatorId, String name, String tree) {
        form.setGroup(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, form.getGroup()));

        ModelSubscription subscription = new ModelSubscription();
        subscription.setSubSetHash(form.getPubSetHash());
        subscription.setGroup(form.getGroup());
        subscription.setName(NAME_ROOT);
        subscription.setTree(TREE_MASTER);
        subscription.setSubscriberId(subscriberId);
        subscription.setOperatorId(operatorId);

        String id = hashId(subscriberId, form.getPubSetHash(), form.getGroup(), name, tree);

        subscription.setId(id);
        return subscription;
    }
}
