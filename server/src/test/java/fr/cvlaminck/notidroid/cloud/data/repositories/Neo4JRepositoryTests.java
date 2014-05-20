package fr.cvlaminck.notidroid.cloud.data.repositories;

import fr.cvlaminck.neo4j.entities.Neo4JEntity;
import fr.cvlaminck.neo4j.repository.impl.BaseNeo4JRepository;
import fr.cvlaminck.notidroid.cloud.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Tests for the Neo4JRepository base implementation.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class Neo4JRepositoryTests {

    @Autowired
    GraphDatabaseService graphDatabaseService;

    @Test
    public void testCreate() throws Exception {
        TestRepository repository = new TestRepository(graphDatabaseService);
        TestEntity testEntity = repository.create();

        assertNotNull("Entity must not be null", testEntity);
        //TODO : assertArrayEquals("Base class name must be used as label of the node", testEntity.get);
    }

    public class TestEntity extends Neo4JEntity {

        public TestEntity(Node underlyingNode) {
            super(underlyingNode);
        }
    }

    public class TestRepository extends BaseNeo4JRepository<TestEntity> {

        public TestRepository(GraphDatabaseService graphDatabaseService) {
            super(TestEntity.class, graphDatabaseService);
        }
    }

}
