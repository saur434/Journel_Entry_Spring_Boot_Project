package com.oracle.JournalApp.Repository;

import com.oracle.JournalApp.Entry.UserEntry;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;


public interface UserRepo extends MongoRepository<UserEntry, ObjectId> {

    UserEntry findByuserName(String userName);

    void deleteByUserName(String userName);
}
