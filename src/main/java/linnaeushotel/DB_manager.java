package linnaeushotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;

import linnaeushotel.guest.Guest;
import linnaeushotel.reservation.Reservation;
import linnaeushotel.room.Location;
import linnaeushotel.room.Room;
import linnaeushotel.room.RoomQuality;
import linnaeushotel.room.RoomType;

public class DB_manager{
	
	private MongoClientURI uri;
	private MongoClient client;
	private DBCollection collection;
	
	public DB_manager(){
		
	}
	
	
	public void insertGuest(Guest g){

		
		try{
			
			uri= new MongoClientURI("mongodb+srv://test:123@cluster0-les1j.mongodb.net/test");
			client= new MongoClient(uri);
			
			@SuppressWarnings("deprecation")
			DB db= client.getDB("Hotel");
			collection=db.getCollection("guest");
			
			// fixa så du har defualt id ifrån databasen
			DBObject guest = new BasicDBObject("_id",g.getId())
				.append("firstName",g.getFirstName())
				.append("lastName", g.getLastName())
				.append("Reservations", g.getReservations())
				.append("address", g.getAddress())
				.append("phone", g.getPhone())
				.append("mobile", g.getMobile())
				.append("fax", g.getFax())
				.append("email", g.getEmail())
				.append("favouriteRoom", g.getFavouriteRoom())
				.append("smoker", g.isSmoker())
				.append("birthday",g.getBirthday())
				.append("spouse", g.getSpouse())
				.append("children", g.getChildren())
				.append("company", g.getCompany());
				
			collection.insert(guest);
			client.close();
			}catch(Exception e){
				System.err.println(e);
			}	
			
	
	
		}

	public void deleteGuest(Guest g){
		
		try{
			
			uri= new MongoClientURI("mongodb+srv://test:123@cluster0-les1j.mongodb.net/test");
			client= new MongoClient(uri);
			
			@SuppressWarnings("deprecation")
			DB db= client.getDB("Hotel");
			collection=db.getCollection("guest");
			
			DBObject query = new BasicDBObject("_id",g.getId());
			collection.remove(query);
		
			client.close();
			}catch(Exception e){
				System.err.println(e);
			}	
		
		
	
	}
	
	
	public void updateGuest(Guest g){
		
	
				try{
					
					uri= new MongoClientURI("mongodb+srv://test:123@cluster0-les1j.mongodb.net/test");
					client= new MongoClient(uri);
					
					@SuppressWarnings("deprecation")
					DB db= client.getDB("Hotel");
					collection=db.getCollection("guest");
					
					
				
					DBObject guest = new BasicDBObject("_id",g.getId())
						.append("firstName",g.getFirstName())
						.append("lastName", g.getLastName())
						.append("Reservations", g.getReservations())
						.append("address", g.getAddress())
						.append("phone", g.getPhone())
						.append("mobile", g.getMobile())
						.append("fax", g.getFax())
						.append("email", g.getEmail())
						.append("favouriteRoom", g.getFavouriteRoom())
						.append("smoker", g.isSmoker())
						.append("birthday",g.getBirthday())
						.append("spouse", g.getSpouse())
						.append("children", g.getChildren())
						.append("company", g.getCompany());
					
					
					DBObject query = new BasicDBObject("_id",g.getId());
					collection.update(query, guest);
					client.close();
					
					}catch(Exception e){
						System.err.println(e);
					}	
				
			}


	public ArrayList<Guest >getGuests(){
		ArrayList<Guest> guestList = new ArrayList<Guest>();
	
		try{
			
			uri= new MongoClientURI("mongodb+srv://test:123@cluster0-les1j.mongodb.net/test");
			client= new MongoClient(uri);
			
			@SuppressWarnings("deprecation")
			DB db= client.getDB("Hotel");
			collection=db.getCollection("guest");
		
			DBCursor cursor =  collection.find();
			
			while(cursor.hasNext()){
				
				DBObject obj = cursor.next();
				Guest g = new Guest();
				//fixa default id till sträng osv
				g.setId((int)obj.get("_id"));
				g.setFirstName((String)obj.get("firstName"));
				g.setLastName((String)obj.get("lastName"));
				g.setReservations((ArrayList<Reservation>)obj.get("Reservations"));
				g.setAddress((String)obj.get("address"));
				g.setPhone((String)obj.get("phone"));
				g.setMobile((String)obj.get("mobile"));
				g.setFax((String)obj.get("fax"));
				g.setEmail((String)obj.get("email"));
				g.setFavouriteRoom((String)obj.get("favouriteRoom"));
				g.setSmoker((Boolean)obj.get("smoker"));
				g.setBirthday((LocalDate)obj.get("birthday"));
				g.setSpouse((String)obj.get("spose"));
				g.setChildren((String)obj.get("children"));
				g.setCompany((String)obj.get("company"));
				
				guestList.add(g);
			}
		
			
			client.close();
			return guestList;
			}catch(Exception e){
				System.err.println(e);
			}	
		
	
		return null;
		
	}

	public ArrayList<Room >getRooms(){
		ArrayList<Room> roomList = new ArrayList<Room>();
	
		try{
			
			uri= new MongoClientURI("mongodb+srv://test:123@cluster0-les1j.mongodb.net/test");
			client= new MongoClient(uri);
			
			@SuppressWarnings("deprecation")
			DB db= client.getDB("Hotel");
			collection=db.getCollection("room");
		
			DBCursor cursor =  collection.find();
			
			while(cursor.hasNext()){
				
				 DBObject obj = cursor.next();
				 RoomQuality a= RoomQuality.valueOf((String)obj.get("RoomQuality"));
				 RoomType b= RoomType.valueOf((String)obj.get("RoomType"));
				 Location c=Location.valueOf((String)obj.get("Location"));
				
				 Room g = new Room(((int)obj.get("roomNumber")),b,a,c,(boolean)obj.get("smoker"),(boolean)obj.get("reserved"));
				 roomList.add(g);
			}
		
			
				client.close();
				return roomList;
			
		    }catch(Exception e){
				System.err.println(e);
			}	
		
	
		return null;
		
	}

public void updateRoom(Room  g){
	
	
	try{
		
		uri= new MongoClientURI("mongodb+srv://test:123@cluster0-les1j.mongodb.net/test");
		client= new MongoClient(uri);
		boolean reservationStatus;
		@SuppressWarnings("deprecation")
		DB db= client.getDB("Hotel");
		collection=db.getCollection("room");
		
		
		if(g.isReserved())
			reservationStatus=false;
		else
			reservationStatus=true;
		
		
		DBObject room = new BasicDBObject("roomNumber",g.getRoomNumber())
			.append("RoomType",g.getType().toString())
			.append("RoomQuality", g.getQuality().toString())
			.append("Location", g.getLocation().toString())
			.append("smoker", g.isSmoker())
			.append("reserved", reservationStatus);
			
			
		
		
		DBObject query = new BasicDBObject("roomNumber",g.getRoomNumber());
		collection.update(query, room);
		client.close();
		
		}catch(Exception e){
			System.err.println(e);
		}	
	
}


}




