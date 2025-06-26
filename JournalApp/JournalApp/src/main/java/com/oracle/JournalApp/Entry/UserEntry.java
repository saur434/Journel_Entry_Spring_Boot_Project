package com.oracle.JournalApp.Entry;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@Builder
public class UserEntry {

    @Id
    private ObjectId id;

    @NonNull
    @Indexed(unique = true)
    private String userName;

    private String email;
    private boolean sentimentanalysis;

    @NonNull
    private String password;
    @DBRef
    List<JournelEntry> journelEntries = new ArrayList<>();

    private List<String> roles;
}
