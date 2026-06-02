package eleTakeOut.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDishVO {

    private Long dishId;

    private String name;

    private String image;

    private Double amount;

    private Integer number;

}