package resources.news;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import resources.projects.ProjectsAction;

/**
 * Created by root on 10.9.16.
 */
@Result(type = "json")
public class NewsByIdAction extends ActionSupport {
    private static final long serialVersionUID = 9037336532369476225L;
    private static final Logger log = LogManager.getLogger(ProjectsAction.class);

    private Integer id;
    private News news;

    public String editNew() throws Exception {
        News news = NewsService.getById(this.getId());
        this.setNews(news);

        return SUCCESS;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public News getNews() {
        return this.news;
    }

    public void setNews(News n) {
        this.news = n;
    }
}