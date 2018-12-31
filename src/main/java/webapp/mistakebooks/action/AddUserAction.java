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
@RequestMapping("/User")
public class AddUserAction  {
    private static final long serialVersionUID = 1L;
    private String id;
    private String password;
    private String phone;
    private String email;
    private Byte valid;
    private Byte role;


    @Autowired
    private AppService appService;
    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @RequestMapping("/Register")
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        id = new String(request.getParameter("name")
                .getBytes("ISO8859-1"), "UTF-8");
        password = new String(request.getParameter("pwd")
                .getBytes("ISO8859-1"), "UTF-8");
        phone = new String(request.getParameter("phone")
                .getBytes("ISO8859-1"), "UTF-8");
        email = new String(request.getParameter("email")
                .getBytes("ISO8859-1"), "UTF-8");
        String roleSend=new String(request.getParameter("role")
                .getBytes("ISO8859-1"), "UTF-8");
        valid = 1;
        if(roleSend.equals('1')){role=1;}
        else{role=0;}

        System.out.println("name: "+id);

        UserEntity user = new UserEntity(id, password, phone, email, valid,role);
        Integer result= appService.addUser(user);
        if(result==1){
            out.print("添加成功");
        }
        else{
            out.print("用户名已存在");
        }
    }

}
