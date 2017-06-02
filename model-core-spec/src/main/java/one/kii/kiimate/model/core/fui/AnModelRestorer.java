package one.kii.kiimate.model.core.fui;


import one.kii.kiimate.model.core.dai.IntensionDai;

import java.util.Map;

/**
 * Created by WangYanJiong on 08/04/2017.
 */
public interface AnModelRestorer {

    Map<String, Object> restoreAsMetaData(Long extId);

    Map<String, IntensionDai.Record> restoreAsIntensionDict(Long extId);
}
