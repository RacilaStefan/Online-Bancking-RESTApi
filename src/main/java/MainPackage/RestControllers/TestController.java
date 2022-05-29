package MainPackage.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class TestController {

    @GetMapping("/test")
    private ResponseEntity<?> test() {
        return ResponseEntity.ok("THis is a test");
    }
}
