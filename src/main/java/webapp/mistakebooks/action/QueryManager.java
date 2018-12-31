package webapp.mistakebooks.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webapp.mistakebooks.model.UserEntity;
import webapp.mistakebooks.service.AppService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/Manager")
public class QueryManager {
    private static final long serialVersionUID = 1L;
    private String id;
    private String password;
    @Autowired
    private AppService appService;
    @RequestMapping("/Login")
    protected void Query(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {
        response.setContentType("text/plain;charset=UTF-8");
        id = new String(request.getParameter("name").
                getBytes("ISO8859-1"), "UTF-8");
        password = new String(request.getParameter("pwd").
                getBytes("ISO8859-1"), "UTF-8");
        UserEntity user = new UserEntity(id, password);
        PrintWriter out = response.getWriter();
        String result = appService.queryManager(user);
        out.print(result);
    }
}

