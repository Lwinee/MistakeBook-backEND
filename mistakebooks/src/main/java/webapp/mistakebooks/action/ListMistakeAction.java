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
@RequestMapping("/CRUD")
public class ListMistakeAction {
    private String userId;

    @Autowired
    private AppService appService;

    @RequestMapping("/List")
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        userId = request.getParameter("name");
        System.out.println("user: "+userId);
        JSONArray result=appService.listMistake(userId);
        out.print(result);
    }
}
