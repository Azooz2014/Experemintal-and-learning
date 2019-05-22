package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    boolean addWC;
    boolean addCH;

    CheckBox checkBox;
    CheckBox checkBox2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    public void submitOrder(View view) {
        // You can displaying any method without the need to create a new variable.
        displayMessage(createOrderSummary());
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * The Price method.
     */
    private double calculatePrice(int quantity, double cPrice) {

        //You can return the operation without the needing to create a new variable.
        return quantity * cPrice;
    }
    /**
     * Newer version of the old createOrderSummary method.
     * More efficient.
     */
    private String createOrderSummary() {
        addWC = checkBox.isChecked();
        addCH = checkBox2.isChecked();

        String priceMessage = "Name: Abdelaziz";
        priceMessage += "\nAdd whipped cream ? " + addWC;
        priceMessage += "\nAdd chocolate ? " + addCH;
        priceMessage += "\nQuantity:" + quantity;
        priceMessage += "\nPrice:" + calculatePrice(quantity, 5);
        priceMessage += "\nThank you!";
        return priceMessage;
    }

//     my old method.
//     inefficient method way.
//     private String createOrderSummary(){
//     String name = "Abdelaziz";
//     int quan = quantity;
//     String Q = "\n"+"Quantity:" + quan;
//     return name+Q;
//     }
}