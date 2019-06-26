package it.polito.tdp.poweroutages.model;

import java.time.LocalDateTime;

public class Po {

	private int id;
	private int ca;
	private LocalDateTime began;
	private LocalDateTime finished;
	private long duration;
	
	public Po(int id, int ca, LocalDateTime began, LocalDateTime finished, long duration) {
		super();
		this.id = id;
		this.ca = ca;
		this.began = began;
		this.finished = finished;
		this.duration = duration;
	}

	public int getId() {
		return id;
	}

	public int getCa() {
		return ca;
	}

	public LocalDateTime getBegan() {
		return began;
	}

	public LocalDateTime getFinished() {
		return finished;
	}

	public long getDuration() {
		return duration;
	}
	
	@Override
	public String toString() {
		return this.id+" "+this.began.toString()+" "+this.finished.toString()+" "+this.duration+" "+this.ca+"\n";
	}
	
} 
