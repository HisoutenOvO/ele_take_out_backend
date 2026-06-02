package eleTakeOut.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishShopVO {

    private Long id;

    private Long categoryId;

    private String name;

    private String description;

    private String ingredients;//原料

    private String serving;//份量

    private Double price;

    private String image;

    private Integer monthlySales;

}
