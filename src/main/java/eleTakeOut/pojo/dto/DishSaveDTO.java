package eleTakeOut.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishSaveDTO {

    private String name;

    private Long categoryId;

    private String description;

    private String ingredients;

    private String servings;

    private Double price;

    private String image;
}
