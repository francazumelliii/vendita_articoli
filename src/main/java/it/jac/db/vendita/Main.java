package it.jac.db.vendita;

import java.time.LocalDate;
import java.util.Random;

import it.jac.db.vendita.dao.ArticoloDao;
import it.jac.db.vendita.dao.ArticoloJdbcDao;
import it.jac.db.vendita.dao.PrezzoOffertaDao;
import it.jac.db.vendita.dao.PrezzoOffertaJdbcDao;
import it.jac.db.vendita.entity.Articolo;
import it.jac.db.vendita.entity.PrezzoOfferta;

public class Main {

	public static void main(String[] args) throws Exception {
		
//		ArticoloService service = new ArticoloServiceImpl();
//		
//		service.findArticoloById(4);
		
		long startTime = System.currentTimeMillis();
		ArticoloDao dao = new ArticoloJdbcDao();
//		
//		String[] HEADERS = { 
//				"asin","title",
//				"imgUrl","productURL",
//				"stars","reviews",
//				"price","listPrice",
//				"category_id","isBestSeller",
//				"boughtInLastMonth"
//		};
//		
//		Reader in = new FileReader("D:\\JAC\\anno 2024-25\\ICT23-25\\amazon_products.csv");
//		
//		CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
//		        .setHeader(HEADERS)
//		        .setSkipHeaderRecord(true)
//		        .build();
//		
//		Iterable<CSVRecord> records = csvFormat.parse(in);
//
//		List<Articolo> list = new ArrayList<>();
//		
//		int i = 0;
//	    for (CSVRecord record : records) {
//	    	
//	        String title = record.get("title");
//	        String price = record.get("price");
//	        
//	        Articolo articolo = new Articolo();
//	        articolo.descrizione = title;
//	        articolo.marca = title.substring(0, Math.max(0, title.indexOf(' ')));
//	        
//	        if (articolo.marca.isBlank()) {
//	        	articolo.marca = "SCONOSCIUTA";
//	        }
//	        if (articolo.marca.length() > 255) {
//	        	articolo.marca = articolo.marca.substring(0, 255);
//	        }
//	        articolo.modello = title.substring(title.indexOf(' ') + 1);
//	        if (articolo.modello.length() > 255) {
//	        	articolo.modello = articolo.modello.substring(0, 255);
//	        }
//	        
//	        articolo.prezzoBase = Double.parseDouble(price);
//	        
////	        dao.save(articolo);
//	        list.add(articolo);
//	        
//	        if (i % 1000 == 0) {
//	        	System.out.println(i);
//	        	dao.save(list);
//	        	list.clear();
//	        }
//	        
//	        if (i++ > 100_000) {
//	        	
//	        	dao.save(list);	        	
//	        	System.out.println(System.currentTimeMillis() - startTime);
//	        	return;
//	        }
//	    }
	    
	    PrezzoOffertaDao offertaDao = new PrezzoOffertaJdbcDao();
	    
	    LocalDate[] dateInizio = {
    		LocalDate.of(2024, 10, 20),
    		LocalDate.of(2024, 10, 25),
    		LocalDate.of(2024, 11, 5),
    		LocalDate.of(2024, 11, 15),
    		LocalDate.of(2024, 11, 25)
	    };
	    
	    LocalDate[] dateFine = {
    		LocalDate.of(2024, 10, 23),
    		LocalDate.of(2024, 10, 28),
    		LocalDate.of(2024, 11, 8),
    		LocalDate.of(2024, 11, 18),
    		LocalDate.of(2024, 11, 28)
	    };
	    
	    double sconti[] = {0.90, 0.85, 0.75, 0.95, 0.80};
	    
	    Random rnd = new Random();
	    
	    for (int i = 155870;i < 160_000;i++) {
	    	
	    	Articolo entity = dao.findById(i);
	    	if (entity != null) {
	    		
	    		int pos = rnd.nextInt(5);
//	    		creo offerta
	    		PrezzoOfferta offerta = new PrezzoOfferta();
	    		offerta.articolo = entity;
	    		offerta.dataInizio = dateInizio[pos];
	    		offerta.dataFine = dateFine[pos];
	    		offerta.valore = entity.prezzoBase * sconti[pos];
	    		
	    		offertaDao.save(offerta);
	    	}
	    	
			if (i % 100 == 0) {
				System.out.println(i);
			}

	    	i++;
	    }
	}
}
