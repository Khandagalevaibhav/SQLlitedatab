package tatastrive.application.sqllitedatabase;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {
    EditText etr, etn, etm;
    Button btadd, btdelete, btmodify, btview, btviewall, btshow;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        etr = findViewById( R.id.editRollno );
        etn = findViewById( R.id.editName );
        etm = findViewById( R.id.editMarks );
        btadd = findViewById( R.id.Add );
        btdelete = findViewById( R.id.Delete );
        btmodify = findViewById( R.id.Modify );
        btview = findViewById( R.id.View );
        btviewall = findViewById( R.id.Viewall );
        btshow = findViewById( R.id.Show );

        btadd.setOnClickListener( this );
        btdelete.setOnClickListener( this );
        btmodify.setOnClickListener( this );
        btview.setOnClickListener( this );
        btviewall.setOnClickListener( this );
        btshow.setOnClickListener( this );
        etr.setOnClickListener( this );
        etn.setOnClickListener( this );
        etm.setOnClickListener( this );
        db = openOrCreateDatabase( "studentDB", MODE_PRIVATE, null );
        db.execSQL( "CREATE TABLE IF NOT EXISTS student( rollno VARCHAR,name VARCHAR,marks VARCHAR);" );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Add:
                if (etr.getText().toString().trim().length() == 0 || etn.getText().toString().trim().length() == 0 || etm.getText().toString().trim().length() == 0) {
                    Toast.makeText( this, "Invalid input", Toast.LENGTH_SHORT ).show();
                    shwMsg( "Error", "Invalid input" );
                    return;
                }
                db.execSQL("INSERT INTO student VALUES('"+etr.getText()+"','"+etn.getText()+
                        "','"+etm.getText()+"');");
                shwMsg("Success", "Record added");
                 clearText();
                Toast.makeText( this, "Add", Toast.LENGTH_SHORT ).show();
                break;

            case R.id.Delete:
                if(etr.getText().toString().trim().length()==0)
                {
                    shwMsg("Error", "Please enter Rollno");
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+etr.getText()+"'", null);
                if(c.moveToFirst())
                {
                    db.execSQL("DELETE FROM student WHERE rollno='"+etr.getText()+"'");
                    shwMsg("Success", "Record Deleted");
                }
                else
                {
                    shwMsg("Error", "Invalid Rollno");
                }
                clearText();

                break;

            case R.id.Modify:
                if(etr.getText().toString().trim().length()==0)
                {
                    shwMsg("Error", "Please enter Rollno");
                    return;
                }
                Cursor c1 =db.rawQuery("SELECT * FROM student WHERE rollno='"+etr.getText()+"'", null);
                if(c1.moveToFirst())
                {
                    db.execSQL("UPDATE student SET name='"+etn.getText()+"',marks='"+etm.getText()+
                            "' WHERE rollno='"+etr.getText()+"'");
                    shwMsg("Success", "Record Modified");
                }
                else
                {
                    shwMsg("Error", "Invalid Rollno");
                }
                clearText();
                break;

            case R.id.View:
                if(etr.getText().toString().trim().length()==0)
                {
                    shwMsg("Error", "Please enter Rollno");
                    return;
                }
                Cursor c2=db.rawQuery("SELECT * FROM student WHERE rollno='"+etr.getText()+"'", null);
                if(c2.moveToFirst())
                {
                    etn.setText(c2.getString(1));
                    etm.setText(c2.getString(2));
                }
                else
                {
                    shwMsg("Error", "Invalid Rollno");
                    clearText();
                }
                break;

            case R.id.Viewall:
                Cursor c3=db.rawQuery("SELECT * FROM student", null);
                if(c3.getCount()==0)
                {
                    shwMsg("Error", "No records found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c3.moveToNext())
                {
                    buffer.append("Rollno: "+c3.getString(0)+"\n");
                    buffer.append("Name: "+c3.getString(1)+"\n");
                    buffer.append("Marks: "+c3.getString(2)+"\n\n");
                }
                shwMsg("Student Details", buffer.toString());
                Toast.makeText( this, "ViewAll", Toast.LENGTH_SHORT ).show();
                break;

            case R.id.Show:
                Toast.makeText( this, "Show", Toast.LENGTH_SHORT ).show();
                shwMsg( "Devloped By-","Mr.Sushant Bansode" );
                break;

            case R.id.editMarks:
                Toast.makeText( this, "editmarks", Toast.LENGTH_SHORT ).show();
                break;


            case R.id.editName:
                Toast.makeText( this, "editname", Toast.LENGTH_SHORT ).show();
                break;

            case R.id.editRollno:
                Toast.makeText( this, "editRollno", Toast.LENGTH_SHORT ).show();
                break;
        }
    }

    private void clearText() {
        etm.setText("");
        etn.setText("");
        etr.setText("");
    }

    private void shwMsg(String title, String msg) {
        Builder alertDialog=new AlertDialog.Builder(this);
        alertDialog.setCancelable( true );
        alertDialog.setTitle( title );
        alertDialog.setMessage( msg);
        alertDialog.setIcon( R.mipmap.ic_launcher_round );
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.me_set:
                startActivity( new Intent( Settings.ACTION_SETTINGS ) );
                break;
            case R.id.me_wait:
                AlertDialog.Builder builder=new AlertDialog.Builder( this );
                builder.setMessage( "keep waiting" );
                builder.setMessage( "keep setting" );
                return true;
        }
        return super.onOptionsItemSelected( item );
    }
}
