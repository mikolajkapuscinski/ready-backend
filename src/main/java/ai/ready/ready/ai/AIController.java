package ai.ready.ready.ai;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ai")
@AllArgsConstructor
public class AIController {

    private final AIService aiService;

    @PostMapping("/find-book")
    ResponseEntity<FindBookResponseFormat> findBook(@RequestParam("image") MultipartFile image) {
        if(image.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(aiService.findBook(image));
    }
}
