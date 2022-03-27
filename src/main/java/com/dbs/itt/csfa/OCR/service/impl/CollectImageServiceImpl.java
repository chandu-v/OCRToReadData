package com.dbs.itt.csfa.OCR.service.impl;

import com.dbs.itt.csfa.OCR.service.CollectImageService;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.leptonica.PIX;
import org.bytedeco.tesseract.TessBaseAPI;
import org.bytedeco.tesseract.presets.tesseract;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.bytedeco.leptonica.global.lept.pixDestroy;
import static org.bytedeco.leptonica.global.lept.pixRead;

@Service
public class CollectImageServiceImpl implements CollectImageService {
    @Value("${lucasg_path}")
    public String lucasg_path;
    @Override
    public String extractOCRData(MultipartFile image) {
        BytePointer outText;
        TessBaseAPI api = null;
        try {
            api = new TessBaseAPI();
        }catch (Exception e) {
            e.getLocalizedMessage();
        }
// Initialize tesseract-ocr with English, without specifying tessdata pathls

        if (api.Init(null, "eng") != 0) {
            System.err.println("Could not initialize tesseract.");
            System.exit(1);
        }
        // Open input image with leptonica library
        PIX PIXimage = null;
        File file = new File(System.getProperty("java.io.tmpdir") + image.getName() + ".png");
        try {
            image.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PIXimage = pixRead(file.getPath());
        api.SetImage(PIXimage);
        // Get OCR result
        outText = api.GetUTF8Text();
        System.out.println("OCR output:\n" + outText.getString());

        // Destroy used object and release memory
        api.End();
        outText.deallocate();
        pixDestroy(PIXimage);
        file.deleteOnExit();
        return outText.getString();
    }
}
