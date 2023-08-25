package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image=new Image(description,dimensions);
        Optional<Blog>optionalBlog=blogRepository2.findById(blogId);
        Blog blog=optionalBlog.get();
        image.setBlog(blog);
        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){

        if(blogRepository2.existsById(id)){
            blogRepository2.deleteById(id);
        }

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        Image image=imageRepository2.findById(id).get();

        String imageDimensions=image.getDimensions();
        int indexX1=imageDimensions.indexOf('X');
        int iS=Integer.parseInt(imageDimensions.substring(0,indexX1));
        int iE=Integer.parseInt(imageDimensions.substring(indexX1+1,imageDimensions.length()));

        int indexX2=screenDimensions.indexOf('X');
        int SS=Integer.parseInt(screenDimensions.substring(0,indexX2));
        int ES=Integer.parseInt(screenDimensions.substring(indexX1+1,screenDimensions.length()));

  int ans=(int)(Math.floor((Double.valueOf(SS))/(Double.valueOf(iS)))*Math.floor((Double.valueOf(ES))/(Double.valueOf(iE))));
    return ans;
    }
}
