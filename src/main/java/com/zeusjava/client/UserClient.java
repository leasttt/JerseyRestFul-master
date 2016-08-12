package com.zeusjava.client;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import com.zeusjava.entity.User;

public class UserClient {
	private static WebResource r = null;
	public static void insertUser(){
		r = Client.create().resource("http://localhost:8080/jersey/api/users");
		Form form = new Form();
		form.add("userId", "002");
		form.add("userName", "ZhaoHongXuan");
		form.add("userAge", 23);
		ClientResponse response = r.type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, form);
		System.out.println(response.getStatus());
	}
	public static void findUser(){
		r = Client.create().resource("http://localhost:8080/jersey/api/users/002");
		String jsonRes = r.accept(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println(jsonRes);
	}

	public static void updateUser(){
		r = Client.create().resource("http://localhost:8080/jersey/api/users");
		Form form = new Form();
		form.add("userId", "002");
		form.add("userName", "Edison");
		form.add("userAge", 30);
		ClientResponse response = r.path(form.get("userId").toString()).type(MediaType.APPLICATION_JSON)
				.put(ClientResponse.class, form);
		System.out.println(response.getStatus());
	}
	public static void deleteUser(){
		r = Client.create().resource("http://localhost:8080/jersey/api/users");

		GenericType<JAXBElement<User>> generic = new GenericType<JAXBElement<User>>() {};
		JAXBElement<User> jaxbContact = r
				.path("002")
				.type(MediaType.APPLICATION_JSON)
				.get(generic);
		User user = jaxbContact.getValue();
		System.out.println(user.getUserId() + ": " + user.getUserName());
		ClientResponse response = r.path("002").delete(ClientResponse.class);
		System.out.println(response.getStatus());
	}

	public static void main(String[] args) {
//		findUser();
//		insertUser();
//		findUser();
		updateUser();
		findUser();
//		deleteUser();
//		findUser();
//		insertUser();
	}
}
