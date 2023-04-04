package com.example.listview.view

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.listview.R
import com.example.listview.adapter.ContactAdapter
import com.example.listview.databinding.ActivityMainBinding
import com.example.listview.model.Contact


class MainActivity : BaseActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //Data Source
    private val contactList: MutableList<Contact> = mutableListOf()

    //Adapter
    private val contactAdapter: ContactAdapter by lazy {
        ContactAdapter(
            this,
            contactList
        )
    }

    private lateinit var carl: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        supportActionBar?.subtitle = getString(R.string.contact_list)

        fillContactList()
        amb.contatosLv.adapter = contactAdapter

        carl = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
                val contact = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    result.data?.getParcelableExtra(EXTRA_CONTACT, Contact::class.java)
                }
                else{
                    result.data?.getParcelableExtra(EXTRA_CONTACT)
                }
                contact?.let {
                    contactList.add(it)
                    contactAdapter.notifyDataSetChanged()
                }

            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addContactMi -> {
                carl.launch(Intent(this, contactActivity::class.java))
                true
            }
            else -> false
        }
    }

    //função para preencher nossa DS

    private fun fillContactList(){
        for(index in 1 .. 50){
            contactList.add(
                Contact(
                    index,
                    "Nome $index",
                    "Endereço $index",
                    "Telefone $index",
                    "Email $index"
                )
            )
        }
    }
}