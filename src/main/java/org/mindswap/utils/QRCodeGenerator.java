package org.mindswap.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.mindswap.model.Invoice;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeGenerator {

    public static void generateQRCode(Invoice invoice){
        String qrCodePath = "/Users/miguelaguiar/Documents/QRCodes";
        String qrCodeName = qrCodePath + invoice.getId() + "QRCODE.png";

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode("ID: " + invoice.getId() + "\n" +
                                                "Price: " + invoice.getPrice() + "\n" +
                                                "Items: " + invoice.getRental() + "\n" +
                                                "Worker: " + invoice.getWorker(),
                    BarcodeFormat.QR_CODE, 400, 400);

            Path path = FileSystems.getDefault().getPath(qrCodeName);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
