package com.baixiang.controller;

import com.baixiang.model.jpa.Image;
import com.baixiang.model.response.BaseBean;
import com.baixiang.model.response.Response;
import com.baixiang.service.ImageService;
import com.baixiang.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static com.baixiang.config.PropertiesConfig.POSTER_PATH;
import static com.baixiang.config.PropertiesConfig.SCREEN_SHOT_PATH;
import static com.baixiang.utils.Urls.API_UPLOAD_IMAGE;

/**
 * Created by shenjiajun on 2017/10/23.
 */
@RestController
public class ImageController {
    @Autowired
    ImageService imageService;

    @RequestMapping(value = API_UPLOAD_IMAGE, method = RequestMethod.POST)
    public Response uploadImage(@RequestParam(value = "imageFile", required = true) MultipartFile imageFile,
                                @RequestParam(value = "type", required = true) String type) {
        if (null != imageFile) {
            Image image = new Image();
            String imageFileName;
            if (type.equals(Image.TYPE_SCREENSHOT)) {
                imageFileName = saveFile(imageFile, SCREEN_SHOT_PATH);
                if (!imageFileName.equals(null)) {
                    image.setUrl(SCREEN_SHOT_PATH + imageFileName);
                    image.setImageName(imageFileName);
                }
                image.setType(Image.TYPE_SCREENSHOT);
            } else if (type.equals(Image.TYPE_POSTER)) {
                imageFileName = saveFile(imageFile, POSTER_PATH);
                if (!imageFileName.equals(null)) {
                    image.setUrl(POSTER_PATH + imageFileName);
                    image.setImageName(imageFileName);
                }
                image.setType(Image.TYPE_POSTER);
            }
            image = imageService.save(image);
            if (image.getId() != 0) {
                return new Response<Image>(image, null);
            }
        }
        return new Response<BaseBean>(new BaseBean(2), null);
    }


    private String saveFile(MultipartFile multipartFile, String dirPath) {
        if (null != multipartFile && !multipartFile.isEmpty()) {
            try {
                System.out.printf("filename=" + multipartFile.getOriginalFilename());
                String fileName = System.currentTimeMillis() + "-" + multipartFile.getOriginalFilename();
                String filePath = FileUtil.getFilePath(dirPath, fileName);
                File file = new File(filePath);
                multipartFile.transferTo(file);
                return fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
