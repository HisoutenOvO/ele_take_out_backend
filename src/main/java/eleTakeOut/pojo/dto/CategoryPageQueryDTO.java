package eleTakeOut.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPageQueryDTO {

    private String keyword;

    private Integer page;

    private Integer pageSize;

}
