package com.neoPOC;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.traversal.Evaluators;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Uniqueness;
import org.neo4j.io.fs.FileUtils;

import com.google.gson.Gson;

/**
 * @author markquinsland
 *
 */
public class DeleteMe {

    /**
     *
     */
    public DeleteMe() {
        // TODO Auto-generated constructor stub
    }



    private GraphDatabaseService db;
    private TraversalDescription friendsTraversal;

    private static final File databaseDirectory = new File( "target/neo4j-traversal-example" );

    private Gson gson = new Gson();

    /**
     * @param args
     */
    private ArrayList <Path> alPaths = new ArrayList <Path> ();

    public static void main( String[] args ) throws IOException
    {
        FileUtils.deleteRecursively( databaseDirectory );
        GraphDatabaseService database = new GraphDatabaseFactory().newEmbeddedDatabase( databaseDirectory );
        DeleteMe traversalexample = new DeleteMe( database );
        Node joe = traversalexample.createData();
     //   traversalexample.run( joe );
        database.shutdown();
    }

    public DeleteMe(GraphDatabaseService db )
    {

        System.out.println("constructor TraversalExample");
        this.db = db;

    }



    private Node createData()
    {

        System.out.println("begin create data");

        String query = "CREATE (joe:Asset {name: 'Joe'}), (sara:Asset {name: 'Sara'}), "
                + "(lisa:Asset {name: 'Lisa'}), (peter:Asset {name: 'PETER'}), (dirk:Asset {name: 'Dirk'}), "
                + "(lars:Asset {name: 'Lars'}), (ed:Asset {name: 'Ed'}),(reg:Asset {name:'regulator'}),"
                + "(joe)-[:KNOWS]->(sara), (lisa)-[:LIKES]->(joe), "
                + "(peter)-[:KNOWS]->(sara), (dirk)-[:KNOWS]->(peter), "
                + "(lars)-[:KNOWS]->(drk), (ed)-[:KNOWS]->(lars), "
                + "(lisa)-[:KNOWS]->(lars), (ed)-[:KNOWS]->(reg) "
                + "RETURN joe";
        Result result = db.execute( query );
        Object joe = result.columnAs( "joe" ).next();
        if ( joe instanceof Node )
        {
            return (Node) joe;
        }
        else
        {
            throw new RuntimeException( "Joe isn't a node!" );
        }
    }

    // end::sourceRels[]
}