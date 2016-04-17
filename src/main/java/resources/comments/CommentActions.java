package resources.comments;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Result;
import resources.companies.Company;
import resources.infrastructure.ValidateResult;
import resources.projects.Project;
import resources.projects.ProjectValidator;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Result(type = "json")
public class CommentActions extends ActionSupport {
    private int id;

    private List<Comment> comments;
    private Boolean remove;
    private int userId;
    private String commentText;
    private int projectId;

    private List<Map<String, String>> validateErrors;
//    private CommentValidator validator = new CommentValidator();

    public String execute() throws Exception {
        return this.create();
    }

    public String view() throws Exception {
        List<Comment> comments = CommentService.getAllByProjectId(this.getProjectId());
        this.setComments(comments);

        return SUCCESS;
    }

    public String create() throws Exception {
//        ValidateResult data = validator.validate(this);
//
//        if (data.hasErrors()) {
//            this.setValidateErrors(data.errors);
//            return SUCCESS;
//        }

        Comment comment = CommentService.save(mapComment());

        return SUCCESS;
    }

    private Comment mapComment() {
        Comment comment = new Comment();

        comment.setId(this.getId());
        comment.setProjectId(this.getProjectId());
        comment.setCommentText(this.getCommentText());
        comment.setCreatedOn(new Date());

        return comment;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getRemove() {
        return this.remove;
    }

    public void setRemove(Boolean remove) {
        this.remove = remove;
    }

    public String getCommentText() {
        return this.commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getProjectId() {
        return this.projectId;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setValidateErrors(List<Map<String, String>> errors) {
        this.validateErrors = errors;
    }

    public List<Map<String, String>> getValidateErrors() {
        return this.validateErrors;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }
}
