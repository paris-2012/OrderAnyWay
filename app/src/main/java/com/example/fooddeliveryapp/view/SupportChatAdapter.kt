package com.example.fooddeliveryapp.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.SenderViewBinding
import com.example.fooddeliveryapp.databinding.SupportChatViewBinding
import com.example.fooddeliveryapp.model.remote.SupportChat
import com.example.fooddeliveryapp.model.remote.SupportChatSenderData

class SupportChatAdapter(
    private val context: Context,
    private val chats: MutableList<SupportChat>,
    private val supportChatSenderData: SupportChatSenderData
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var supportChatViewBinding: SupportChatViewBinding
    private lateinit var bindingSender: SenderViewBinding

    override fun getItemCount() = chats.size

    override fun getItemViewType(position: Int): Int {
        return chats[position].viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == SENDER_VIEW) {
            supportChatViewBinding = SupportChatViewBinding.inflate(layoutInflater, parent, false)
            SenderViewHolder(supportChatViewBinding.root)
        } else {
            bindingSender = SenderViewBinding.inflate(layoutInflater, parent, false)
            ReceiverViewHolder(bindingSender.root)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (chats[position].viewType == SENDER_VIEW) {
            (holder as SenderViewHolder).bind(position)
        } else {
            (holder as ReceiverViewHolder).bind(position)
        }

        holder.itemView.setOnLongClickListener {

            val builder = AlertDialog.Builder(context).apply {
                setIcon(R.drawable.ic_baseline_delete_24)
                setTitle(context.getString(R.string.delete))
                setMessage(context.getString(R.string.confirm_delete))
                setPositiveButton("Confirm") { dialog, _ ->
                    dialog.dismiss()
                    try {
                        if (position == chats.size) {
                            chats.removeAt(position - 1)
                            notifyItemRemoved(position - 1)
                        } else {
                            chats.removeAt(position)
                            notifyItemRemoved(position)
                        }
                    } catch (e: Exception) {
                    }
                }
                setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
            }

            val dialog = builder.create()
            dialog.setCancelable(false)
            dialog.show()

            true
        }
    }

    inner class SenderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val txtSender: TextView = supportChatViewBinding.txtSupport
        private val button1: Button = supportChatViewBinding.btnSupportMessage1
        private val button2: Button = supportChatViewBinding.btnSupportMessage2
        private val button3: Button = supportChatViewBinding.btnSupportMessage3

        fun bind(position: Int) {
            txtSender.text = chats[position].text
            button1.text = supportChatSenderData.button1
            button1.setOnClickListener {
                val intent = Intent(context, OrdersActivity::class.java)
                startActivity(context, intent, null)
            }
            button2.text = supportChatSenderData.button2
            button2.setOnClickListener {
                val intent = Intent(context, TrackingActivity::class.java)
                startActivity(context, intent, null)
            }
            button3.text = supportChatSenderData.button3
            button3.setOnClickListener {
                val intent = Intent(context, FavoriteActivity::class.java)
                startActivity(context, intent, null)
            }
        }
    }

    inner class ReceiverViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val txtSender: TextView = bindingSender.txtSender
        fun bind(position: Int) {
            txtSender.text = chats[position].text
        }
    }

    companion object {
        const val SENDER_VIEW = 1
    }
}