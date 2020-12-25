package io.himcs.tcc.orderprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tcc_order")
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TccOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "create_time")
    @Builder.Default
    private LocalDateTime createTime = LocalDateTime.now();

    @Column(name = "update_time")
    @Builder.Default
    private LocalDateTime updateTime = LocalDateTime.now();

    @Column(name = "order_code")
    private String orderCode;

    @Column(name = "goods_code")
    private String goodsCode;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "frozen_amount")
    private Integer frozenAmount;

    @Column(name = "amount")
    private Integer amount;

    /**
     * 0-已创建 1-完成 2-取消
     */
    @Column(name = "status")
    private Integer status;

}
