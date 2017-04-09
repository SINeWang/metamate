package wang.yanjiong.metamate.core.api;

import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by WangYanJiong on 4/6/17.
 */

@RestController
@RequestMapping("/v1")
public interface SubscribeModelApi {

    String NAME_DEFAULT = "default";

    String TREE_MASTER = "master";

    @RequestMapping(value = "/subscribe/{pubSetHash}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseEntity<Receipt> subscribe(
            @RequestHeader("X-SUMMER-RequestId") String requestId,
            @RequestHeader("X-MM-SubscriberId") String subscriberId,
            @RequestHeader("X-MM-OperatorId") String operatorId,
            @ModelAttribute Form form);

    @Data
    class Form {
        private String pubSetHash;
        private String group;
    }


    @Data
    class Receipt {
        private String id;
        private String subSetHash;
        private String subscriberId;
        private String group;
        private String name;
        private String tree;
        private String operatorId;
    }

}
