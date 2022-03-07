package MainPackage.RestControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/debug")
public class TestController {

    @GetMapping("/alo")
    private ResponseEntity<String> test() {
        return ResponseEntity.ok("alo");
    }

}
