package MainPackage.RestControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/alo")
    private ResponseEntity<String> test() {
        return ResponseEntity.ok("alo");
    }

    @GetMapping("/login")
    private ResponseEntity<String> send404() {
        return new ResponseEntity<>("404 Not Found", HttpStatus.OK);
    }
}
