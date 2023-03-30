package org.mindswap.utils.PDF;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.transaction.Transactional;
import org.mindswap.model.Invoice;
import org.mindswap.model.Movie;
import org.mindswap.repository.MovieRepository;
import org.mindswap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PdfGenerator {

    private BaseFont bfBold;
    private BaseFont bf;
    private int pageNumber = 0;

    private String clientName;

    private String startDate;
    private String endDate;
    private String storeCity;
    private String storeAddress;

    private String invoiceId;
    private List<Movie> movieList;
    private double totalPrice;
    private UserRepository userRepository;
    private MovieRepository movieRepository;


    @Autowired
    public PdfGenerator(BaseFont bfBold, BaseFont bf, UserRepository userRepository, MovieRepository movieRepository) {
        this.bfBold = bfBold;
        this.bf = bf;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    @Transactional
    public void createPDF(String pdfFilename, Invoice invoice) {

        Document doc = new Document();
        PdfWriter docWriter = null;
        initializeFonts();

        this.clientName = invoice.getRental().getUser().getFirstName() + " " + invoice.getRental().getUser().getLastName() ;
        this.invoiceId = invoice.getId().toString();
        this.startDate = invoice.getRental().getStartDate().toString();
        this.endDate = invoice.getRental().getEndDate().toString();
        this.storeCity = invoice.getStore().getCity();
        this.storeAddress = invoice.getStore().getAddress();
        this.movieList = invoice.getRental().getMovies();



        try {
            String path = "/Users/ruirajao/Desktop/GroupProject_Backend/src/main/resources/invoices/".concat(pdfFilename);
            docWriter = PdfWriter.getInstance(doc, new FileOutputStream(path));
            doc.addAuthor("betterThanZero");
            doc.addCreationDate();
            doc.addProducer();
            doc.addCreator("MindSwappers");
            doc.addTitle("Invoice");
            doc.setPageSize(PageSize.LETTER);

            doc.open();
            PdfContentByte cb = docWriter.getDirectContent();

            boolean beginPage = true;
            int y = 0;

            for (Movie movie : movieList) {
                String id = movie.getId().toString();
                String title = movie.getTitle();
                String genre = movie.getMovieGenre();
                double rating = movie.getImdbRating();
                double price = movie.getPrice();

                totalPrice += price;
                System.out.println(totalPrice);


                if (beginPage) {
                    beginPage = false;
                    generateLayout(doc, cb);
                    //generateHeader(doc, cb);
                    y = 615;
                }
                generateDetail(doc, cb, y, id,title,genre,price,rating);
                y = y - 15;
                if (y < 50) {
                    printPageNumber(cb);
                    doc.newPage();
                    beginPage = true;
                }
            }
            generateHeader(doc, cb);

            printPageNumber(cb);

        } catch (DocumentException dex) {
            dex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (doc != null) {
                doc.close();
            }
            if (docWriter != null) {
                docWriter.close();
            }
        }
    }

    public void generateLayout(Document doc, PdfContentByte cb) {

        try {

            cb.setLineWidth(1f);

            // Invoice Header box layout
            cb.rectangle(400, 680, 170, 100);
            cb.moveTo(400, 740);
            cb.lineTo(570, 740);
            cb.moveTo(400, 760);
            cb.lineTo(570, 760);
            cb.moveTo(480, 720);
            cb.lineTo(480, 780);
            cb.moveTo(400, 700);
            cb.lineTo(570, 700);
            cb.moveTo(400, 720);
            cb.lineTo(570, 720);
            cb.moveTo(480, 720);
            cb.lineTo(480, 700);
            cb.moveTo(400,680);
            cb.lineTo(570,680);
            cb.moveTo(480,680);
            cb.lineTo(480,700);

            cb.stroke();

            // Invoice Header box Text Headings
            createHeadings(cb, 402, 763, "Client Name");
            createHeadings(cb, 402, 743, "Invoice Id");
            createHeadings(cb, 402, 723, "Rental Start Date");
            createHeadings(cb, 402, 703, "Rental End Date");
            createHeadings(cb, 402, 683, "Total price");

            // Invoice Detail box layout
            cb.rectangle(20, 50, 550, 600);
            cb.moveTo(20, 630);
            cb.lineTo(570, 630);
            cb.moveTo(50, 50);
            cb.lineTo(50, 650);
            cb.moveTo(150, 50);
            cb.lineTo(150, 650);
            cb.moveTo(430, 50);
            cb.lineTo(430, 650);
            cb.moveTo(500, 50);
            cb.lineTo(500, 650);
            cb.stroke();

            // Invoice Detail box Text Headings
            createHeadings(cb, 22, 633, "Id");
            createHeadings(cb, 52, 633, "Genre");
            createHeadings(cb, 152, 633, "Movie Title");
            createHeadings(cb, 432, 633, "Rating");
            createHeadings(cb, 502, 633, "Price");

            //add the images
            Image companyLogo = Image.getInstance("src/main/resources/images/movie.jpeg");
            companyLogo.setAbsolutePosition(17, 690);
            companyLogo.scalePercent(25);
            doc.add(companyLogo);


            //add qrCode
            //Image qrcode = Image.getInstance("src/main/resources/images/QRCodes/" + invoiceId + "QRCODE.png");
            Image qrcode = Image.getInstance("src/main/resources/QRCodes/".concat("InvoiceQRCODE").concat(invoiceId).concat(".png"));
            qrcode.setAbsolutePosition(260, 670);
            qrcode.scalePercent(25);
            doc.add(qrcode);
        } catch (DocumentException dex) {
            dex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void generateHeader(Document doc, PdfContentByte cb) {

        try {

            createHeadings(cb, 160, 750, "MOVIE TOWN");
            createHeadings(cb, 160, 735, storeAddress);
            createHeadings(cb, 160, 720, storeCity);
            createHeadings(cb, 160, 705, "Portugal");

            createHeadings(cb, 482, 763, clientName);
            createHeadings(cb, 482, 743, invoiceId);
            createHeadings(cb, 482, 723, startDate);
            createHeadings(cb, 482, 703, endDate);
            createHeadings(cb, 482, 683, totalPrice +"€");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void generateDetail(Document doc, PdfContentByte cb, int y,String id,String title, String genre, double price,double rating) {
        DecimalFormat df = new DecimalFormat("0.00");

        try {

            createContent(cb, 48, y, id, PdfContentByte.ALIGN_RIGHT);
            createContent(cb, 52, y, genre, PdfContentByte.ALIGN_LEFT);
            createContent(cb, 152, y, title, PdfContentByte.ALIGN_LEFT);

            createContent(cb, 498, y, df.format(rating), PdfContentByte.ALIGN_RIGHT);
            createContent(cb, 568, y, df.format(price), PdfContentByte.ALIGN_RIGHT);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void createHeadings(PdfContentByte cb, float x, float y, String text) {


        cb.beginText();
        cb.setFontAndSize(bfBold, 8);
        cb.setTextMatrix(x, y);
        cb.showText(text.trim());
        cb.endText();

    }

    public void printPageNumber(PdfContentByte cb) {


        cb.beginText();
        cb.setFontAndSize(bfBold, 8);
        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Page No. " + (pageNumber + 1), 570, 25, 0);
        cb.endText();

        pageNumber++;

    }

    public void createContent(PdfContentByte cb, float x, float y, String text, int align) {


        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.showTextAligned(align, text.trim(), x, y, 0);
        cb.endText();

    }

    public void initializeFonts() {


        try {
            bfBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}