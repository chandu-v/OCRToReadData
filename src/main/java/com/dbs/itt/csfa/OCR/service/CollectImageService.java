package com.dbs.itt.csfa.OCR.service;

import org.springframework.web.multipart.MultipartFile;

public interface CollectImageService {

    public String extractOCRData(MultipartFile image);

}
