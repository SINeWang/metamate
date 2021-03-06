package one.kii.kiimate.model.core.dai;

import lombok.Data;
import one.kii.summer.beans.annotations.Commit;
import one.kii.summer.beans.annotations.Unique;
import one.kii.summer.io.annotations.MayHave;
import one.kii.summer.io.exception.BadRequest;
import one.kii.summer.io.exception.Conflict;
import one.kii.summer.io.exception.NotFound;
import one.kii.summer.io.exception.Panic;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by WangYanJiong on 3/23/17.
 */
public interface ExtensionDai {

    Record loadLast(ChannelName channel) throws Panic, BadRequest, NotFound;

    Record loadLast(ChannelId channel) throws Panic, BadRequest, NotFound;

    Record loadLast(ChannelSet channel) throws Panic, BadRequest;

    List<Record> search(ClueGroup clue) throws BadRequest, Panic;

    @Transactional
    void remember(Record record) throws Conflict, BadRequest;

    void forget(String id);


    List<Providers> queryProviders(ClueId clue);

    @Data
    class ClueId {
        String id;
    }

    @Data
    class Providers {
        String id;
    }

    @Data
    class ClueGroup {

        String ownerId;

        String group;
    }


    @Data
    class ChannelId {

        @Unique
        String id;

        @Unique
        @MayHave
        Date endTime;
    }

    @Data
    class ChannelSet {
        @Unique
        String set;
    }

    @Data
    class ChannelName {

        @Unique
        String ownerId;

        @Unique
        String group;

        @Unique
        String name;

        @Unique
        String tree;

        @Unique
        @MayHave
        Date endTime;
    }

    @Data
    class Record {

        private String id;

        private String commit;

        @Unique
        @Commit
        private String ownerId;

        @Unique
        @Commit
        private String group;

        @Unique
        @Commit
        private String name;

        @Unique
        @Commit
        private String tree;

        @Commit
        private String visibility;

        @Commit
        private String operatorId;

        @Commit
        private Date beginTime;

        @MayHave
        @Commit
        @Unique
        private Date endTime;

    }

}
