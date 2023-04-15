package org.mindswap.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.transaction.Transactional;
import org.mindswap.exceptions.InvoiceNotFoundException;
import org.mindswap.mapper.InvoiceMapper;
import org.mindswap.model.Invoice;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Component
public class QRCodeGenerator {

    InvoiceMapper invoiceMapper;

    public QRCodeGenerator(InvoiceMapper invoiceMapper){
        this.invoiceMapper = invoiceMapper;
    }

    @Transactional
    public void generateQRCode(String qrcodeFileName, Invoice invoice){

        String qrCodePath = "/Users/ruirajao/Desktop/GroupProject_Backend/src/main/resources/QRCodes/".concat(qrcodeFileName);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode("ID: " + invoice.getId() + "\n" +
                                                "Price: " + invoice.getPrice() + "\n" +
                                                "Items: " + invoice.getRental() + "\n" +
                                                "NIF: " + "2616" + invoice.getRental().getUser().getId(),
                    BarcodeFormat.QR_CODE, 400, 400);

            Path path = FileSystems.getDefault().getPath(qrCodePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        } catch (WriterException | IOException e) {
            throw new InvoiceNotFoundException();
        }
    }

}
