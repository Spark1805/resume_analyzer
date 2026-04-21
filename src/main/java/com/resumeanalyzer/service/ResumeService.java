package com.resumeanalyzer.service;

import com.resumeanalyzer.model.ResumeAnalysis;
import com.resumeanalyzer.repository.ResumeRepository;
import com.resumeanalyzer.util.FileParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResumeService {

    @Autowired
    private FileParser parser;

    @Autowired
    private OpenAIService aiService;

    @Autowired
    private ResumeRepository repo;

    public ResumeAnalysis process(MultipartFile file, String jd, String userId) throws Exception {
        String text = parser.extractText(file);
        String result = aiService.analyze(text, jd);

        ResumeAnalysis analysis = new ResumeAnalysis();
        analysis.setUserId(userId);
        analysis.setResult(result);

        return repo.save(analysis);
    }
}
