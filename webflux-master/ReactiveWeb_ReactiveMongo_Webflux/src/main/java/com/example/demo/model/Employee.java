package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection ="EMPLOYEE")
public class Employee {
	@Id
	private String id;
	private String name;
	private Long Salary;

	public static class Event {

		 long id;
		 LocalDateTime dateTime;

		 public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public Event () {

		}
		public LocalDateTime getDateTime() {
			return dateTime;
		}
		public void setDateTime(LocalDateTime dateTime) {
			this.dateTime = dateTime;
		}
		public Event ( long id, LocalDateTime dateTime) {
			super();
			this.id = id;
			this.dateTime = dateTime;
		}
		@Override
		public String toString() {
			return "Event [id=" + id + ", dateTime=" + dateTime + "]";
		}



	}
}
