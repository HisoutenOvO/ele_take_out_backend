package eleTakeOut.server.service.serviceImpl;

import eleTakeOut.pojo.vo.DishVO;
import eleTakeOut.server.mapper.DishMapper;
import eleTakeOut.server.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishMapper dishMapper;


}
