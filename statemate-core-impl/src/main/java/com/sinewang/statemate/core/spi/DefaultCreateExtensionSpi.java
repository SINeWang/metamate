package com.sinewang.statemate.core.spi;

import one.kii.statemate.core.spi.CreateExtensionSpi;
import one.kii.summer.io.exception.*;
import one.kii.summer.io.sender.ErestPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by WangYanJiong on 4/7/17.
 */
@ConfigurationProperties(prefix = "metamate")
@Component
public class DefaultCreateExtensionSpi implements CreateExtensionSpi {

    private static Logger logger = LoggerFactory.getLogger(DefaultCreateExtensionSpi.class);

    private static String URI = "/{ownerId}/extension";
    private static String TREE = "master";
    private static String VISIBILITY_PUBLIC = "public";
    private String baseUrl;

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public Receipt createMasterPublicExtension(Form form) throws Panic {
        String url = baseUrl + URI;

        ErestPost erest = new ErestPost(form.getOwnerId());
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put("group", toList(form.getGroup()));
        map.put("name", toList(form.getName()));
        map.put("tree", toList(TREE));
        map.put("visibility", toList(VISIBILITY_PUBLIC));
        try {
            return erest.execute(url, map, Receipt.class, form.getOwnerId());
        } catch (Conflict conflict) {
            Receipt receipt = new Receipt();
            receipt.setId(conflict.getKey());
            return receipt;
        } catch (BadRequest | Forbidden | NotFound | Panic panic) {
            logger.error("", panic);
            throw new Panic();
        }
    }

    private List<String> toList(String string) {
        List<String> list = new ArrayList<>();
        list.add(string);
        return list;
    }

}
