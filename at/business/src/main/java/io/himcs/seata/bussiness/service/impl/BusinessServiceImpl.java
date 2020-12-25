package io.himcs.seata.bussiness.service.impl;

import io.himcs.seata.bussiness.client.OrderService;
import io.himcs.seata.bussiness.client.PointsService;
import io.himcs.seata.bussiness.client.StorageService;
import io.himcs.seata.bussiness.service.BusinessService;
import io.himcs.seata.common.dto.OrderDTO;
import io.himcs.seata.common.dto.PointsDTO;
import io.himcs.seata.common.dto.StorageDTO;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Resource
    private PointsService pointsService;
    @Resource
    private StorageService storageService;
    @Resource
    private OrderService orderService;

    /**
     * 商品销售
     *
     * @param goodsCode 商品编码
     * @param quantity  销售数量
     * @param username  用户名
     * @param points    增加积分
     * @param amount    订单金额
     * @return
     */
    // TM 向 TC发起全局事务 生成 XID(全局锁）
//
    @GlobalTransactional(name = "fsp-sale", timeoutMills = 20000, rollbackFor = Exception.class)
    @Override
    public OrderDTO sale(String goodsCode, Integer quantity, String username, Integer points, Integer amount) {
        PointsDTO pointsDTO = PointsDTO.builder().username(username).points(points).build();
        StorageDTO storageDTO = StorageDTO.builder().goodsCode(goodsCode).quantity(quantity).build();
        OrderDTO request = OrderDTO.builder().username(username).points(points).goodsCode(goodsCode).quantity(quantity).amount(amount).build();
        //写表 UNDO_LOG 记录回滚日志（BranchID),通知TC操作结果
        pointsService.increase(pointsDTO);
        //写表 UNDO_LOG 记录回滚日志（BranchID),通知TC操作结果
        storageService.decrease(storageDTO);
        //写表 UNDO_LOG 记录回滚日志（BranchID),通知TC操作结果
        OrderDTO order = orderService.create(request);
        // 分支 a 执行成功 TM 通知 TC 全局提交
        // 分支 a TC 通知所有RM 提交成功， 删除 UNDO_LOG 回滚日志

        // 分支 b 执行失败 TM 通知 TC 全局 Rollback
        // 分支 b TC 通知所有RM 进行回滚， 根据 UNDO_LOG 反向操作 还原数据 删除 UNDO_LOG

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return order;
    }
}
