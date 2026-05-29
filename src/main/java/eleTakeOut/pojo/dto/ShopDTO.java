package eleTakeOut.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopDTO {

    private Long id;

    private String name;

    private String password;//md5

    private Double rating;

    private Long monthlySales;

    private Integer deliveryTime;

    private String distance;

    private Double minPrice;

    private Double deliveryFee;

    private Integer campus;

    private String special;

    private String image;

    private String notice;

    private Integer status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
