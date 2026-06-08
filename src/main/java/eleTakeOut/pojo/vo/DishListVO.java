package eleTakeOut.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishListVO {

    private Long id;

    private Long categoryId;

    private String name;

    private String description;

    private Integer monthlySales;

    private String image;

    private Double price;
}
