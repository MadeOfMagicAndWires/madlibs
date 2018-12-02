package online.madeofmagicandwires.joostbremmer_pset2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import java.util.List;


public class TextDropDownAdapter extends ArrayAdapter<Text> implements SpinnerAdapter {

    private int resourceId;
    private int dropDownResourceId;



    /**
     * Constructor setting the dropdown resource as well.
     * @param context the current activity context
     * @param resource the resource ID for a layout file to use for the dropdown
     * @param objects the objects to be represented in the listView
     * @param dropDownId the resource ID for a layout file to use for the dropdown options
     */
    public TextDropDownAdapter(@NonNull Context context,
                               int resource,
                               @NonNull List<Text> objects,
                               int dropDownId) {
        super(context, resource, objects);
        this.resourceId = resource;
        this.dropDownResourceId= dropDownId;
    }

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public TextDropDownAdapter(@NonNull Context context,
                                int resource,
                                @NonNull List<Text> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }


    /**
     * Binds the data to a View to display
     *
     * @param position The position of the item
     *                 within the adapter's data set of the items
     *                 whose view we want
     * @param convertView The old view to reuse, if possible
     * @param parent The parent that this view will eventually be attached to;
     *               must never be null
     * @return the resulting View
     */
    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        // get text object
        Text text = getItem(position);

        //create new convertView if necessary
        if(convertView == null) {
           convertView = LayoutInflater.from(getContext()).inflate(
                   getResource(),
                   parent,
                   false);
        }

        // get textView
        TextView dropDownText = convertView.findViewById(R.id.dropdownOptionText);


        // bind data to view
        if(text != null) {
            dropDownText.setText(text.getTextTitle());
        }


        return convertView;

    }

    /**
     * Creates a dropdown option view containing the data from a Text object
     * @param position the position of the item in the Adapter's dataset
     * @param convertView The old view to re-purpose if available, else null
     * @param parent The parent view to which convertView is to be attached
     * @return the resulting view
     */
    @Override
    public View getDropDownView(int position,
                                @Nullable View convertView,
                                @NonNull ViewGroup parent) {

        // create or convert new convertView
        TextView dropDown;
        if(convertView == null) {
            dropDown = (TextView) LayoutInflater.from(getContext()).inflate(
                    getmDropDownResource(),
                    parent,
                    false);
        } else {
            dropDown = (TextView) convertView;
        }
        // get Text data, bind it to view
        Text text = getItem(position);
        if(text != null) {
            dropDown.setText(text.getTextTitle());
        }

        return dropDown;
    }

    /**
     * Returns the Resource ID for the Layout to be inflated for DropDownViews
     * @return int linking to the layout resource to be inflated
     */
    private int getmDropDownResource() {
        return this.dropDownResourceId;
    }

    /**
     * Returns the Resource ID for the Layout to be inflated for #getView()
     * @return int linking to the layout resource to be inflated
     */
    private int getResource() {
        return this.resourceId;
    }


    /**
     * <p>Sets the layout resource to create the drop down views.</p>
     *
     * @param resource the layout resource defining the drop down views
     * @see #getDropDownView(int, View, ViewGroup)
     */
    @Override
    public void setDropDownViewResource(int resource) {
        super.setDropDownViewResource(resource);
        this.dropDownResourceId = resource;
    }
}
