import io.himcs.tcc.storageprovider.StorageProviderApplication;
import io.himcs.tcc.storageprovider.action.StorageAction;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = StorageProviderApplication.class)
public class StorageProviderTest {
    @Resource
    StorageAction storageAction;

    @Test
    public void testPrepare() {
        BusinessActionContext businessActionContext = new BusinessActionContext();
        storageAction.prepare(businessActionContext, "coke", 10);
    }

    @Test
    public void testCommit() {
        BusinessActionContext businessActionContext = new BusinessActionContext();
        Map map = new HashMap<>();
        map.put("goodsCode", "coke");
        businessActionContext.setActionContext(map);
        storageAction.commit(businessActionContext);
    }

    @Test
    public void testRollback() {
        BusinessActionContext businessActionContext = new BusinessActionContext();
        Map map = new HashMap<>();
        map.put("goodsCode", "coke");
        businessActionContext.setActionContext(map);
        storageAction.rollback(businessActionContext);
    }
}
