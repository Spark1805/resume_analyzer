package com.resumeanalyzer.controller;

import com.resumeanalyzer.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    private ResumeService service;

    @PostMapping("/analyze")
    public ResponseEntity<?> analyze(
            @RequestParam("file") MultipartFile file,
            @RequestParam("jd") String jd
    ) throws Exception {

        String userId = "demoUser";
        return ResponseEntity.ok(service.process(file, jd, userId));
    }
}
