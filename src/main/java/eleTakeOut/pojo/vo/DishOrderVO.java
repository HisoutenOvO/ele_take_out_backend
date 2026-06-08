package eleTakeOut.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishOrderVO {

    private Long id;

    private String image;

    private String name;

    private Integer number;//件数

}
