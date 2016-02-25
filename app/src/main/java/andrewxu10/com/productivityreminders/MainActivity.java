package andrewxu10.com.productivityreminders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ArrayList<String> arrayListOfTasks;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listOfTasks = (ListView) findViewById(R.id.mainListView);
        //EditText longPressable = (EditText) findViewById(R.id.the_item_text)
        listOfTasks.setOnItemClickListener(this);
        //longPressable.setOnItemLongClickListener(this);
        arrayListOfTasks = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                this,
                R.layout.list_view_lines,
                R.id.the_item_text,
                arrayListOfTasks
        );

        listOfTasks.setAdapter(adapter);
        readAllTasks();
        adapter.notifyDataSetChanged();
    }

    private void readAllTasks() {
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.remindersfile));
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            arrayListOfTasks.add(line);
        }

        adapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public void addTaskClick(View view) {
        EditText the_word = (EditText) findViewById(R.id.theWord);
        String wordEntry = the_word.getText().toString();
        arrayListOfTasks.add(wordEntry);

        adapter.notifyDataSetChanged();
        the_word.setText("");

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        EditText toBeDeleted = (EditText) findViewById(R.id.the_item_text);
        for (int s = 0; s < arrayListOfTasks.size(); s++) {
            if(arrayListOfTasks.get(s).equals(toBeDeleted))
            {
                arrayListOfTasks.remove(s);
            }
        }

        return false;
    }
}

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/