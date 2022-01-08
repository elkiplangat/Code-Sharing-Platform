package platform;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

@Controller
//@RestController
public class ControllerWeb {
   private Code code = new Code("public static void main(String[] args) {\n    SpringApplication.run(CodeSharingPlatform.class, args);\n}", LocalDate.now());

    @GetMapping("/code")
    public String getCode(HttpServletResponse response, ModelMap modelMap) {
        response.setHeader("Content-Type", "text/html");
        modelMap.addAttribute("date", this.code.getDate());
        modelMap.addAttribute("code", this.code.getCode());
        return "code";
    }

    @GetMapping("api/code")
    public ResponseEntity<HashMap<String, Object>> getCode() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");
        HashMap<String, Object> response = new HashMap<>();
        response.put("code", this.code.getCode());
        response.put("date", this.code.getDate());
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");


        return new ResponseEntity<HashMap<String, Object>>(response, headers, HttpStatus.OK);


    }

    @PostMapping("api/code/new")
    public ResponseEntity<HashMap<String, String>> newCode(@RequestBody HashMap<String, String> requestCode) {
        String receivedCode = requestCode.get("code");
        this.code.setCode(receivedCode);
        this.code.setDate(LocalDate.now());

        return new ResponseEntity<>(new HashMap<>(), HttpStatus.OK);


    }

    @GetMapping("/code/new")
    public String getNewCode() {
        return "newcode";
    }
    @PostMapping("code/new")
    public HttpStatus postNewCodeFromBrowser(@RequestBody HashMap<String, String> requestCode ){
        String receivedCode = requestCode.get("code");
        this.code.setCode(receivedCode);
        this.code.setDate(LocalDate.now());
        return HttpStatus.OK;
    }

}
