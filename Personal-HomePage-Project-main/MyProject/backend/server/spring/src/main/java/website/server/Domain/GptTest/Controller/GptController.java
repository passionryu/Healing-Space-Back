package website.server.Domain.GptTest.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.server.Domain.GptTest.Service.GptService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/gpt")
@Tag(name = "GPT", description = "GPT API")
public class GptController {

    private final GptService gptService;


    /**
     * test 1 - 독서 토론
     * @param text
     * @return
     */
    @Operation(summary = " gpt testing", description = "")
    @PostMapping("")
    public ResponseEntity<String> test1(@RequestBody String text){

        String response = gptService.call(text);

        return ResponseEntity.ok(response);
    }

}
