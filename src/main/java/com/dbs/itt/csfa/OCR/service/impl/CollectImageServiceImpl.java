package com.dbs.itt.csfa.OCR.service.impl;

import com.dbs.itt.csfa.OCR.service.CollectImageService;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.leptonica.PIX;
import org.bytedeco.tesseract.TessBaseAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.bytedeco.leptonica.global.lept.pixDestroy;
import static org.bytedeco.leptonica.global.lept.pixRead;

@Service
public class CollectImageServiceImpl implements CollectImageService {
    @Value("${lucasg_path}")
    public String lucasg_path;
    @Override
    public String extractOCRData(MultipartFile image) {
//        loadLibToPath();
        BytePointer outText;
        TessBaseAPI api = new TessBaseAPI();
// Initialize tesseract-ocr with English, without specifying tessdata path
        if (api.Init(null, "eng") != 0) {
            System.err.println("Could not initialize tesseract.");
            System.exit(1);
        }
        // Open input image with leptonica library
//        PIX image = pixRead(args.length > 0 ? args[0] : "/usr/src/tesseract/testing/phototest.tif");

        PIX PIXimage = null;
        PIXimage = pixRead(image.getName());
        api.SetImage(PIXimage);
        // Get OCR result
        outText = api.GetUTF8Text();
        System.out.println("OCR output:\n" + outText.getString());

        // Destroy used object and release memory
        api.End();
        outText.deallocate();
        pixDestroy(PIXimage);
        return null;
    }

    private void loadLibToPath() {
        try {
            Loader.load(TessBaseAPI.class);
        } catch (UnsatisfiedLinkError e) {
            String path = null;
            try {
                path = Loader.cacheResource(TessBaseAPI.class, "windows-x86_64/jniTessBaseAPI.dll").getPath();
                new ProcessBuilder(lucasg_path+"/DependenciesGui.exe", path).start().waitFor();
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
