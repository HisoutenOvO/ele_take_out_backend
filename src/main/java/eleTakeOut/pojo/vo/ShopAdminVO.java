package eleTakeOut.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopAdminVO {

    private Long id;

    private String name;

    private Double rating;

    private Long monthlySales;

    private Double minPrice;

    private Double deliveryFee;

    private Integer campus;

    private String special;

    private String image;

    private Integer status;

}
