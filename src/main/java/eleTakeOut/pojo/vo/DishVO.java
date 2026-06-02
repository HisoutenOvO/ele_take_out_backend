package eleTakeOut.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishVO {

    private Long id;

    private Long shopId;

    private Long categoryId;

    private String name;

    private String description;

    private String ingredients;//原料

    private String serving;//份量

    private Double price;

    private String image;

    private Integer monthlySales;

}
