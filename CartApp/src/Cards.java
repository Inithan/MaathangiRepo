

import com.opencsv.bean.CsvBindByName;

public class Cards {
	
	
	    @CsvBindByName
	    private String CardNumber;

		public String getCardNumber() {
			return CardNumber;
		}

		public void setCardNumber(String cardNumber) {
			CardNumber = cardNumber;
		}


	    
	    

}
