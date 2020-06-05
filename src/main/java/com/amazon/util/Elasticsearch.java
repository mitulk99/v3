package com.amazon.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

public class Elasticsearch implements Back  {

	@Autowired	
    Coordinates call;
	public List<StoresDetails> Backend(UserRequirements User) throws Exception
	{
		List<StoresDetails> details= new ArrayList<StoresDetails>();
//               	try {
//              
               call.Coords(User.getPincode());
               Double lat= call.getLat();
               Double lon= call.getLon();
   
				RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost",9200, "http")));
				SearchRequest searchRequest = new SearchRequest("my_locations"); 
				SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
				searchSourceBuilder.query(QueryBuilders
						  .geoDistanceQuery("location")
						  .point(lat,lon)
						  .distance(User.getRadius(), DistanceUnit.KILOMETERS)
						  .geoDistance(GeoDistance.ARC));
				searchRequest.source(searchSourceBuilder.sort(SortBuilders.geoDistanceSort("location",lat,lon).order(SortOrder.ASC).unit(DistanceUnit.KILOMETERS)));
				SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
				RestStatus status = searchResponse.status();
				SearchHits hits = searchResponse.getHits();
				SearchHit[] searchHits = hits.getHits();
				for (SearchHit hit : searchHits) {
					JSONObject obj1= new JSONObject(hit.toString());
					JSONArray obj2=obj1.getJSONArray("sort");
					obj1=obj1.getJSONObject("_source");
					if(obj1.get("category").equals(User.getCategory()) || User.getCategory().equals("all")==true)
					{
						StoresDetails sd=StoresDetails.builder().build();
						if(obj1.has("merchant name")==true)
							sd.setMerchantname(obj1.get("merchant name").toString());
							if(obj1.has("open time")==true)
							sd.setOpentime(obj1.get("open time").toString());
							if(obj1.has("close time")==true)
							sd.setClosetime(obj1.get("close time").toString());
							if(obj1.has("address")==true)
							sd.setAddress(obj1.get("address").toString());
							if(obj1.has("category")==true)
							sd.setCategory(obj1.get("category").toString());
							if(obj1.has("home delivery")==true)
							sd.setRangeofhomedelivery(obj1.get("range of home delivery").toString());
							sd.setDistance(obj2.getDouble(0));
							details.add(sd);
					}
				}
//			} catch (Exception e) {
////				e.printStackTrace();
//				System.out.println("hii");
//			}
//               	finally {
//               		return details;
//               	}
				
				return details;
	}
}
