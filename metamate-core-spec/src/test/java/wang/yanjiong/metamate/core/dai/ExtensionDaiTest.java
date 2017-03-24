package wang.yanjiong.metamate.core.dai;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wang.yanjiong.metamate.core.model.Extension;

/**
 * Created by WangYanJiong on 3/24/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@BootstrapWith(SpringBootTestContextBootstrapper.class)
public class ExtensionDaiTest {

    @Autowired
    private ExtensionDai extensionDai;

    @Test(expected = NullPointerException.class)
    public void testNull() {
        extensionDai.getExtensionById(null);
    }

    @Test(expected = NullPointerException.class)
    public void testEmpty() {
        extensionDai.getExtensionById("");
    }

    @Test
    public void testExist() {
        Extension extension = extensionDai.getExtensionById("12");
        Assert.assertNotNull(extension);
    }
}