package com.anhquanha.passkeeper.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.anhquanha.passkeeper.MainApplication;
import com.anhquanha.passkeeper.R;
import com.anhquanha.passkeeper.model.Account;
import com.anhquanha.passkeeper.util.DateUtil;
import com.anhquanha.passkeeper.util.StringUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAccountActivity extends AppCompatActivity{
    @BindView(R.id.create_account_btn)
    Button createAccountBtn;
    @BindView(R.id.idNewAccount)
    TextView idTv;
    @BindView(R.id.passNewAccount)
    TextView passTv;
    @BindView(R.id.confirmPassNewAccount)
    TextView confirmPassTv;
    @BindView(R.id.category_spinner)
    Spinner categorySpinner;
    @BindView(R.id.backBtn)
    ImageView backBtn;
    @BindView(R.id.fragmentNameTv)
    TextView fragmentNameTv;

    ArrayList<String> categoryList = new ArrayList<>();
    ArrayAdapter<String> adapter ;

    String id = "";
    String password = "";
    String confirmedPassword = "";
    String category = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_layout);
        ButterKnife.bind(this);

        initCategoryData();

        createAccountBtn.setOnClickListener(view -> {
            handleCreateButton();
        });
        backBtn.setOnClickListener(v->{
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        fragmentNameTv.setText(StringUtil.getStringResource(R.string.create_account));
    }

    private void handleCreateButton() {
        id = idTv.getText().toString();
        password = passTv.getText().toString();
        confirmedPassword = confirmPassTv.getText().toString();

        if(id.equals("")){
            idTv.setError(getResources().getString(R.string.blank_id_error));
            return;
        }
        if(password.equals("")){
            passTv.setError(getResources().getString(R.string.blank_error));
            return;
        }
        if(confirmedPassword.equals("")){
            confirmPassTv.setError(getResources().getString(R.string.blank_error));
            return;
        }
        if(!password.equals(confirmedPassword))
        {
            Toast.makeText(this, "Two passwords must be the same!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(category.equals("")){
            Toast.makeText(this, "Please choose a category!", Toast.LENGTH_SHORT).show();
            return;
        }


        /** pass all case, now we create new account in SQLite database **/
        createNewAccount(id, password, category, MainApplication.getUserInfo().getId(), DateUtil.formatDateTime(System.currentTimeMillis()));
        //Toast.makeText(this, "Create success", Toast.LENGTH_SHORT).show();
    }

    private void createNewAccount(String id, String password, String category, String idUser, String createdAt) {
        Account account = new Account( id, password, category, MainApplication.getUserInfo().getId(), createdAt);
        MainApplication.getDatabaseHandler().createAccount(account);
        Toast.makeText(this, "Create new account successfully!", Toast.LENGTH_SHORT).show();
    }

    private void initCategoryData() {
        categoryList.add("Banking");
        categoryList.add("PC/Laptop");
        categoryList.add("Social Networking");
        categoryList.add("Education");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryList);

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        categorySpinner.setAdapter(adapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}
