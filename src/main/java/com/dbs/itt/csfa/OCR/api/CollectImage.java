package com.dbs.itt.csfa.OCR.api;

import com.dbs.itt.csfa.OCR.service.CollectImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CollectImage {

    @Autowired
    CollectImageService collectImageService;

    @PostMapping("upload")
    public String extractText(MultipartFile image) {
        return collectImageService.extractOCRData(image);
    }
}
