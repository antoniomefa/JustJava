package com.antoniomendiola.justjava;

/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        /**   Log.v("MainActivity", "Has whipped cream: " + hasWhippedCream);  **/

        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckbox.isChecked();

        EditText name = (EditText) findViewById(R.id.client_name);
        String clientName = name.getText().toString();

        int price = calculatePrice();
        createOrderSumary(price, hasWhippedCream, hasChocolate, clientName);
    }

    /**
     * Calculates the price of the order.
     *
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    public void increment(View view){
        quantity += 1;
        displayQuantity(quantity);
    }

    public  void  decrement(View view){
        quantity -= 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * Create summary of the order.
     *
     *@param price of order
     *@param addChocolate is whether or not the user wants chocolate topping
     *@param addWhippedCream is whether or not the user wants whipped cream topping
     *@return text summary
     */
    private String createOrderSumary (int price, boolean addWhippedCream, boolean addChocolate, String clientName){

        String priceMessage = "Name: " + clientName +
                              "\nAdd whipped cream? " + addWhippedCream +
                             "\nAdd chocolate? " + addChocolate +
                              "\nQuantity: " + quantity +
                              "\nTotal: $" + price +
                              "\nThank You!";
        return displayMessage(priceMessage);
    }

    /**
     * This method displays the given text on the screen.
     */
    private String displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
        return message;
    }
}