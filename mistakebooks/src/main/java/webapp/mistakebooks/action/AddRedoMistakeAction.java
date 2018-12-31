package webapp.mistakebooks.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webapp.mistakebooks.model.RedomistakeEntity;
import webapp.mistakebooks.service.AppService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@RestController
@RequestMapping("/CRUD")
public class AddRedoMistakeAction {
    private String userId;
    private int mistakeId;
    private String answer;
    @Autowired
    private AppService appService;

    @RequestMapping("/Redo")
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        answer = request.getParameter("answer");
        String mistakeID = request.getParameter("mistakeID");
        userId = request.getParameter("name");
        mistakeId = Integer.parseInt(mistakeID);

        RedomistakeEntity redo=new RedomistakeEntity(userId,mistakeId,answer);

        String result= appService.addRedo(redo);
        out.print(result);


    }


}
