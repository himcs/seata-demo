package io.himcs.tcc.storageprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tcc_storage")
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TccStorage implements Serializable {

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


    @Column(name = "goods_code")
    private String goodsCode;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "frozen_quantity")
    private Integer frozenQuantity;

}
