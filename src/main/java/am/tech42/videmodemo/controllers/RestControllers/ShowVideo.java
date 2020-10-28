package am.tech42.videmodemo.controllers.RestControllers;

import am.tech42.videmodemo.MultipartFileSender.MultipartFileSender;
import am.tech42.videmodemo.model.Video.Video;
import am.tech42.videmodemo.services.VideoService;
import com.google.common.io.Files;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class ShowVideo {
    @Autowired
    VideoService videoService;
    @GetMapping("/videos/{id}/showvideo")
    public void showVideo(@PathVariable int id, HttpServletResponse response, HttpServletRequest request) throws Exception {
        response.setContentType("video/mp4");
        Video video= videoService.getById(id);
        MultipartFileSender mfs = MultipartFileSender.fromURIString(video.getSrc());
        mfs.with(response);
        mfs.with(request);
        mfs.serveResource();
    }
}
