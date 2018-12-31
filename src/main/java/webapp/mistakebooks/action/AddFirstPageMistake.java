package webapp.mistakebooks.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webapp.mistakebooks.model.MistakeEntity;
import webapp.mistakebooks.service.AppService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/Manager")
public class AddFirstPageMistake {
    @Autowired
    private AppService appService;
    @RequestMapping("/AddPageMistake")
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String mistakeID = request.getParameter("id");
        Integer mistakeid=Integer.parseInt(mistakeID);

        String result= appService.addPageMistake(mistakeid);
        out.print(result);
    }
}
