package com.kykapple.springbootplayground.mongodb.domain.repository;

import com.kykapple.springbootplayground.mongodb.domain.User;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class CustomUserRepositoryImpl implements CustomUserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User findAndUpdateUserAge(String userName, int age) {
        Update update = new Update();
        update.set("age", age);
        FindAndModifyOptions findAndModifyOptions = FindAndModifyOptions.options()
                .returnNew(true);

        User modifiedUser = mongoTemplate.findAndModify(
                Query.query(Criteria.where("name").is(userName)),
                update,
                findAndModifyOptions,
                User.class
        );

        return modifiedUser;
    }

    @Override
    public long updateAllUserAge(String userName, int age) {
        Update update = new Update();
        update.set("age", age);

        UpdateResult count = mongoTemplate.updateMulti(
                Query.query(Criteria.where("name").is(userName)),
                update,
                User.class
        );

        return count.getModifiedCount();
    }

    public User removeUser(String userId) {
        User removedUser = mongoTemplate.findAndRemove(
                Query.query(Criteria.where("_id").is(userId)),
                User.class
        );

        return removedUser;
    }

}
