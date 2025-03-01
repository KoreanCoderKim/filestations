package com.example.FileStateWeb2.Controller;

import com.example.FileStateWeb2.MediaTypeUtiles;
import com.example.FileStateWeb2.dto.HumanDto;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;

@RestController
public class C {
    @GetMapping("/hyper")
    @PostMapping("/fileupload")
    public String fileupload(HumanDto human, @RequestParam("uploadFile") MultipartFile uploadFile, HttpServletRequest request) throws IOException {
        System.out.println("HelloController fileupload" + new Date());
        System.out.println(human.toString());

        //어플리케이션 컨텍스트의 "/upload" 디렉토리의 실제 파일 시스템 경로 얻기
        String path = request.getServletContext().getRealPath("/upload");

        //파일 이름 얻기
        String filename = uploadFile.getOriginalFilename();
        //버츄얼리티 폴더 파일로 만들기
        if (!human.getName().isBlank() && !filename.isEmpty()) {
            String choi = human.getName()+"_"+filename;
            String filepath = path + "/" + choi;
            System.out.println("------파일 경로 : " + filepath);
            File f = new File(filepath);
            String filePath = "C://Users//sagwa//Downloads//FileStateWeb2-20250216T035447Z-001//FileStateWeb2//src//main//webapp//upload//manage.txt"; // 파일 경로

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.write(human.getName() + "_" + filename); // 데이터 쓰기
                writer.newLine(); // 줄 바꿈
            } catch (IOException e) {
                e.printStackTrace(); // 예외 처리
            }
            //출력 스트림에 대한 버퍼링 기능을 제공하는 클래스
            BufferedOutputStream os;
            try {
                os = new BufferedOutputStream(new FileOutputStream(f));
                os.write(uploadFile.getBytes());//업로드된 파일 데이터를 저장
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
                return "F5";
            }
            return "F5";
        }
        else {
            return "EOR";
        }
    }
    //download
    @Autowired
    ServletContext serveletContetxt;

    @GetMapping("/fileDownload")
    public ResponseEntity<InputStreamResource> filedownload(String filename, String name, HttpServletRequest requset) throws Exception {
        System.out.println("HelloController filedownload" + new Date());
        //경로
        String path = requset.getServletContext().getRealPath("/upload");
        //파일의 미디어 타입을 얻는다.
        MediaType mediaType = MediaTypeUtiles.getMediaTypeForFileName(serveletContetxt, filename);
        System.out.println("file name : " + filename);
        System.out.println("mediaType name : " + mediaType);
        //파일의 실제경로(파일명 포함된 경로)
        File file = new File(path + File.separator + filename);
        //FileInputStream : 파일로 부터 바이트 단위로 데이터 읽을 수 있는 InputStram 객체 생성
        //InputStreamResource : InputStream 클래스의 래퍼 클래스, InputStream으로부터 데이터를 읽어 클라이언트로 보내는 역할
        InputStreamResource is = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(mediaType)
                .contentLength(file.length())
                .body(is);
    }
}