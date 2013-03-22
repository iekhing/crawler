package SandBox.testing;

import me.prettyprint.cassandra.service.CassandraHostConfigurator;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.factory.HFactory;

public class CassandraTesting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		cluster=MW Dev Cluster
//				keyspace=mw
//				#host=107.21.107.130, 107.22.217.187, 107.21.107.129, 50.19.117.192
//				#casPort=9160
//				host=sg-main.wordsterbeta.com:9160
//				casPort=9160
		System.out.println("Start");
		Cluster cluster = HFactory.getOrCreateCluster("MW Dev Cluster",
				new CassandraHostConfigurator("sg-main.wordsterbeta.com:9160"));
		//Keyspace keyspace = HFactory.createKeyspace("mw", cluster);
		System.out.println("End");
	}

}
