package com.puja9.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private val context: Context, private val listener : INotesAdapter): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>(){

    val allNotes = ArrayList<Note>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val itemText = itemView.findViewById<TextView>(R.id.saved_text)
        val deleteBtn = itemView.findViewById<ImageView>(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent, false))
        viewHolder.deleteBtn.setOnClickListener {
        listener.onItemClick((allNotes[viewHolder.adapterPosition]))
        }
        return  viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.itemText.text = currentNote.text
    }

    override fun getItemCount(): Int {
       return allNotes.size
    }

    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
}

interface INotesAdapter{
    fun onItemClick(note: Note)
}