package ase.weather;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText name,password;
    Button login;
    String mName="",mPassword="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        intializeViews();
    }

    private void intializeViews() {
        name=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        if(id==R.id.login){
            if(isValid()){
                hideKeyboard();
                Intent intent=new Intent(MainActivity.this,GetWeather.class);
                intent.putExtra("Name",mName);
                startActivity(intent);
                finish();
            }
        }
    }

    public boolean isValid() {
        mName=name.getText().toString();
        mPassword=password.getText().toString();
        if(!mName.equals("admin")){
            name.setError("Enter valid name");
            name.requestFocus();
            return false;
        }
        else if(!mPassword.equals("admin")){
            password.setError("Enter valid password");
            password.requestFocus();
            return false;
        }
        return true;
    }

    public void hideKeyboard(){
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
