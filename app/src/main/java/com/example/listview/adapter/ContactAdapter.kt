package com.example.listview.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.listview.R
import com.example.listview.databinding.TileContactBinding
import com.example.listview.model.Contact

class ContactAdapter(
    context: Context,
    private val contactList: MutableList<Contact>
): ArrayAdapter<Contact>(context, R.layout.tile_contact, contactList) {
    private lateinit var tcb: TileContactBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val contact: Contact = contactList[position]

        var tileContactView = convertView
        if(tileContactView == null){
            //infla uma nova célula
            tcb = TileContactBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater, parent, false
            )
            tileContactView = tcb.root
        }

        //substituir os valore
        tileContactView.findViewById<TextView>(R.id.nameTv).text = contact.name
        tileContactView.findViewById<TextView>(R.id.emailTv).text = contact.email


        return tileContactView
    }
}