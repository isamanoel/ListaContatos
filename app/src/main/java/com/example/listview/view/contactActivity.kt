package com.example.listview.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listview.databinding.ActivityContactBinding
import com.example.listview.model.Contact
import kotlin.random.Random

class contactActivity : BaseActivity() {
    private val acb:ActivityContactBinding by lazy{
        ActivityContactBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(acb.root)

        supportActionBar?.subtitle = "Informações do contato"

        acb.saveBt.setOnClickListener{
            val contact: Contact = Contact(
                id = generateId(),
                name = acb.nameEt.text.toString(),
                address = acb.addressEt.text.toString(),
                phone = acb.phoneEt.text.toString(),
                email = acb.emailEt.text.toString()
            )

            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_CONTACT, contact)
            setResult(RESULT_OK, resultIntent)
            finish()

        }
    }

    private fun generateId(): Int{
        val random = Random(System.currentTimeMillis())
        return random.nextInt()
    }
}