package com.dbs.itt.csfa.OCR;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.tesseract.TessBaseAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class OcrApplication {



	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(OcrApplication.class, args);
	}

}
