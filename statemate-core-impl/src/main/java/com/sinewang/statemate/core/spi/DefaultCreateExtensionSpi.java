package com.sinewang.statemate.core.spi;

import one.kii.statemate.core.spi.CreateExtensionSpi;
import one.kii.summer.erest.ErestPostFormUrlEncoded;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
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

    private static String URI = "/extension";
    private static String TREE = "master";
    private static String VISIBILITY_PUBLIC = "public";
    private String ownerId;
    private String baseUrl;

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public String createMasterPublicExtension(ExtensionForm form) {
        String url = baseUrl + URI;

        ErestPostFormUrlEncoded erest = new ErestPostFormUrlEncoded();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-MM-OwnerId", ownerId);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put("group", toList(form.getGroup()));
        map.put("name", toList(form.getName()));
        map.put("tree", toList(TREE));
        map.put("visibility", toList(VISIBILITY_PUBLIC));
        ExtensionReceipt receipt = erest.doPost(url, httpHeaders, map, ExtensionReceipt.class);
        return receipt.getId();
    }

    private List<String> toList(String string) {
        List<String> list = new ArrayList<>();
        list.add(string);
        return list;
    }


}
