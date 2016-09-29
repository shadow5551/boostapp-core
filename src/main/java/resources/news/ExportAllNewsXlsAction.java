package resources.news;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by root on 15.9.16.
 */
public class ExportAllNewsXlsAction extends ActionSupport {
    private int id;

    private Integer userId;
    private Integer companyId;
    private InputStream inputStream;

    public String view() throws Exception {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Export News");

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);


        cell.setCellValue("Новости различных компаний с полным описанием");

        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue("id");
        cell = row.createCell(1);
        cell.setCellValue("title");
        cell = row.createCell(2);
        cell.setCellValue("description");

        List<News> news = NewsService.getAll();

        int rowNum = 1;
        for(News new1 : news) {
            row = sheet.createRow(++rowNum);
            cell = row.createCell(0);
            cell.setCellValue(new1.getId());
            cell = row.createCell(1);
            cell.setCellValue(new1.getTitle());
            cell = row.createCell(2);
            cell.setCellValue(new1.getContent());
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        setInputStream(new ByteArrayInputStream(baos.toByteArray()));

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

