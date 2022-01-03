package hello.proxy.app.v2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequiredArgsConstructor
@RequestMapping // @RestController 안쓰는 이유는 컴포넌트 스캔대상이 되기때문에 일부러 안 쓰는 것
@ResponseBody
public class OrderControllerV2 {
    private final OrderServiceV2 orderService;

    @GetMapping("/v2/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/v2/no-log")
    public String nolog() {
        return "ok";
    }
}
