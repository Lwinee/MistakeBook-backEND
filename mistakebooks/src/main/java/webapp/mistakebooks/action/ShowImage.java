package webapp.mistakebooks.action;

import com.mongodb.*;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import webapp.mistakebooks.service.AppService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/CRUD")
public class ShowImage {
    @Autowired
    private AppService appService;
    @RequestMapping(value = "/image")
    private void processSendPic(
                                @RequestParam("mistakeID") String fileName,
                                @RequestParam("files[]") MultipartFile file,
                                HttpServletRequest request,
                                HttpServletResponse response)
            throws IOException
    {
        response.setCharacterEncoding("utf-8");
        response.setContentType("image/*");
        System.out.println("file: "+fileName);
        PrintWriter out = response.getWriter();
        String headImg;
        if (file != null && !file.isEmpty()) {
            headImg = file.getOriginalFilename();
            // 构建上传目录及文件对象，不存在则自动创建
            String path = "C:\\webImages\\";
            File imgFile = new File(path, headImg);
            file.transferTo(imgFile);
            String result = appService.uploadPic(imgFile, fileName);
            out.print(result);
        }
    }

    @RequestMapping(value = "/getPic")
    private void processSendPic(
                                @RequestParam("mistakeID") String fileName,
                                HttpServletRequest request,
                                HttpServletResponse response)
            throws IOException
    {

        response.setCharacterEncoding("utf-8");
        response.setContentType("image/*");
        OutputStream out = response.getOutputStream();

        //GridFSDBFile result = appService.getPic(fileName);

        MongoClient mongo = new MongoClient();
        DB mongodb = mongo.getDB("Mistake");

        GridFS gfsPhoto = new GridFS(mongodb,"Images");
        // get image file by it's filename
        GridFSDBFile imageForOutput = gfsPhoto.findOne(fileName);

        imageForOutput.writeTo(out);
        out.flush();
        out.close();
        mongo.close();
    }
}
