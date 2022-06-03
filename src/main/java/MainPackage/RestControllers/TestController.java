package MainPackage.RestControllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Test Controller")

@AllArgsConstructor
@RestController
public class TestController {

    @GetMapping("/test")
    private ResponseEntity<?> test() {
        return ResponseEntity.ok("THis is a test");
    }
}
