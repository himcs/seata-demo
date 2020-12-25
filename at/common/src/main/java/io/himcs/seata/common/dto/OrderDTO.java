package io.himcs.seata.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @Builder.Default
    private LocalDateTime createTime = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime updateTime = LocalDateTime.now();

    private String username;

    private Integer points;

    private String goodsCode;

    private Integer quantity;

    private Integer amount;

}
