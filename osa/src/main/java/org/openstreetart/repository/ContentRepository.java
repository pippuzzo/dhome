package org.openstreetart.repository;

import org.springframework.stereotype.Repository;
import org.openstreetart.model.Content;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentRepository{

    private final List<Content> contentList = new ArrayList<>();

    public ContentRepository() {}

    public List<Content> findAll(){
        return contentList;
    }

    public Optional<Content> findById(Integer id){
        return contentList.stream().filter(c->c.id().equals(id)).findFirst();
    }

    public void add(Content content){
        contentList.add(content);
    }

    public void save(Content content) throws Exception {
        Optional<Content> contentToUpdate = findById(content.id());
        if(contentToUpdate.isPresent()){
            throw new Exception("Content ID già presente");
        }
        contentList.add(content);
    }

    public void update(Integer id, Content content) {
        for (int i = 0; i < contentList.size(); i++) {
            if(contentList.get(i).id().equals(id)){
                contentList.set(i,content);
            }
       }
    }

    public boolean isExist(Integer id){
        return contentList.stream().filter(c->c.id().equals(id)).count()==1;
    }

    public void delete(Integer id) {
        contentList.removeIf(c -> c.id().equals(id));
    }
}
