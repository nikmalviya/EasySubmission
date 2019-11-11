package com.project;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileNotFoundException;

@Controller
@RequestMapping("/files")
public class RetriveFileController {

    @GetMapping(produces = MediaType.ALL_VALUE)
    @ResponseBody
    public ResponseEntity<FileSystemResource> getFile(@RequestParam("filePath") String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        String filename = file.getName();
        filename = filename.substring(filename.indexOf("$$")+2,filename.lastIndexOf("$$"));
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        return ResponseEntity.ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+filename+"\"")
                            .body(fileSystemResource);
    }
}
