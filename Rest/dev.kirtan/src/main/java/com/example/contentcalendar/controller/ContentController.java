package com.example.contentcalendar.controller;

import jakarta.validation.Valid;

import com.example.contentcalendar.model.Content;
import com.example.contentcalendar.repository.ContentCollectionRepositiory;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {

   private final ContentCollectionRepositiory contentCollectionRepositiory;

   public ContentController(ContentCollectionRepositiory contentCollectionRepositiory){
      this.contentCollectionRepositiory = contentCollectionRepositiory;
   }

   @GetMapping("")
   public List<Content> findall(){
      return contentCollectionRepositiory.findall();
   }

   @GetMapping("/{id}")
   public Content findById(@PathVariable Integer id){
      return contentCollectionRepositiory.findById(id).orElseThrow(
            ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Content Not Found"));

   }

   @ResponseStatus(HttpStatus.CREATED)
   @PostMapping("")
   public void create(@Valid @RequestBody Content content){
      contentCollectionRepositiory.save(content);
   }

   @ResponseStatus(HttpStatus.NO_CONTENT)
   @PutMapping("/{id}")
   public void update(@RequestBody Content content, @PathVariable Integer id){
      if(contentCollectionRepositiory.existById(id)){
         throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Content Not Found");
      }
      contentCollectionRepositiory.save(content);
   }

   @ResponseStatus(HttpStatus.NO_CONTENT)
   @DeleteMapping("/{id}")
   public void delete(@PathVariable Integer id){
      contentCollectionRepositiory.delete(id);
   }




}
