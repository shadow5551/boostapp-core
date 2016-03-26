package resources.projects;

import java.sql.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

@Result(type = "json")
public class ProjectsAction extends ActionSupport {

    private static final long serialVersionUID = 9037336532369476225L;
    private static final Logger log = LogManager.getLogger(ProjectsAction.class);

    private List<String> projectNames;

    public String execute() throws Exception {
        this.view();
        return SUCCESS;
    }

    public String view() throws Exception {
        Connection conn = null;
        Statement stmt = null;

        projectNames = new ArrayList<String>();

        String DB_URL = "jdbc:mysql://localhost:3306/boostapp-core";

        conn = DriverManager.getConnection(DB_URL, "root", "root");

        stmt = conn.createStatement();

        String sql = "SELECT * FROM projects";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            String title = rs.getString("title");
            projectNames.add(title);
        }

        rs.close();

        return SUCCESS;
    }

    public List<String> getProjectNames() {
        return projectNames;
    }
}
