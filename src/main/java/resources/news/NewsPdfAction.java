package resources.news;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;

/**
 * Created by root on 15.9.16.
 */
public class NewsPdfAction extends ActionSupport {
    private static final String USER_PASSWORD = "123";
    private static final String OWNER_PASSWORD = "1";

    private int id;

    private Integer newsId;
    private InputStream inputStream;

    public String view() throws Exception{

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        Document document = new Document();

       // PdfWriter.getInstance(document, buffer);


        PdfWriter writer = PdfWriter.getInstance(document, buffer);
        writer.setEncryption(USER_PASSWORD.getBytes(), OWNER_PASSWORD.getBytes(),PdfWriter.ALLOW_ASSEMBLY | PdfWriter.ALLOW_COPY, PdfWriter.STANDARD_ENCRYPTION_128);
        writer.createXmpMetadata();

        document.open();
        Paragraph p = new Paragraph();
        Chunk tagline = new Chunk("Boost App - Best Crowdfunding platform ever!");
        tagline.setUnderline(0.1f, -2f);
        tagline.setLineHeight(16);
        p.add(tagline);
        p.setAlignment(Element.ALIGN_RIGHT);
        document.add(p);

        Image logo = Image.getInstance("src/main/webapp/img/logo.png");
        logo.setAlignment(Element.ALIGN_RIGHT);
        document.add(logo);

        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        News news = NewsService.getById(this.getNewsId());

        if (news!= null) {
            Paragraph par = new Paragraph();
            Chunk label = new Chunk("                                                     " +
                    "Here is news "+ news.getTitle() + " invoice"
            );

            par.add(label);
            par.setAlignment(Element.ALIGN_MIDDLE);
            document.add(par);

            document.add(new Paragraph(" "));

            Paragraph par1 = new Paragraph();
            Chunk label2 = new Chunk(
                    news.getContent()
            );

            par1.add(label2);
            par1.setAlignment(Element.ALIGN_MIDDLE);
            document.add(par1);

            document.add(new Paragraph(""));
            document.add(new Paragraph("Invoice generated at: " + new Date()));
        }

        document.close();

        inputStream = new ByteArrayInputStream(buffer.toByteArray());


        return SUCCESS;
    }


    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getNewsId() {
        return this.newsId;
    }
}
