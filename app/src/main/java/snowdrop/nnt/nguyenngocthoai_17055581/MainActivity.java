package snowdrop.nnt.nguyenngocthoai_17055581;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    DataUser dataUser;
    Button btnAdd, btnRemove, btnCancel;
    EditText txtName;
    ListView lvName;
    ArrayList nameList, idList;
    ArrayAdapter adapter;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataUser = new DataUser(this, "userdb.sqlite", null, 1);
//        dataUser.addUser(new User("Thoai 1"));
//        dataUser.addUser(new User("Thoai 2"));
//        dataUser.addUser(new User("Thoai 3"));
        idList = new ArrayList();
        nameList = new ArrayList();

        txtName = findViewById(R.id.txtName);
        btnAdd = findViewById(R.id.btnAdd);
        btnRemove = findViewById(R.id.btnRemove);
        btnCancel = findViewById(R.id.btnCancel);
        lvName = findViewById(R.id.lv);

        getNameList();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nameList);
        lvName.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataUser.addUser(new User(txtName.getText().toString()));
                getNameList();
                adapter.notifyDataSetChanged();
                txtName.setText("");
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataUser.removeUser((Integer) idList.get(index));
                getNameList();
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        lvName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
            }
        });

    }

    private ArrayList getNameList() {
//        itco
//        ArrayList<String> nameList = new ArrayList<>();
        nameList.clear();
        idList.clear();
        for (Iterator iterator = dataUser.getAll().iterator(); iterator.hasNext(); ) {
            User user = (User) iterator.next();
            nameList.add(user.getName());
            idList.add(user.getId());
        }
        return nameList;
    }
}