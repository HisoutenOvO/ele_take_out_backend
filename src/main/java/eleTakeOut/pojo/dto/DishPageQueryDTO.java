package eleTakeOut.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishPageQueryDTO {

    private Long shopId;

    private Long categoryId;

    private String keyword;

    private Integer page;

    private Integer pageSize;
}
