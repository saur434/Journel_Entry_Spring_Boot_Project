package com.oracle.JournalApp.Repository;


import org.bson.types.ObjectId;
import com.oracle.JournalApp.Entry.configJournelAppEntry;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface configJournelAppRepo extends MongoRepository<configJournelAppEntry, ObjectId> {


}
