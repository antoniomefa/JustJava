package com.antoniomendiola.justjava;

/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

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

        int price = calculatePrice(hasChocolate, hasWhippedCream);
        createOrderSumary(price, hasWhippedCream, hasChocolate, clientName);
    }

    /**
     * Calculates the price of the order.
     *
     */
    private int calculatePrice(boolean addChocolate, boolean addWippedCream) {
        int basePrice = 5;
        if (addChocolate){
            basePrice += 2;
        }
        if (addWippedCream){
            basePrice += 1;
        }
        return quantity * basePrice;
    }

    public void increment(View view){
        if(quantity == 100){
            Toast.makeText(getApplicationContext(), getString(R.string.too_many_coffees), Toast.LENGTH_SHORT).show();
        }else{
            quantity += 1;
        }
        displayQuantity(quantity);
    }

    public  void  decrement(View view){
        if(quantity == 1){
            Toast.makeText(this, getString(R.string.too_few_coffees), Toast.LENGTH_SHORT).show();
        }else{
            quantity -= 1;
        }
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
     */
    private void createOrderSumary (int price, boolean addWhippedCream, boolean addChocolate, String clientName){

        String priceMessage = getString(R.string.order_summary_name, clientName) +
                              "\n" + getString(R.string.order_summary_whipped_cream, addWhippedCream) +
                             "\n" + getString(R.string.order_summary_chocolate, addChocolate) +
                              "\n" + getString(R.string.order_summary_quantity, quantity) +
                              "\n" + getString(R.string.order_summary_price, price) +
                              "\n" + getString(R.string.thank_you);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.order_summary_email_subject, clientName));
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Send Email"));
        }
    }

}