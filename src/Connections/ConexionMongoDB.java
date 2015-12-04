/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 package Connections;
 
/**
 *
 * @author Jesus
 */
 import com.mongodb.MongoClient;
 import com.mongodb.client.MongoDatabase;
 
 import org.bson.Document;
 import com.mongodb.Block;
 import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import model.Migration;
import model.MigrationDirectory;
import model.Value_Label;
 
/**
  *This class made the connection with MongoBD and create a new collection with image's information
  * @author Jesus Munoz
  */
 public class ConexionMongoDB {
 
     /**
      * @param args the command line arguments
      */
     private MongoClient mongoClient = null;
     private MongoDatabase db = null;
     private ArrayList<Migration> data = null;
     private String dbName;
     private ArrayList<Migration> rMigrationList = new ArrayList();
     private ArrayList<MigrationDirectory> rMigrationDirectoryList = new ArrayList();
 
 /**
  *  Class ConexionMongoDB' constructor
  * @param dbName Database name
  */
     public ConexionMongoDB(String dbName) {
         this.mongoClient = new MongoClient();
         this.db = mongoClient.getDatabase(dbName);
         this.data = data;
         this.dbName = dbName;
     }
     /**
      * Function return ArrayList of Migration's object
      * @param data 
      */
     public void setMigrationData(ArrayList<Migration>data) {
         this.data = data;
     }
     /**
      * 
      * @param collName 
      */
     public void listAllfromCollect(String collName) {
 
         FindIterable<org.bson.Document> iterable = this.db.getCollection(collName).find(); //Buscamos el documento con id: Asus
 
         iterable.forEach(new Block<Document>() {
             @Override
             public void apply(final Document document) { //Necesitamos comprobar si para ese ID el campo esta vacío.
                 System.out.println(document);
                 //System.out.println(document.getString("name"));
 
             }
         });
 
     }
     
     public void insertIntoMongoDB() {
         //listAllfromCollect("images");
         db.getCollection(dbName).drop();
         for (Migration m: data) {
             insertImage(m);
         }
     }
    
     private void insertImage(Migration m){
        Document docLabel = null;
        Document docDirectory = null;
        ArrayList<Document> labelsList = null;
        ArrayList<Document> directoriesList = null;
        
        directoriesList = new ArrayList<>();

        for(MigrationDirectory md: m.getDirectoryName()) {
     
            // Generamos el array de Documentos de Etiquetas
            labelsList = new ArrayList<>();
            for (Value_Label vl: md.getLabels()) {
                docLabel = new Document();
                docLabel.append("labelName", vl.getLabel())
                        .append("value", vl.getValue());
                labelsList.add(docLabel);
            }
            docDirectory = new Document();
            docDirectory.append("directoryName", md.getName())
                    .append("labels", labelsList);
            directoriesList.add(docDirectory);
        }
           
        db.getCollection(dbName).insertOne(            
                new Document()
                        .append("_id", m.getId())
                        .append("name", m.getName())
                        .append("size", m.getSize())
                        .append("ext", m.getExtension())
                        .append("path", m.getPath())
                        .append("directory", directoriesList));    
     }
     
     public ArrayList<Migration> getAllImages() {
        rMigrationList = new ArrayList<>();
        FindIterable<Document> iterable = db.getCollection(dbName).find()
                .sort(new Document("name", 1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Migration m = new Migration(document.getInteger("_id"),
                        document.getString("name"),
                        document.getInteger("size"),
                        document.getString("ext"),
                        document.getString("path"),
                                null);
                rMigrationList.add(m);
            }
        });
        
        return rMigrationList;
     }
     
     public ArrayList<Migration> getImagesFilterBy(String filterName, String filterValue) {
         rMigrationList = new ArrayList<>();
         FindIterable<Document> iterable = db.getCollection(dbName).find(
                 Filters.regex(filterName, filterValue))
                .sort(new Document("name", 1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Migration m = new Migration(document.getInteger("_id"),
                        document.getString("name"),
                        document.getInteger("size"),
                        document.getString("ext"),
                        document.getString("path"),
                                null);
                rMigrationList.add(m);
            }
        });
         return rMigrationList;
     }
     
     public ArrayList<MigrationDirectory> getDirectoriesFromImageID(int idImage) {
         rMigrationDirectoryList = new ArrayList<>();
         FindIterable<Document> iterable = db.getCollection(dbName).find(
                new Document("_id", idImage));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
               List<Document> documents = (List<Document>) document.get("directory");
               for (Document d: documents) {
                   MigrationDirectory md = new MigrationDirectory(d.getString("directoryName"), null);
                   rMigrationDirectoryList.add(md);
               }
                   
            }
        });
         
         return rMigrationDirectoryList;
     }
  
}
