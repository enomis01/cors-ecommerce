package it.paolone.ecommerce.services;

import it.paolone.ecommerce.dto.PaymentDataDTO;
import it.paolone.ecommerce.entities.PaymentData;
import it.paolone.ecommerce.repositories.PaymentDataRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
public class PaymentDataService {

    private final PaymentDataRepository paymentDataRepository;
    private final ModelMapper modelMapper;

    public PaymentData getPaymentDataById(Long id) {
        Optional<PaymentData> fetchedPaymentData = paymentDataRepository.findById(id);
        return fetchedPaymentData.orElse(null);
    }

    public List<PaymentData> getAllPaymentData() {
        return paymentDataRepository.findAll();

    }

    public PaymentData savePaymentData(PaymentData data) {
        return paymentDataRepository.save(data);

    }

    public PaymentData convertToPaymentData(PaymentDataDTO dataInput){
        return modelMapper.map(dataInput, PaymentData.class);
    }

    public PaymentDataDTO convertToPaymentDataDTO (PaymentData data){
        return modelMapper.map(data, PaymentDataDTO.class);
    }




    
}
