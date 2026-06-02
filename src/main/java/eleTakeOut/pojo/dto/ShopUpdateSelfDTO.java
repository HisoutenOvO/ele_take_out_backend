package eleTakeOut.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopUpdateSelfDTO {

    private String name;

    private String special;

    private String image;

    private String notice;

}
