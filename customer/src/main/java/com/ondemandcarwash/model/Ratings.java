package com.ondemandcarwash.model;
	
	import org.springframework.data.mongodb.core.mapping.Document;

	import org.springframework.data.annotation.Id;

	@Document(collection="Ratings")
	public class Ratings {
		
		
		@Id
		int washerId;
		int rating;
		String review;
		
		public Ratings() {
			
		}
		
		public int getWasherId() {
			return washerId;
		}
		public void setWasherId(int washerId) {
			this.washerId = washerId;
		}
		public int getRating() {
			return rating;
		}
		public void setRating(int rating) {
			this.rating = rating;
		}
		public String getReview() {
			return review;
		}
		public void setReview(String review) {
			this.review = review;
		}
		@Override
		public String toString() {
			return "Ratings [washerId=" + washerId + ", rating=" + rating + ", review=" + review + "]";
		}
		public Ratings(int washerId, int rating, String review) {
			super();
			this.washerId = washerId;
			this.rating = rating;
			this.review = review;
		}
		
	}
		
		
			