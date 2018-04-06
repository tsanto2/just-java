package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int numCoffees = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display(numCoffees);
    }

    public void increment(View view){
        numCoffees++;
        display(numCoffees);
    }

    public void decrement(View view){
        numCoffees--;
        display(numCoffees);
    }

    public void submitOrder(View view){
        CheckBox check = findViewById(R.id.whip_check_view);

        String message = "$" + (numCoffees * 5);
        message += ("\nAdd dat creme? ") + isChecked(check);
        check = findViewById(R.id.choc_check_view);
        message += ("\nAdd dat chocolate? ") + isChecked(check);
        check = findViewById(R.id.caramel_check_view);
        message += ("\nAdd dat caramel? ") + isChecked(check);
        check = findViewById(R.id.cocoa_check_view);
        message += ("\nAdd dat cocoa? ") + isChecked(check);
        check = findViewById(R.id.milk_check_view);
        message += ("\nAdd dat milk? ") + isChecked(check);

        composeEmail(message);

        displayToast();
    }

    private void composeEmail(String message){
        EditText editTextView = findViewById(R.id.edit_view);

        String address = editTextView.getText().toString();
        String[] addresses= new String[1];
        if (address != null)
            addresses[0] = address;

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, addresses);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        if (emailIntent.resolveActivity(getPackageManager()) != null){
            startActivity(emailIntent);
        }
    }

    private void display(int number){
        TextView quantityTextView = findViewById(R.id.quantity_view);
        quantityTextView.setText(Integer.toString(number));
    }

    private void displayToast(){
        Context context = getApplicationContext();
        CharSequence toastMessage = "Composing Email...";
        int duration = Toast.LENGTH_LONG;

        Toast emailToast = Toast.makeText(context, toastMessage, duration);
        emailToast.show();
    }

    private String isChecked(CheckBox checkBox){
        String yn = "NAH";
        if (checkBox.isChecked())
            yn = "YEE";

        return yn;
    }
}
