package com.enml.bazar.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.enml.bazar.R;
import com.enml.bazar.data.ViewModelProviders;
import com.enml.bazar.data.model.interfaces.ICategory;
import com.enml.bazar.data.repositories.Resource;
import com.enml.bazar.data.viewmodel.CategoryViewModel;
import com.enml.bazar.utils.MultipleMunicipalitySelectionSpinner;
import com.enml.bazar.utils.MultipleSelectionSpinner;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

public class NewItemActivity extends AppCompatActivity {

    //MultipleSelection spinner object
    MultipleSelectionSpinner provinceSpinner;
    MultipleMunicipalitySelectionSpinner municipalitySpinner;

    //List which hold multiple selection spinner values
    List<String> list = new ArrayList<String>();

    ImageView image1, image2, image3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_new_item);

        CategoryViewModel categoryViewModel = (CategoryViewModel) ViewModelProviders.get(this, CategoryViewModel.class);

        categoryViewModel.getCategories().observe(this, new Observer<Resource<List<ICategory>>>() {
            @Override
            public void onChanged(Resource<List<ICategory>> listResource) {

            }
        });

        image1 = findViewById(R.id.image1);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photoFromGallery();
            }
        });

        MaterialSpinner spinner = findViewById(R.id.spinnerStatus);
        spinner.setItems("Estado del Producto", "Nuevo", "Usado");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });

        MaterialSpinner spinner1 = findViewById(R.id.spinnerCategory);
        spinner1.setItems("Seleccione la Categoria", "Celulares y Electrónicos", "Computadoras", "Para su Casa", "Moda y Belleza",
                "Deporte, Musica y Hobbies", "Artículos Infantiles", "Animales Domésticos",
                "Autos", "Vivienda", "Empleos", "Servicios", "Divisas");
        spinner1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });

        MaterialSpinner spinner2 = findViewById(R.id.spinnerSubCategory);
        spinner2.setItems("Seleccione la Sub-Categoria", "Celulares y Electrónicos", "Computadoras", "Para su Casa", "Moda y Belleza",
                "Deporte, Musica y Hobbies", "Artículos Infantiles", "Animales Domésticos",
                "Autos", "Vivienda", "Empleos", "Servicios", "Divisas");
        spinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });

        //casting of spinner
        provinceSpinner = findViewById(R.id.provinceSpinner);
        municipalitySpinner = findViewById(R.id.municipiosSpinner);

        //adding items to list
        list.add("Pinar del Rio");
        list.add("Artemisa");
        list.add("Mayabeque");
        list.add("Habana");
        list.add("Matanzas");
        list.add("Villa Clara");
        list.add("Cienfuegos");

        //set items to spinner from list
        provinceSpinner.setItems(list);
        municipalitySpinner.setItems(list);

        /*//onClick listener of button for showing multiple selection spinner values
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewItemActivity.this, "Selected : " + provinceSpinner.getSelectedItemsAsString() , Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    private void photoFromGallery() {
        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.STORAGE)
                .onGranted(new com.yanzhenjie.permission.Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Album.image(NewItemActivity.this) // Image selection.
                                .multipleChoice()
                                .camera(true)
                                .columnCount(3)
                                .selectCount(3)
                                .onResult(new Action<ArrayList<AlbumFile>>() {
                                    @Override
                                    public void onAction(@NonNull ArrayList<AlbumFile> result) {
                                        ArrayList<File> files = new ArrayList<>();
                                        for (AlbumFile albumFile : result) {
                                            files.add(new File(albumFile.getPath()));
                                        }
                                        //callback.onAction(files);
                                    }
                                })
                                .onCancel(new Action<String>() {
                                    @Override
                                    public void onAction(@NonNull String result) {
                                    }
                                })
                                .start();

                    }
                })
                .onDenied(new com.yanzhenjie.permission.Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {

                    }
                })
                .start();
    }
}