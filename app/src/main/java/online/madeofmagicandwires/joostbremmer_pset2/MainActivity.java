package online.madeofmagicandwires.joostbremmer_pset2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Text> texts;
    private TextDropDownAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateDropdown();
    }

    public void populateDropdown() {
        texts = TextReader.populateList(this, TextReader.TEXT_RES_IDS);
        adapter = new TextDropDownAdapter(
                this,
                R.layout.dropdown_option,
                texts);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Spinner dropdown = findViewById(R.id.dropdown);
        dropdown.setAdapter(adapter);
    }
}
