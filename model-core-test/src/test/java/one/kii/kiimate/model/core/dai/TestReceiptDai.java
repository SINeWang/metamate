package one.kii.kiimate.model.core.dai;

import com.sinewang.kiimate.model.core.dai.mapper.ExtensionMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by WangYanJiong on 3/24/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@BootstrapWith(SpringBootTestContextBootstrapper.class)
@ComponentScan("com.sinewang.kiimate.model")
@SpringBootTest(classes = {TestReceiptDai.class})
public class TestReceiptDai {

    Long testId = 11111L;
    String testOwnerId = "testOwnerId";
    String testGroup = "testGroup";
    String testName = "testName";
    String testTree = "testTree";
    String testStructure = "testStructure";
    String testVisibility = "testVisibility";
    @Autowired
    private ExtensionDai extensionDai;

    @Autowired
    private ExtensionMapper extensionMapper;

    @Before
    public void setup() {
        extensionMapper.revoke(testId, new Date());
    }

    @Test
    public void testFirstInsert() {
        extensionMapper.revoke(testId, new Date());

        ExtensionDai.Record record = new ExtensionDai.Record();
        record.setGroup(testGroup);
        record.setOwnerId(testOwnerId);
        record.setName(testName);
        record.setTree(testTree);
        record.setVisibility(testVisibility);
        record.setId(testId);
        try {
            extensionDai.remember(record);
        } catch (Exception conflict) {
            conflict.printStackTrace();
        }
        ExtensionDai.ChannelId extId = new ExtensionDai.ChannelId();
        extId.setId(testId);

        ExtensionDai.Record dbRecord = null;
        try {
            dbRecord = extensionDai.loadLast(extId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals(dbRecord.getId(), testId);
        Assert.assertEquals(dbRecord.getOwnerId(), testOwnerId);
        Assert.assertEquals(dbRecord.getGroup(), testGroup);
        Assert.assertEquals(dbRecord.getName(), testName);
        Assert.assertEquals(dbRecord.getTree(), testTree);
        Assert.assertEquals(dbRecord.getVisibility(), testVisibility);
    }

    @Test()
    public void testSecondInsert() {
        ExtensionDai.Record record = new ExtensionDai.Record();
        record.setGroup(testGroup);
        record.setOwnerId(testOwnerId);
        record.setName(testName);
        record.setTree(testTree);
        record.setVisibility(testVisibility);
        record.setId(testId);
        try {
            extensionDai.remember(record);
        } catch (Exception conflict) {
            conflict.printStackTrace();
        }
    }

    @After
    public void cleanUp() {
        extensionMapper.revoke(testId, new Date());
    }

}
