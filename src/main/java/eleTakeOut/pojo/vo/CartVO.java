package eleTakeOut.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartVO {

    private Long id;

    private Long dishId;

    private Long shopId;

    private String name;

    private Double amount;//单价

    private String image;

    private Integer number;//数量
}
