package com.oracle.JournalApp.Repository;

import com.oracle.JournalApp.Entry.UserEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.util.List;

public class UserRepoImpl {

    @Autowired
private MongoTemplate mongoTemplate;

    public List<UserEntry>  getSAList(){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"));
        query.addCriteria(Criteria.where("sentimentanalysis").is("true"));
        List<UserEntry>users = mongoTemplate.find(query, UserEntry.class);
        return users;
    }
}
