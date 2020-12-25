package io.himcs.seata.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class StorageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String goodsCode;

    private Integer quantity;
}
