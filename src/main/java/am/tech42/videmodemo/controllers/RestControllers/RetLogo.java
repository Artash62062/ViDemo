package am.tech42.videmodemo.controllers.RestControllers;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.File;
import java.io.IOException;

@RestController
public class RetLogo {
    @GetMapping("/logo")
    public byte[] retImage() throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(new File("/home/artash/Documents/ViDemoStaticFiles/Logo/logo.png"));
        return fileContent;
    }
}
