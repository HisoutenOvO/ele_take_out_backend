package eleTakeOut.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishVO {

    private Long id;

    private Long shopId;

    private String categoryName;

    private String name;

    private String description;

    private String ingredients;//原料

    private String serving;//份量

    private Double price;

    private String image;

    private Integer monthlySales;

}
