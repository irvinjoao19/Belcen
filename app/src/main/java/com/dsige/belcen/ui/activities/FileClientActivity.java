package com.dsige.belcen.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;

import com.dsige.belcen.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class FileClientActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
        @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;
//    @BindView(R.id.bar)
//    BottomAppBar bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_client);
        ButterKnife.bind(this);
        bindUI();
    }

    private void bindUI() {
//        roomViewModel = ViewModelProviders.of(this).get(RoomViewModel.class);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Cliente");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(view -> finish());
//        bar.inflateMenu(R.menu.navigation_menu);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.file_client, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.editar) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
