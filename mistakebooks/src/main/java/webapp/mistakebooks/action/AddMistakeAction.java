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
@RequestMapping("/CRUD")
public class AddMistakeAction {

    private String mistakeTitle;
    private String mistakeCause;
    private String userId;
    private String subject;
    private String tag;

    @Autowired
    private AppService appService;

    @RequestMapping("/AddMistake")
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        mistakeTitle = request.getParameter("title");
        System.out.println(" mistakeTitle ; "+ mistakeTitle);
        mistakeCause = request.getParameter("cause");
        userId = request.getParameter("name");
        subject = request.getParameter("subject");
        tag = request.getParameter("tag");


        MistakeEntity mistake=new MistakeEntity(userId,mistakeTitle,mistakeCause,subject,tag);
        String result= appService.addMistake(mistake);
        out.print(result);


    }

}
