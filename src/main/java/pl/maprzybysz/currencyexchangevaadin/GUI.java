package pl.maprzybysz.currencyexchangevaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.math.BigDecimal;
import java.util.List;

@Route("/")
@Component
@PreserveOnRefresh
public class GUI extends VerticalLayout {


    private ExchangeService exchangeService;


    @Autowired
    public GUI(ExchangeService exchangeService) {
        List<String> currencies = exchangeService.getAvailableCurrencies();
        this.exchangeService = exchangeService;
        setWidth("100%");
        setHeight("100%");
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        Select selectFrom = getSelect(currencies, "FROM");
        Select selectTo = getSelect(currencies, "TO");
        BigDecimalField amount = new BigDecimalField();
        amount.setPlaceholder("AMOUNT");
        TextField exchangeAmountField = new TextField();
        exchangeAmountField.setVisible(false);
        exchangeAmountField.setReadOnly(true);
        Button exchangeButton = new Button("EXCHANGE");
        add(selectFrom, selectTo, amount, exchangeButton, exchangeAmountField);
        exchangeButton.addClickListener(buttonClickEvent-> {
            buttonFn(exchangeService, selectFrom, selectTo, amount, exchangeAmountField);
        });
    }

    private void buttonFn(ExchangeService exchangeService, Select selectFrom, Select selectTo, BigDecimalField amount, TextField exchangeAmountField) throws RestClientException {
        BigDecimal amountAfter = new BigDecimal(amount.getValue().toString());
        BigDecimal conversionRate = new BigDecimal(exchangeService.getConversionRate(
                selectFrom.getValue().toString(), selectTo.getValue().toString()
        ));
        BigDecimal exchangeAmount = amountAfter.multiply(conversionRate);
        String result =
                amount.getValue().toString()+ selectFrom.getValue().toString()+" = "
                        +exchangeAmount+ selectTo.getValue().toString();
        exchangeAmountField.setValue(result);
        exchangeAmountField.setVisible(true);
    }

    private Select getSelect(List list, String label){
        Select<String> select = new Select<>();
        select.setPlaceholder(label);
        select.setItems(list);
        return select;
    }
}
