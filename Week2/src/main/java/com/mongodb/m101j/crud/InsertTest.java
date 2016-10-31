/*
 * Copyright 2015 MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mongodb.m101j.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.m101j.util.Helpers.printJson;
import static java.util.Arrays.asList;

public class InsertTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("insertTest");

        coll.drop();

        Document smith = new Document("name", "Smith")
                         .append("age", 30)
                         .append("profession", "programmer");

        Document jones = new Document("name", "Jones")
                         .append("age", 25)
                         .append("profession", "hacker");

        printJson(smith);
//Document is displayed without _id

        coll.insertOne(smith);
        coll.insertOne(jones);


        printJson(smith);
        printJson(jones);
//        Document is displayed With _id because the driver took care of it.

        coll.drop();

        coll.insertMany(asList(smith, jones));

        printJson(smith);
        printJson(jones);
    }
}
