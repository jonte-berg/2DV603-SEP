package linnaeushotel.reservation;

import java.time.LocalDate;

import linnaeushotel.room.Room;

/**
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name Reservation.java
 */
public class Reservation {
	
	private LocalDate startDate;
	private LocalDate endDate;
	
	private Room room;
	
	private boolean checkedIn;
	
	//TODO: Do not allow negative price. - Oskar Mendel 2018-05-01
	private double price;
	
	public Reservation(LocalDate startDate, LocalDate endDate, Room room, double price) {
		if (price < 0) {
			//TODO: What kind of exception / error do we want here? - Oskar Mendel 2018-05-01
			throw new RuntimeException("Negative price");
		}
		
		this.startDate = startDate;
		this.endDate = endDate;
		this.room = room;
		this.checkedIn = false;
	}

	/**
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * @return the checkedIn
	 */
	public boolean isCheckedIn() {
		return checkedIn;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * @param room the room to set
	 */
	public void setRoom(Room room) {
		this.room = room;
	}

	/**
	 * @param checkedIn the checkedIn to set
	 */
	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
}
