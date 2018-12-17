package online.madeofmagicandwires.joostbremmer_pset2;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;


public class StoryActivity extends AppCompatActivity {


    private final String savedStoryKey = "savedStoryKey";
    private final String savedTextKey = "savedTextKey";

    private Story story;
    private Text receivedText;
    private StoryInputFieldsAdapter adapter;

    /**
     * onCreate method, is called when the activity is created or resumed
     * @param savedInstanceState bundle of saved data from previous state
     * @see AppCompatActivity#onCreate(Bundle)
     * @see AppCompatActivity#onSaveInstanceState(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get saved Story from savedInstanceState if it exists
        if(savedInstanceState != null && savedInstanceState.containsKey(savedStoryKey)) {
            receivedText = (Text) savedInstanceState.getSerializable(savedTextKey);
            story = (Story) savedInstanceState.getSerializable(savedStoryKey);
        } else  {
            // initialize all private members
            initElements();
        }



        // draw screen
        assert story != null;
        if(!story.isFilledIn()) {
            setContentView(R.layout.activity_story);
            setRecyclerView();
            if(adapter != null) {
                setSubmitButton();
            }
        } else {
            showStory();
        }



        Log.d("StoryActivity", "Amount of placeholders: " + story.getPlaceholderCount());

        Log.d("StoryActivity", "The following placeholders were found:");
        for(int i=0;i<story.getPlaceholderCount();i++){
            Log.d(Integer.toString(i), story.getPlaceholder(i).toString());
        }
    }

    /**
     * Initializes all private elements if they are not set already.
     * @see #setTxt()
     * @see #setStory(Text)
     */
    public void initElements() {
        if(story == null || receivedText == null) {
            try {
                setTxt();
            } catch (NullPointerException e) {
                Log.e("Could not initialise Text", e.getMessage());
                finish();
            }
            setStory(receivedText);

        }
    }

    /**
     * Retrieves the Text object from the previous activity
     */
    private void setTxt() throws NullPointerException {
        Intent intent = getIntent();
        Text txt = (Text) intent.getSerializableExtra(MainActivity.TEXT_EXTRA_NAME);
        if(txt != null) {
            receivedText = txt;
        } else {
            throw new NullPointerException("No text received from previous activity");
        }
    }

    /**
     * Initializes the Story instance from Text object
     */
    private void setStory(Text txt) {
        story = TextReader.getStoryFromText(getApplicationContext(), txt);

    }

    /**
     * Initializes the RecyclerView with the right adapter and layout managers
     */
    private void setRecyclerView() {
        RecyclerView container = findViewById(R.id.inputContainer);
        if(container != null) {
            adapter = new StoryInputFieldsAdapter(this, story);

            // use linear or grid layout manager based on orientation
            // (yes I know grid with 1 span would also have worked)
            // @see https://stackoverflow.com/a/3589509
            RecyclerView.LayoutManager manager;
            int orientation = getResources().getConfiguration().orientation;
            if(orientation == Configuration.ORIENTATION_PORTRAIT) {
                manager = new LinearLayoutManager(this);
            } else {
                manager = new GridLayoutManager(this, 2);
            }

            // link adapter and manager to recyclerview
            container.setAdapter(adapter);
            container.setLayoutManager(manager);
        }

    }

    /**
     * Initiates the final "Submit" so that when clicked it applies the replacement changes
     * and shows the Story's text.
     */
    private void setSubmitButton() {
        Button btn = findViewById(R.id.inputCommit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean changesCommitted = adapter.commitReplacements();
                if(changesCommitted) {
                    showStory();
                }
            }
        });
    }

    /**
     * Shows the Story's text in the current activity
     */
    private void showStory() {
        setContentView(R.layout.show_story);
        TextView storyTitle = findViewById(R.id.storyTitle);
        WebView storyText = findViewById(R.id.storyText);

        // set text title from receivedText
        storyTitle.setText(receivedText.getTextTitle());

        // set data and settings from story
        // @see https://stackoverflow.com/q/21694306
        WebSettings config = storyText.getSettings();
        config.setDefaultFontSize(20);
        storyText.setBackgroundColor(getColor(R.color.backgroundDefault));

        // load data
        storyText.loadData(story.toString(), "text/html; charset=utf-8", "utf-8");

    }

    /**
     * Saves Story and Text instance to memory when activity is suspended
     * @param outState Bundle to which the story and text can later be retrieved
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        outState.putSerializable(savedTextKey, receivedText);
        outState.putSerializable(savedStoryKey, story);
    }
}
