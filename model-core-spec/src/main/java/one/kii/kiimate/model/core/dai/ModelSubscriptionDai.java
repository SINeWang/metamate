package one.kii.kiimate.model.core.dai;

import lombok.Data;
import lombok.Getter;
import one.kii.summer.beans.annotations.KeyFactor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by WangYanJiong on 4/6/17.
 */
public interface ModelSubscriptionDai {


    @Transactional
    void save(ModelSubscription modelSubscription) throws DuplicatedSubscription;

    ExtensionId getLatestRootExtIdByOwnerSubscription(String owner, String subId);

    List<ModelSubscription> querySubscriptions(ClueGroup clue);

    List<Subscribers> querySubscribers(ClueSubscriberId clue);

    ModelSubscription selectSubscription(ChannelGroupNameTree channel);

    int countModelSubscriptions(String pubSet);

    @Data
    class ClueGroup{
        String ownerId;
        String group;
    }

    @Data
    class ClueSubscriberId{
        String id;
    }

    @Data
    class ChannelGroupNameTree{
        String ownerId;
        String group;
        String name;
        String tree;
    }

    @Data
    class Subscribers {
        String id;
    }

    @Data
    class ExtensionId {
        String id;
    }

    @Data
    class ModelSubscription {
        private String id;

        private String subSet;

        @KeyFactor
        private String subscriberId;

        @KeyFactor
        private String group;

        @KeyFactor
        private String name;

        @KeyFactor
        private String tree;

        private String operatorId;

    }

    class DuplicatedSubscription extends Exception {

        @Getter
        private String subSetHash;

        @Getter
        private String subscriberId;

        @Getter
        private String group;

        @Getter
        private String name;

        @Getter
        private String tree;

        public DuplicatedSubscription(String subSetHash, String subscriberId, String group, String name, String tree) {
            super();
            this.subSetHash = subSetHash;
            this.subscriberId = subscriberId;
            this.group = group;
            this.name = name;
            this.tree = tree;
        }

    }
}
