package com.example.contentcalendar.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotEmpty;

import com.example.contentcalendar.model.Content;
import com.example.contentcalendar.model.Status;
import com.example.contentcalendar.model.Type;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public class ContentCollectionRepositiory {
   private List<Content> contentList = new ArrayList();

   public List<Content> findall(){
      return contentList;
   }
   public ContentCollectionRepositiory(){

   }

   public Optional<Content> findById(Integer id){
      return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
   }

   @PostConstruct
   private void init(){
      Content c = new Content(
            1,
      "This is End",
            "There is nothing to Show ",
            Status.IDEA,
            Type.ARTICLE,
            LocalDateTime.now(),
            null,
            "");
      contentList.add(c);
   }

   public void save(Content content){
      contentList.removeIf(c-> c.id().equals(content.id()));
      contentList.add(content);
      System.out.println("Reached at this position");
   }

   public boolean existById(Integer id){
      return contentList.stream().filter(c -> c.id().equals(id)).count() == 1;
   }

   public void delete(Integer id){
      contentList.removeIf(c-> c.id().equals(id));
   }

}
