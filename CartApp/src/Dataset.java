

import com.opencsv.bean.CsvBindByName;

public class Dataset {
	
				

	
	@CsvBindByName(column = "Category")
    private String category;
	@CsvBindByName(column = "Item")
    private String item;
	@CsvBindByName(column = "Quantity")
    private int quantity;
	@CsvBindByName(column = "Price/ Piece")
    private double price;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	

}
