package com.jedeiah.product.vo;

import com.jedeiah.commons.group.AddGroup;
import com.jedeiah.commons.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author chj
 * @since 2024-03-30
 */
@Data
@Schema(name = "Products", description = "产品vo")
public class ProductsVo {

    @Schema(description = "产品id")
    @NotNull(groups = UpdateGroup.class,message = "产品id不能为空")
    private Integer id;

    @Schema(description = "产品名称")
    @NotBlank(groups = {UpdateGroup.class, AddGroup.class},message = "产品名称不能为空")
    private String name;
}
