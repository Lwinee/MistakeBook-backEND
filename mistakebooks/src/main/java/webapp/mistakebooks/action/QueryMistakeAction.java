package webapp.mistakebooks.action;

import net.sf.json.JSONObject;
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
public class QueryMistakeAction {
    private int mistakeId;
    @Autowired
    private AppService appService;

    @RequestMapping("/SearchMistake")
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        int mistakeID = Integer.parseInt(id);
        JSONObject result= appService.searchMistake(mistakeID);
        out.print(result);
    }


}
