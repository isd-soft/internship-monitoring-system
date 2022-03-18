package org.inthergroup.ims.candidate.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Base64;
import static java.util.Base64.getEncoder;

@Service
public class EncodeAndDecodePdf {

    // EncodePdf(pdfPath"D:\test.txt");
    @Value("${pdf.location}")
    private String userBucketPath;

    private static String encodePdf(String pdfPath, String savePath) throws Exception {
        FileInputStream pdfStream = new FileInputStream(pdfPath);
        byte[] data = pdfStream.readAllBytes();
        String pdfString = getEncoder().encodeToString(data);

        FileWriter fileWriter = new FileWriter(savePath);

        fileWriter.write(pdfString);
        fileWriter.close();
        pdfStream.close();
        return pdfString;
    }

    private static void decodePdf(String txtPath, String savePath) throws Exception {
        FileInputStream inputStream = new FileInputStream(txtPath);
        Base64.getDecoder().decode(new String(inputStream.readAllBytes()));
    }
}
