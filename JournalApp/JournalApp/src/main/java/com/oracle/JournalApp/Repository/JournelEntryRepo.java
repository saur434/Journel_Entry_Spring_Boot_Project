package com.oracle.JournalApp.Repository;


import com.oracle.JournalApp.Entry.JournelEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournelEntryRepo extends MongoRepository<JournelEntry, ObjectId>
{

}
