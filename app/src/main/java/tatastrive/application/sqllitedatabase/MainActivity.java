package tatastrive.application.sqllitedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener{
    EditText etr,etn,etm;
    Button btadd,btdelete,btmodify,btview,btviewall,btshow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        etr=findViewById( R.id.editRollno );
        etn=findViewById( R.id.editName );
        etm=findViewById( R.id.editMarks);
        btadd=findViewById( R.id.Add );
        btdelete=findViewById( R.id.Delete );
        btmodify=findViewById( R.id.Modify );
        btview=findViewById( R.id.View );
        btviewall=findViewById( R.id.Viewall);
        btshow=findViewById( R.id.Show );

        btadd.setOnClickListener( this );
        btdelete.setOnClickListener( this );
        btmodify.setOnClickListener( this );
        btview.setOnClickListener( this );
        btviewall.setOnClickListener( this );
        btshow.setOnClickListener( this );
        etr.setOnClickListener( this );
        etn.setOnClickListener( this );
        etm.setOnClickListener( this );

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Add:
                Toast.makeText( this, "Add", Toast.LENGTH_SHORT ).show();
                break;

            case R.id.Delete:
                Toast.makeText( this, "Delete", Toast.LENGTH_SHORT ).show();
                break;

            case R.id.Modify:
                Toast.makeText( this, "Modify", Toast.LENGTH_SHORT ).show();
                break;

            case R.id.View:
                Toast.makeText( this, "View", Toast.LENGTH_SHORT ).show();
                break;

            case R.id.Viewall:
                Toast.makeText( this, "ViewAll", Toast.LENGTH_SHORT ).show();
                break;

            case R.id.Show:
                Toast.makeText( this, "Show", Toast.LENGTH_SHORT ).show();
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
    }

