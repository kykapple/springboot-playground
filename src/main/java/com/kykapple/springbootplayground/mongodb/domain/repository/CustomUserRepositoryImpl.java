package com.kykapple.springbootplayground.mongodb.domain.repository;

import com.kykapple.springbootplayground.mongodb.domain.User;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.time.LocalDateTime;
import java.util.List;

public class CustomUserRepositoryImpl implements CustomUserRepository {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public User findAndUpdateUserAge(String userName, int age) {
        Update update = new Update();
        update.set("age", age);
        FindAndModifyOptions findAndModifyOptions = FindAndModifyOptions.options()
                .returnNew(true);

        User modifiedUser = mongoOperations.findAndModify(
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

        UpdateResult count = mongoOperations.updateMulti(
                Query.query(Criteria.where("name").is(userName)),
                update,
                User.class
        );

        return count.getModifiedCount();
    }

    public User removeUser(String userId) {
        User removedUser = mongoOperations.findAndRemove(
                Query.query(Criteria.where("_id").is(userId)),
                User.class
        );

        return removedUser;
    }

    public void groupBy(String name) {
        MatchOperation matchOperation = Aggregation.match(Criteria.where("name").is(name));
        GroupOperation groupOperation = Aggregation.group("name")
                .sum("amount").as("totalAmount");

        AggregationResults<User> aggregate = mongoOperations.aggregate(Aggregation.newAggregation(matchOperation, groupOperation), User.class, User.class);

        aggregate.getMappedResults();
    }

    @Override
    public List<User> findUserByRegisteredAtLeastOneHour(LocalDateTime current) {
        return mongoOperations.find(Query.query(Criteria.where("registeredAt").lte(current)), User.class);
    }


}
