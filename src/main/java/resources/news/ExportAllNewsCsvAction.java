package resources.news;

import com.opensymphony.xwork2.ActionSupport;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by root on 15.9.16.
 */
public class
ExportAllNewsCsvAction extends ActionSupport {
    private int id;

    private Integer userId;
    private Integer companyId;
    private InputStream inputStream;

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "id,companyId,title,content";

    public String view() throws Exception {

        DataOutputStream buffer = new DataOutputStream(new FileOutputStream("report.csv"));

        List<News> news = NewsService.getAll();
        if (news != null) {
            buffer.writeBytes(FILE_HEADER);
            buffer.writeBytes(NEW_LINE_SEPARATOR);

            for (News news1 : news) {
                if (news1 != null) {
                    buffer.writeBytes("" + news1.getId());
                    buffer.writeBytes(COMMA_DELIMITER);
                    buffer.writeBytes(""+news1.getCompanyId());
                    buffer.writeBytes(COMMA_DELIMITER);
                    if (news1.getTitle().contains("\"")) {
                       buffer.writeBytes(""+ "\"" + news1.getTitle()+ "\"" + "\"");
                    }
                    else
                    {
                        buffer.writeBytes("" + "\"" + news1.getTitle() + "\"");
                    }
                    buffer.writeBytes(COMMA_DELIMITER);
                    if (news1.getContent().contains("\"")) {
                        buffer.writeBytes("" + "\"" + news1.getContent() +"\"" +"\"");
                    }
                    else {
                        buffer.writeBytes("" + "\"" + news1.getContent() + "\"");
                    }
                    buffer.writeBytes(COMMA_DELIMITER);
                    buffer.writeBytes(NEW_LINE_SEPARATOR);
                }
            }

        }

        setInputStream(new FileInputStream("report.csv"));

        return SUCCESS;
    }


    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Integer getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}