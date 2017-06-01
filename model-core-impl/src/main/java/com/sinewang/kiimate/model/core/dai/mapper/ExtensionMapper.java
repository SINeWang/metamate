package com.sinewang.kiimate.model.core.dai.mapper;

import one.kii.kiimate.model.core.dai.ExtensionDai;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by WangYanJiong on 3/23/17.
 */
@Mapper
public interface ExtensionMapper {

    void insertExtension(
            @Param("id") long id,
            @Param("ownerId") String ownerId,
            @Param("group") String group,
            @Param("name") String name,
            @Param("tree") String tree,
            @Param("visibility") String visibility,
            @Param("beginTime") Date beginTime);

    ExtensionDai.Extension selectLatestExtensionById(
            @Param("id") long id);

    ExtensionDai.Extension selectLastExtensionByIdTime(
            @Param("id") long id,
            @Param("beginTime") Date beginTime);

    List<ExtensionDai.Extension> selectExtensionsByOwnerGroup(
            @Param("ownerId") String ownerId,
            @Param("group") String group);

    List<ExtensionDai.Extension> queryExtensionsByOwnerGroup(
            @Param("ownerId") String ownerId,
            @Param("group") String group);

    void deleteExtensionById(@Param("id") long id);

    void updateEndTimeExtensionById(
            @Param("extId") long extId,
            @Param("endTime") Date endTime);

}
