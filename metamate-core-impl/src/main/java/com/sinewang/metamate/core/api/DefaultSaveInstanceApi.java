package com.sinewang.metamate.core.api;

import one.kii.summer.beans.utils.DataTools;
import one.kii.summer.bound.factory.ResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wang.yanjiong.metamate.core.api.SaveInstanceApi;
import wang.yanjiong.metamate.core.dai.InstanceDai;
import wang.yanjiong.metamate.core.fi.AnInstanceExtractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangYanJiong on 3/27/17.
 */
@RestController
public class DefaultSaveInstanceApi implements SaveInstanceApi {

    private static final Logger logger = LoggerFactory.getLogger(DefaultSaveInstanceApi.class);

    @Autowired
    private InstanceDai instanceDai;

    @Autowired
    private AnInstanceExtractor instanceExtractor;

    @Override
    public ResponseEntity<List<Instance>> saveInstanceViaFormUrlEncoded(
            @RequestHeader("X-SUMMER-OwnerId") String ownerId,
            @RequestHeader("X-SUMMER-OperatorId") String operatorId,
            @RequestHeader("X-SUMMER-RequestId") String requestId,
            @PathVariable("providerId") String providerId,
            @PathVariable("extId") String extId,
            @RequestParam MultiValueMap<String, String> map) {

        List<AnInstanceExtractor.Instance> instances = instanceExtractor.extract(
                ownerId, providerId, extId, operatorId, map);

        List<InstanceDai.Instances> instances1 = DataTools.copy(instances, InstanceDai.Instances.class);

        try {
            instanceDai.insertInstances(instances1);
        } catch (InstanceDai.InstanceDuplicated instanceDuplicated) {
            logger.error("instanceDuplicated", instanceDuplicated);
        }

        List<InstanceDai.Instance> dbInstances = instanceDai.selectLatestInstanceByOwnerIdExtId(extId, ownerId);

        List<Instance> apiInstances = new ArrayList<>();

        for (InstanceDai.Instance dbInstance : dbInstances) {
            Instance apiInstance = DataTools.copy(dbInstance, Instance.class);
            apiInstance.setValue(new String[]{dbInstance.getValue()});
            apiInstances.add(apiInstance);
        }
        return ResponseFactory.accepted(apiInstances, ownerId);
    }
}
