package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myapplication.database.NotesDB
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.viewmodels.MainActivityVM
import com.example.myapplication.viewmodels.MainActivityVMF

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        setupActionBarWithNavController(findNavController(R.id.fragmentContainerView))

        val application = requireNotNull(this).application
        val dao = NotesDB.getInstance(application).getNotesDao()
        val viewModelFactory = MainActivityVMF(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainActivityVM::class.java]
    }

    override fun onSupportNavigateUp(): Boolean {
        val controller = findNavController(R.id.fragmentContainerView)
        return controller.navigateUp() || super.onSupportNavigateUp()
    }
}