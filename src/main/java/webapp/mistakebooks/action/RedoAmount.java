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
public class RedoAmount {
    @Autowired
    private AppService appService;

    @RequestMapping("/RedoAmount")
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String userID = new String(request.getParameter("name").
                getBytes("ISO8859-1"), "UTF-8");
        PrintWriter out = response.getWriter();
        JSONArray result=appService.listRedoAmount(userID);
        out.print(result);
    }

}
