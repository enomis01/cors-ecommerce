package it.paolone.ecommerce.configuration;

import it.paolone.ecommerce.dto.SoldProductDTO;
import it.paolone.ecommerce.entities.SoldProducts;
import it.paolone.ecommerce.repositories.PaymentDataRepository;
import it.paolone.ecommerce.repositories.ShippingRepository;
import it.paolone.ecommerce.services.PaymentDataService;
import it.paolone.ecommerce.services.ShippingService;
import it.paolone.ecommerce.services.TransactionService;
import it.paolone.ecommerce.repositories.TransactionRepository;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5500")
                .allowedMethods("OPTIONS","GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        //da aggiustare qui, controllare se è davvero necessario ?

        // modelMapper.typeMap(ProductDTO.class, Product.class).addMappings(mapper -> {
        //     mapper.map(ProductDTO::getProductName, Product::setProductName);
        //     mapper.map(ProductDTO::getImageFilename, Product::setImageFilename);
        //     mapper.map(ProductDTO::getQuantity, Product::setQuantity);
        //     mapper.map(ProductDTO::getBarcode, Product::setBarcode);
        //     mapper.skip(Product::setProductId);
        // });

        // modelMapper.typeMap(Product.class, ProductDTO.class).addMappings(mapper -> {
        //     mapper.map(Product::getBarcode, ProductDTO::setBarcode);
        //     mapper.map(Product::getImageFileName, ProductDTO::setImageFilename);
        //     mapper.map(Product::getProductName, ProductDTO::setProductName);
        //     mapper.map(Product::getQuantity, ProductDTO::setQuantity);
        // });
        
        return modelMapper;
    }

    @Bean
    public ShippingService shippingService(ShippingRepository shippingRepository, ModelMapper modelMapper) {
        return new ShippingService(shippingRepository,modelMapper);
    }

    @Bean
    public PaymentDataService paymentDataService(PaymentDataRepository paymentDataRepository, ModelMapper modelMapper){
        return new PaymentDataService(paymentDataRepository,modelMapper);
    }

    @Bean
    public TransactionService transactionService(TransactionRepository transactionRepository, ModelMapper modelMapper){
        return new TransactionService(transactionRepository,modelMapper);
    }

}
