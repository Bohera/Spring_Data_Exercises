package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Month;
import java.time.Year;

@Entity
@Table(name = "credit_card")
public class CreditCard extends BillingDetail{

    @Column(name = "card_type" ,length = 10)
    private String cardType;

    @Column(name = "expiration_month")
    private Month expirationMonth;

    @Column(name = "expiration_year")
    private Year expirationYear;

    public CreditCard() {}

    public CreditCard(String number, User owner, String cardType, Month expirationMonth, Year expirationYear) {
        super(number, owner);
        this.cardType = cardType;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Month getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(Month expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public Year getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(Year expirationYear) {
        this.expirationYear = expirationYear;
    }
}
