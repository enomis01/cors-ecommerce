package it.paolone.ecommerce.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import it.paolone.ecommerce.dto.PaymentDataDTO;
import it.paolone.ecommerce.entities.PaymentData;
import it.paolone.ecommerce.services.PaymentDataService;
import lombok.RequiredArgsConstructor;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5500")
public class PaymentDataController {

    private final PaymentDataService paymentDataService;
    
    private Logger logger = LoggerFactory.getLogger(PaymentDataController.class);

    @PostMapping("/save_payment_info")
    public ResponseEntity<PaymentDataDTO> savePayment(@RequestBody PaymentDataDTO paymentInfo) {
        try {
            if (paymentInfo != null) {
                // Esegui la validazione dei dati della carta
                validatePaymentData(paymentInfo);

                // Conversione e salvataggio dei dati di pagamento
                PaymentData convertedPaymentDataDTO = paymentDataService.convertToPaymentData(paymentInfo);
                PaymentData savedPaymentData = paymentDataService.savePaymentData(convertedPaymentDataDTO);
                PaymentDataDTO returningPaymentData = paymentDataService.convertToPaymentDataDTO(savedPaymentData);

                return ResponseEntity.ok(returningPaymentData);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (ValidationException ve) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data provided:" + ve.getMessage(), ve);
        } catch (Exception exc) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while processing the request: " + exc.getMessage(), exc);
        }
    }

    private void validatePaymentData(PaymentDataDTO paymentData) throws ValidationException {
        boolean isCardNumberValid = isValidCardNumber(paymentData.getCardNumber());
        boolean isCvcValid = isValidCvc(paymentData.getCvc());

        if (!isCardNumberValid) {
            logger.error("Il numero della carta non è valido!");
            throw new IllegalArgumentException("Il numero della carta non è valido!");
        }

        if (!isCvcValid) {
            logger.error("Il cvc/cvv Non é valido!");
            throw new IllegalArgumentException("Il cvc/cvv Non é valido!");
        }

        if (isExpirationDateFuture(paymentData.getExpiresMonth(), paymentData.getExpiresYear())) {
            logger.error("Le date di scadenza non sono valide!");
            throw new IllegalArgumentException("Le date di scadenza non sono valide!");
        }
    }

    private boolean isValidCardNumber(String cardNumber) {
        // Implementa la logica per verificare la validità del numero di carta
        // Restituisci true se è valido, altrimenti false
        // Esempio semplificato: verifica se il numero di carta è numerico e ha una
        // lunghezza valida
        return cardNumber.matches("\\d{16}");
    }

    private boolean isValidCvc(String cvc) {
        // Implementa la logica per verificare la validità del CVV
        // Restituisci true se è valido, altrimenti false
        // Esempio semplificato: verifica se il CVV è composto da 3 o 4 cifre
        return cvc.matches("\\d{3,4}");
    }

    private boolean isExpirationDateFuture(String expirationMonth, String expirationYear) {
        try {
            int month = Integer.parseInt(expirationMonth);
            int year = Integer.parseInt(expirationYear);

            // Combina mese e anno in un formato valido per LocalDate
            LocalDate expirationDate = LocalDate.of(year, month, 1);

            return expirationDate.isBefore(
                    LocalDate.now().withMonth(LocalDate.now().getMonthValue()).withYear(LocalDate.now().getYear()));
        } catch (DateTimeException | NumberFormatException e) {
            e.printStackTrace();
            return true;
        }
    }

    @GetMapping("/all_payments")
    public ResponseEntity<List<PaymentDataDTO>> getAllPaymentData() {
        List<PaymentData> fetchedPaymentData = paymentDataService.getAllPaymentData();
        List<PaymentDataDTO> fetchedPaymentDataDTO = new ArrayList<>();
        if (!fetchedPaymentData.isEmpty()) {
            for (PaymentData fetchedPayment : fetchedPaymentData)
                fetchedPaymentDataDTO.add(paymentDataService.convertToPaymentDataDTO(fetchedPayment));

            return ResponseEntity.ok(fetchedPaymentDataDTO);
        }
        return ResponseEntity.noContent().build();
    }
}

// private boolean isValidExpirationMonth(String expirationMonth) {
// // Implementa la logica per verificare la validità della data di scadenza
// // Restituisci true se è valida, altrimenti false
// // Esempio semplificato: verifica se la data è nel formato MM/YY
// return expirationMonth.matches("(0[1-9]|1[0-2])");
// }

// private boolean isValidExpirationYear(String expirationYear) {
// // Implementa la logica per verificare la validità dell'anno di scadenza
// // Restituisci true se è valido, altrimenti false
// // Esempio semplificato: verifica se l'anno di scadenza è nel formato
// corretto
// return expirationYear.matches("\\d{4}");
// }

// ********************************************************|NON**CANCELLARE|*********************************************************************

// @RestController
// @RequestMapping("/api/payments")
// public class PaymentDataController {

// private final PaymentDataService paymentDataService;
// private PaymentData paymentData;

// @Autowired
// public PaymentDataController(PaymentData paymentData, PaymentDataService
// paymentDataService) {
// this.paymentData = paymentData;
// this.paymentDataService = paymentDataService;
// }

// @PostMapping("/save_payment_info")
// public ResponseEntity<PaymentDataDTO> savePayment(@RequestBody PaymentDataDTO
// paymentInfo) {
// try {
// PaymentData convertedPaymentDataDTO =
// paymentDataService.convertToPaymentData(paymentInfo);
// PaymentData savedPaymentData =
// paymentDataService.savePaymentData(convertedPaymentDataDTO);
// PaymentDataDTO returningPaymentData =
// paymentDataService.convertToPaymentDataDTO(savedPaymentData);
// return ResponseEntity.ok(returningPaymentData);
// } catch (ValidationException ve) {
// throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data
// provided: " + ve.getMessage(), ve);
// } catch (ResponseStatusException rse) {
// throw rse;
// } catch (Exception exc) {
// throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error
// occurred while processing the request: " + exc.getMessage(), exc);
// }
// }

// @PostMapping("/save_payment_info")
// public ResponseEntity<PaymentDataDTO> savePayment(@RequestBody PaymentDataDTO
// paymentInfo) {
// if (paymentInfo != null) {
// try {
// PaymentData convertedPaymentDataDTO =
// paymentDataService.convertToPaymentData(paymentInfo);
// PaymentDataDTO returningPaymentData = paymentDataService
// .convertToPaymentDataDTO(paymentDataService.savePaymentData(convertedPaymentDataDTO));
// return ResponseEntity.ok(returningPaymentData);
// } catch (ResponseStatusException exc) {
// throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
// "An error occurred while processing the request: " + exc);
// } catch (Exception exc) {
// throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
// "An error occurred while processing the request : " + exc);
// }
// } else
// return ResponseEntity.badRequest().build();

// }
// }
