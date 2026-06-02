package eleTakeOut.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopUpdateDTO {

    private String name;

    private Double rating;

    private Long monthlySales;

    private Integer deliveryTime;

    private String distance;

    private Double minPrice;

    private Double deliveryFee;

    private Integer campus;

    private String special;

    private String image;

    private String notice;

    private Integer status;

}
