

import com.opencsv.bean.CsvBindByName;

public class Input {
	

	@CsvBindByName(column = "Card number")
    private String cardNo;
	@CsvBindByName(column = "Item")
    private String item;
	@CsvBindByName(column = "Quantity")
    private int quantity;
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
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
	
	
	

}
