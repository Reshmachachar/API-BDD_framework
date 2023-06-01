package resources;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.location;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class TestDataBuild {
	public AddPlace addPlacePayload(String name,String language,String address)
	{
		AddPlace p=new AddPlace();
		p.setAccuracy("50");
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress(address);
		p.setWebsite("http://google.com");
		p.setLanguage(language);
		location l=new location();
		l.setLat("-38.383494");
		l.setLng("33.427362");
		p.setLocation(l);
		List<String> a=new ArrayList<String>();
		a.add("shoe park");
		a.add("shop");
		p.setTypes(a);
		
	  return p;
		 
	}
	public String deletePlacePayload(String place_Id)
	{
		return "{\r\n"
				+ "    \"place_id\":\""+place_Id+"\"\r\n"
				+ "}\r\n"
				+ ""; 
	}

}
