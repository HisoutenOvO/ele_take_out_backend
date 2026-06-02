package eleTakeOut.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPageQueryDTO {

    private Long shopId;

    private Integer status;

    private Integer page;

    private Integer pageSize;

}
