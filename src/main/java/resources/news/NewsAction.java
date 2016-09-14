package resources.news;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import resources.infrastructure.Auth;
import resources.infrastructure.ValidateResult;
import resources.users.User;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 13.9.16.
 */
@Result(type = "json")
public class NewsAction extends ActionSupport{
    private static final long serialVersionUID = 9037336532369476225L;
    private static final Logger log = LogManager.getLogger(NewsAction.class);

    private int id;
    private Integer companyId;

    private List<News> news;
    private String title;
    private String content;
    //private Integer amount;
    private boolean remove;

    private List<Map<String, String>> validateErrors;
    private NewsValidator validator = new NewsValidator();
    private NewsRemoveValidator removeValidator = new NewsRemoveValidator();

    public String execute() throws Exception {
        return this.create();
    }

    public String view() throws Exception {
        User user = Auth.getCurrentUser();
        if (user != null && user.getIsArchived()) {
            return SUCCESS;
        }

        if (this.getCompanyId() != null) {
            List<News> news = NewsService.getByCompanyId(this.getCompanyId());
            this.setNews(news);
        } else {
            List<News> news = NewsService.getAll();
            this.setNews(news);
        }

        return SUCCESS;
    }

    public String update() throws Exception {
        User user = Auth.getCurrentUser();
        if (user != null && user.getIsArchived()) {
            return SUCCESS;
        }

        if (this.getRemove()) {
            ValidateResult data = removeValidator.validate(this);

            if (data.hasErrors()) {
                this.setValidateErrors(data.errors);
                return SUCCESS;
            }

            NewsService.delete(this.getId());
        } else {
            ValidateResult data = validator.validate(this);

            if (data.hasErrors()) {
                this.setValidateErrors(data.errors);
                return SUCCESS;
            }

            NewsService.update(mapNews());
        }

        return SUCCESS;
    }

    public String create() throws Exception {
        User user = Auth.getCurrentUser();
        if (user != null && user.getIsArchived()) {
            return SUCCESS;
        }

        ValidateResult data = validator.validate(this);

        if (data.hasErrors()) {
            this.setValidateErrors(data.errors);
            return SUCCESS;
        }

        NewsService.save(mapNews());

        return SUCCESS;
    }

    private News mapNews() {
        //Project project = new Project();
        News news = new News();
        news.setId(this.getId());
        news.setTitle(this.getTitle());
        news.setContent(this.getContent());
        //project.setAmount(this.getAmount());
        news.setCompanyId(this.getCompanyId());

        return news;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(Integer id) {
        this.companyId = id;
    }

    public boolean getRemove() {
        return this.remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

  /* public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
*/
    public void setNews(List<News> news) {
        this.news = news;
    }

    public List<News> getNews() {
        return this.news;
    }

    public void setValidateErrors(List<Map<String, String>> errors) {
        this.validateErrors = errors;
    }

    public List<Map<String, String>> getValidateErrors() {
        return this.validateErrors;
    }
}
