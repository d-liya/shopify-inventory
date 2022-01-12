package com.shopify.inventory.utils;


import java.io.PrintWriter;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.shopify.inventory.model.Product;
import java.util.List;

public class ProductCSVUtil {
	
	
	
	public static void writeProducts(PrintWriter writer, List<Product> cities) {

        try {
            CustomMappingStrategy<Product> mapStrategy
                    = new CustomMappingStrategy<>();

            mapStrategy.setType(Product.class);

            String[] columns = new String[]{"id", "name", "inventoryReceived","inventoryShipped","inventoryOnHand"};
            mapStrategy.setColumnMapping(columns);

            StatefulBeanToCsv<Product> btcsv = new StatefulBeanToCsvBuilder<Product>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(mapStrategy)
                    .withSeparator(',')
                    .build();

            btcsv.write(cities);

        } catch (CsvException ex) {

            System.out.println("Error mapping Bean to CSV");
        }
    }
}

class CustomMappingStrategy<T> extends ColumnPositionMappingStrategy<T> {
    private static final String[] HEADER = new String[]{"PRODUCT ID", "PRODUCT NAME", "INVENTORY RECEIVED", "INVENTORY SHIPPED", "INVENTORY ONHAND"};

    @Override
    public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException {
        return HEADER;
    }
}
