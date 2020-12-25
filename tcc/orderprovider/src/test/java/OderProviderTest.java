import io.himcs.tcc.orderprovider.OrderProviderApplication;
import io.himcs.tcc.orderprovider.action.OrderAction;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootTest(classes = OrderProviderApplication.class)
public class OderProviderTest {
    @Resource
    private OrderAction orderAction;

    @Test
    public void testPrepare() {
        orderAction.prepare(new BusinessActionContext(), UUID.randomUUID().toString(), "coke", 10, 20);
    }
    @Test
    public void testCommit() {
        BusinessActionContext businessActionContext = new BusinessActionContext();
        Map map = new HashMap<>();
        map.put("orderCode", "024431f8-0d85-420c-9239-08832e4da61f");
        businessActionContext.setActionContext(map);
        orderAction.commit(businessActionContext);
    }

}
