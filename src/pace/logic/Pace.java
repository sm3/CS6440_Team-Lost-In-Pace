package pace.logic;




import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import pace.util.*;

import java.sql.SQLException;

import org.h2.tools.Server;




import java.util.ArrayList;
import java.util.List;

import pace.util.EntityManagerFactory;

public class Pace {
	// persistence context
		static EntityManager em;
		
		// h2 server
		static Server server;
		
	
	
public void init() {
		
		// start h2 server
		try {
			server = Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start();
			
		} catch (SQLException e) {
			throw new RuntimeException("Unable to start h2 server: " + e);
		}
		
		// get persistence context
		em = pace.util.EntityManagerFactory.createEntityManager();
		
}

public void stopServer()
{
		//close everything
		em.close();
		
		// stop h2
		
		server.stop();
}


}

 