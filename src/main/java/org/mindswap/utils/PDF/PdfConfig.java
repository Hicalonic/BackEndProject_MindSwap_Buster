package org.mindswap.utils.PDF;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.itextpdf.text.pdf.BaseFont;

@Configuration
public class PdfConfig {

    @Bean
    public BaseFont baseFont() throws Exception {
        String fontPath = BaseFont.HELVETICA;
        String encoding = BaseFont.CP1252;
        boolean embedded = BaseFont.EMBEDDED;
        return BaseFont.createFont(fontPath, encoding, embedded);
    }
}