//abc
//def

package patrol.npd;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import database.LoginDataBaseAdapter;

public class MainActivity extends ActionBarActivity {

     LoginDataBaseAdapter DB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // create a instance of SQLite Database
        DB = new LoginDataBaseAdapter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login=(Button)findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               // Toast.makeText(getApplicationContext(), "login pressed", Toast.LENGTH_LONG).show();
                final EditText userName=(EditText)findViewById(R.id.userName);
                final EditText password=(EditText)findViewById(R.id.password);
                String user=userName.getText().toString();
                String pass=password.getText().toString();

                // fetch the Password from database for respective user name
                String storedPassword = DB.GetSinlgeEntry(user);

                if (pass.equals(storedPassword)) {
                    Toast.makeText(MainActivity.this,
                            "Congrats: Login Successfull", Toast.LENGTH_LONG)
                            .show();

                    // Set username to be used globally
                    GlobalVariables globalVariable = (GlobalVariables) getApplicationContext();
                    globalVariable.SetUserName(user);

                }else {
                        Toast.makeText(MainActivity.this,
                                "User Name or Password does not match",
                                Toast.LENGTH_LONG).show();
                                user = "arvind";
                                pass = "pass";
                        {
                           // Toast.makeText(getApplicationContext(), "in if", Toast.LENGTH_LONG).show();
                            Intent loggedin =new Intent(MainActivity.this,User.class);
                            loggedin.putExtra("user_name",user);
                            loggedin.putExtra("password", pass);
                            startActivity(loggedin);
                        }
                    }



            }
        });
    }


    @Override
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
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        if (DB.db != null)
            DB.db.close();
    }
}
