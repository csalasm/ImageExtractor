/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionmongodb;
 
/**
 *
 * @author Jesus
 */
 import com.mongodb.MongoClient;
 import com.mongodb.client.MongoDatabase;
 
 import org.bson.Document;
 import com.mongodb.Block;
 import com.mongodb.client.FindIterable;
import java.util.ArrayList;
 
/**
>  *
>  * @author Jesus Munoz
>  */
 public class ConexionMongoDB {
 
     /**
>      * @param args the command line arguments
>      */
     private MongoClient mongoClient = null;
     private MongoDatabase db = null;
 
 
     public ConexionMongoDB(String dbName) {
         this.mongoClient = new MongoClient();
         this.db = mongoClient.getDatabase(dbName);
 
     }
 
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
     
     public void insertImage(){
         
        Document doc1 = new Document();
       
         ArrayList<Document> Ldoc = new ArrayList<>();
        for(int i=0; i<5; i++){
            doc1.append("labelName", "nombreLabel").append("value","valor");
            Ldoc.add(doc1);
        }
        Document doc2 = new Document();
       
         ArrayList<Document> Adoc = new ArrayList<>();
           for(int i=0; i<2; i++){
            doc2.append("directoryName","nombreDir").append("label",(Ldoc));
            Adoc.add(doc2);
           }
           
        db.getCollection("images").insertOne(            
                new Document()
                        .append("_id", 34)
                        .append("name", "imagen")
                        .append("size", "1480")
                        .append("ext", "png")
                        .append("path","/home/")
                        .append("directory", Adoc));    
     }
 
     public static void main(String[] args) { //Main de prueba>
         ConexionMongoDB conexion = new ConexionMongoDB("imagesDB");
 
         conexion.listAllfromCollect("images");
         conexion.insertImage();
     }
     
     
}
