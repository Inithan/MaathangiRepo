import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class Controller {
	
	public static  List<Cards> cardDatas;
	public static  List<Dataset> datasetDatas;
	public static  List<Input> inputDatas;
	
	static String cardFilePath = "C:\\Users\\ipbn\\Downloads\\Files-main\\Files-main\\Cards.csv";
	static String datasetFilePath = "C:\\Users\\ipbn\\Downloads\\Files-main\\Files-main\\Dataset.csv";
	static String inputFilePath = "C:\\Users\\ipbn\\Downloads\\Files-main\\Files-main\\Input.csv";
	static String outputFilePath = "C:\\Users\\ipbn\\Downloads\\Files-main\\Files-main\\Output.txt";
	static String successFilePath = "C:\\Users\\ipbn\\Downloads\\Files-main\\Files-main\\Output.csv";
	
	static int essentialMax = 3;
	static int luxuryMax = 4;
	static int miscMax = 6;

	public static void main(String[] args) {

		System.out.println("Cart calculation started ____");
		double totalAmount = 0;
		String check = "";
			
			 try {
				 FileInputStream cardsfin=new FileInputStream(cardFilePath);
				 FileInputStream datasetfin=new FileInputStream(datasetFilePath);
				 FileInputStream inputfin=new FileInputStream(inputFilePath);
					  Reader cardsReader = new BufferedReader(new InputStreamReader(cardsfin));
					  Reader datasetReader = new BufferedReader(new InputStreamReader(datasetfin));
					  Reader inputReader = new BufferedReader(new InputStreamReader(inputfin));
	              // create csv bean reader
	              CsvToBean<Cards> cardBean = new CsvToBeanBuilder(cardsReader).withType(Cards.class).withIgnoreLeadingWhiteSpace(true).build();
	              CsvToBean<Dataset> datasetBean = new CsvToBeanBuilder(datasetReader).withType(Dataset.class).withIgnoreLeadingWhiteSpace(true).build();
	              CsvToBean<Input> inputBean = new CsvToBeanBuilder(inputReader).withType(Input.class).withIgnoreLeadingWhiteSpace(true).build();

	              // convert `CsvToBean` object to list of cards
	              cardDatas = cardBean.parse();
	              datasetDatas = datasetBean.parse();
	              inputDatas = inputBean.parse();
	              
	              

					for (Input input: inputDatas) {

						List<Dataset> itemInfo = datasetDatas.stream()
                                .filter(e -> input.getItem().equalsIgnoreCase(e.getItem())) 
                                .collect(Collectors.toList());
						
						if(itemInfo.get(0).getCategory().equalsIgnoreCase("Essential")) { check=(input.getQuantity() > essentialMax) || input.getQuantity()>itemInfo.get(0).getQuantity()? createFile(input):""; if(!"".equals(check))break;}
						if(itemInfo.get(0).getCategory().equalsIgnoreCase("Luxury")) { check=(input.getQuantity() > luxuryMax)? createFile(input):""; if(!"".equals(check))break;}
						if(itemInfo.get(0).getCategory().equalsIgnoreCase("Misc")) { check=(input.getQuantity() > miscMax)? createFile(input):"";  if(!"".equals(check))break;}
						
						totalAmount = totalAmount + (itemInfo.get(0).getPrice() * input.getQuantity());
						
						
					}		
	              
					List<Cards> cardToAdd = cardDatas.stream()
                            .filter(e -> inputDatas.get(0).getCardNo().equalsIgnoreCase(e.getCardNumber())) 
                            .collect(Collectors.toList());
					
					if(!cardToAdd.isEmpty()) {cardDatas.add(cardToAdd.get(0));}
					
					
					
					if("".equals(check)) {
					 // create FileWriter object with file as parameter
			        FileWriter outputfile = new FileWriter(successFilePath);
			        // create CSVWriter object filewriter object as parameter
			        CSVWriter writer = new CSVWriter(outputfile);
			        // create a List which contains String array
			        List<String[]> data = new ArrayList<String[]>();
			        data.add(new String[] { "Total Amount"});
			        data.add(new String[] { String.valueOf(totalAmount)});
			        writer.writeAll(data);
			        // closing writer connection
			        writer.close();
			        System.out.println("TotalAmount: "+totalAmount);
			        System.out.println("Output File written to following path: "+ successFilePath);
					}

					System.out.println("Cart calculation Ended ____");
	          } catch (Exception ex) {
	        	  ex.printStackTrace();
	        	  
	        	  
	          }
}
	
	
	
	 public static String createFile(Input input) {
		    try {
		      FileWriter myWriter = new FileWriter(outputFilePath);
		      System.out.println("Please correct quantities. "+input.getItem()+" : Incorrect quantity: "+input.getQuantity());
		      myWriter.write("Please correct quantities. "+input.getItem()+" : Incorrect quantity: "+input.getQuantity());
		      myWriter.close();
		      System.out.println("Output File written to following path: "+ outputFilePath);
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		    return "created";
		  }	

}
