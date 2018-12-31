package webapp.mistakebooks.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webapp.mistakebooks.service.AppService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@RestController
@RequestMapping("/CRUD")
public class QueryTagTipAction {
    private static final long serialVersionUID = 1L;
    private String subject;
    @Autowired
    private AppService appService;

    @RequestMapping("/QueryTagTip")
    protected void Query(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        subject = request.getParameter("subject");
        String result = appService.queryTagtip(subject);
        out.print(result);
    }


}
