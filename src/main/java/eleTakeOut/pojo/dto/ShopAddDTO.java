package eleTakeOut.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopAddDTO {

    private String name;

    private String password;//md5

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
