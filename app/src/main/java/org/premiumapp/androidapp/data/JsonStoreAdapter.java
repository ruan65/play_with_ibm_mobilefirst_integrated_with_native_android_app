package org.premiumapp.androidapp.data;

import android.content.Context;

import com.jsonstore.api.JSONStore;
import com.jsonstore.api.JSONStoreAddOptions;
import com.jsonstore.api.JSONStoreCollection;
import com.jsonstore.database.SearchFieldType;
import com.jsonstore.exceptions.JSONStoreException;

import org.json.JSONException;
import org.json.JSONObject;
import org.premiumapp.androidapp.R;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class JsonStoreAdapter {

    public final String COLL_PEOPLE = "people";
    public final String NAME = "name";
    public final String AGE = "age";

    private Context ctx;

    public JsonStoreAdapter(Context ctx) {
        this.ctx = ctx;
        initJsonStore();
    }

    public void initJsonStore() {

        String[] names = ctx.getResources().getStringArray(R.array.names);
        try {

            List<JSONStoreCollection> collections = new LinkedList<>();
            JSONStoreCollection peopleCollection = new JSONStoreCollection(COLL_PEOPLE);


            peopleCollection.setSearchField(NAME, SearchFieldType.STRING);
            peopleCollection.setSearchField(AGE, SearchFieldType.INTEGER);

            collections.add(peopleCollection);

            JSONStore.getInstance(ctx).openCollections(collections);

            JSONStoreAddOptions addOpt = new JSONStoreAddOptions();

            addOpt.setMarkDirty(true);

            Random r = new Random();

            for (String name : names) {
                peopleCollection.addData(new JSONObject(String.format("{%s: '%s', %s: %d}", NAME, AGE, name, r.nextInt(91) + 10)), addOpt);
            }

        } catch (JSONStoreException ex) {

            ex.printStackTrace();

        } catch (JSONException ex) {

            ex.printStackTrace();
        }
    }


}
