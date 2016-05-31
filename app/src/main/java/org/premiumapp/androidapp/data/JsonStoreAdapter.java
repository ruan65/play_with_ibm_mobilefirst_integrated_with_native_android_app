package org.premiumapp.androidapp.data;

import android.content.Context;
import android.util.Log;

import com.jsonstore.api.JSONStore;
import com.jsonstore.api.JSONStoreAddOptions;
import com.jsonstore.api.JSONStoreCollection;
import com.jsonstore.database.SearchFieldType;
import com.jsonstore.exceptions.JSONStoreDatabaseClosedException;
import com.jsonstore.exceptions.JSONStoreException;
import com.jsonstore.exceptions.JSONStoreFindException;
import com.jsonstore.exceptions.JSONStoreInvalidSchemaException;

import org.json.JSONException;
import org.json.JSONObject;
import org.premiumapp.androidapp.R;
import org.premiumapp.androidapp.data.model.Persona;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class JsonStoreAdapter {

    public final String COLL_PEOPLE = "people";
    public final String NAME = "name";
    public final String AGE = "age";

    private Context ctx;
    private JSONStoreCollection people;
    private List<JSONStoreCollection> collections = new LinkedList<>();
    private JSONStoreAddOptions addOpt = new JSONStoreAddOptions();

    public JsonStoreAdapter(Context ctx) {
        this.ctx = ctx;
        initPeopleCollection(ctx);
    }

    private void initPeopleCollection(Context ctx) {
        try {
            people = new JSONStoreCollection(COLL_PEOPLE);
        } catch (JSONStoreInvalidSchemaException e) {
            e.printStackTrace();
        }
        people.setSearchField(NAME, SearchFieldType.STRING);
        people.setSearchField(AGE, SearchFieldType.INTEGER);
        collections.add(people);
    }

    public void savePerson(Persona p) {

        try {
            JSONStore.getInstance(ctx).openCollections(collections);
            addOpt.setMarkDirty(true);

            people.addData(new JSONObject(String.format(
                    "{%s: '%s', %s: %d}", NAME, p.name, AGE, p.age
            )));

        } catch (JSONStoreException | JSONException ex) {
            ex.printStackTrace();
        }
    }

    public void printData() {


        try {
            List<JSONObject> allDocuments = people.findAllDocuments();

            for (JSONObject o : allDocuments) {

                Log.d("my", o.toString());
            }

        } catch (JSONStoreFindException e) {
            e.printStackTrace();
        } catch (JSONStoreDatabaseClosedException e) {
            e.printStackTrace();
        }
    }


}
