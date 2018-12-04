package online.madeofmagicandwires.joostbremmer_pset2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * RecyclerView Adapter used to populate a RecyclerView with
 * Input options for placeholder replacements.
 */
public class StoryInputFieldsAdapter extends RecyclerView.Adapter<StoryInputFieldViewHolder> {

    private Story mStory;
    private int itemCount;
    private LayoutInflater mInflator;
    private int layoutId;

    public static final int DEFAULT_LAYOUT = R.layout.story_replace_input;

    /**
     * Constructor of the Adapter
     * @param context Active context; mind that this should be an <b>Activity</b>'s
     *                context, not the Application one
     * @param story   Story object containing relevant data
     * @param layoutId Resource Id of the layoutfile to inflate into RecycleView.
     */
    public StoryInputFieldsAdapter(Context context, Story story, int layoutId) {
        this.mStory = story;
        this.itemCount = story.getPlaceholderCount();
        this.mInflator = LayoutInflater.from(context);
        this.layoutId = layoutId;
    }

    /**
     * Constructor of the Adapter; used the default Layout Resource id.
     * @param context <B>Activity</B> Context
     * @param story Story object containing relevant data
     * @see #DEFAULT_LAYOUT
     */
    public StoryInputFieldsAdapter(Context context, Story story) {
        this(context, story, DEFAULT_LAYOUT);
    }

    /**
     * Creates a StoryInputFielViewHolder
     * @param parent parent RecyclerView
     * @param i position of
     * @return
     */
    @NonNull
    @Override
    public StoryInputFieldViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = mInflator.inflate(layoutId, parent, false);
        return new StoryInputFieldViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryInputFieldViewHolder viewHolder, int i) {
        PlaceholderType placeholder = mStory.getPlaceholder(i);
        viewHolder.inputLabel.setText(placeholder.toString());
        viewHolder.inputField.setHint("Please type " + placeholder.toString().toLowerCase());
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }
}
