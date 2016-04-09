package edu.umkc.assignment10;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteResult;

/**
 * Servlet implementation class ControllerService
 */
@Path("/user")
public class ControllerService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @GET
	@Path("/readUser")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings({ "deprecation", "resource" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MongoClientURI uri = new MongoClientURI("mongodb://sravani:umkc123@ds019980.mlab.com:19980/ase");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection users = db.getCollection("Users");
		BasicDBObject query = new BasicDBObject();
		/*request.getParameter("userName");
		request.getParameter("password");*/
		DBCursor docs = users.find(query);
		response.getWriter().write(docs.toArray().toString());
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @POST
	@Path("/createUser")
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings({ "deprecation", "resource" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		System.out.println(data);

		JSONObject params = new JSONObject(data);
		BasicDBObject user1 = new BasicDBObject();
		
		for(Object key:params.keySet().toArray())
		{
			user1.put(key.toString(),params.get(key.toString()));
		}
		System.out.println(user1.toJson());
		MongoClientURI uri = new MongoClientURI("mongodb://sravani:umkc123@ds019980.mlab.com:19980/ase");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection users = db.getCollection("Users");
		WriteResult result = users.insert(user1);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");
		response.getWriter().write(result.toString());
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
    @PUT
	@Path("/updateUser")
	@Produces(MediaType.APPLICATION_JSON)
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String data = buffer.toString();
		System.out.println(data);

		JSONObject params = new JSONObject(data);
		BasicDBObject updateUser = new BasicDBObject();
		
		for(Object key:params.keySet().toArray())
		{
			updateUser.put(key.toString(),params.get(key.toString()));
		}
		System.out.println(updateUser.toJson());
		MongoClientURI uri = new MongoClientURI("mongodb://sravani:umkc123@ds019980.mlab.com:19980/ase");
		MongoClient client = new MongoClient(uri);
		DB db = client.getDB(uri.getDatabase());
		DBCollection users = db.getCollection("Users");
		WriteResult result = users.update(new BasicDBObject().append("userName",request.getParameter("userName")), updateUser);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "PUT");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");
		response.getWriter().write(result.toString());
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
    @SuppressWarnings({ "null", "deprecation" })
	@DELETE
	@Path("/deleteUser")
	@Produces(MediaType.APPLICATION_JSON)
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	String username = request.getParameter("userName");
    	MongoClientURI uri = new MongoClientURI("mongodb://sravani:umkc123@ds019980.mlab.com:19980/ase");
		MongoClient client = new MongoClient(uri);
		BasicDBObject deleteUser = new BasicDBObject();
		DB db = client.getDB(uri.getDatabase());
		DBCollection users = db.getCollection("Users");
		deleteUser.append("name", username);
		WriteResult result = users.remove(deleteUser);
		result.toString();
	}

}
