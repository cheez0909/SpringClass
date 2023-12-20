package controllers.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {

    @GetMapping("/upload")
    public String upload() {
        return "file/upload";
    }

    /**
     * @RequestParam("file") -> name이 file인 value를 멀티파트타입으로 불러옴
     */
    @PostMapping("/upload")
    public String uploadPs(@RequestParam("file") MultipartFile[] files){
        for(MultipartFile file : files) {
            File uploadPath = new File("c:/uploads/" + file.getOriginalFilename()); // 파일 경로 생성
            try {
                file.transferTo(uploadPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "file/upload";
    }
}
