package webapp.mistakebooks.action;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webapp.mistakebooks.service.AppService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/Manager")
public class TagRelatedMistake {
    private static final long serialVersionUID = 1L;
    @Autowired
    private AppService appService;
    @RequestMapping("/tagMistake")
    protected void Query(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String tag = request.getParameter("tag");
        JSONArray result=appService.listTagRelatedMistake(tag);
        out.print(result);
    }
}
