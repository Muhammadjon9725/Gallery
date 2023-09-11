package com.example.gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gallery.Adapter.RecyAdapter
import com.example.gallery.databinding.ActivityMainBinding
import com.example.gallery.databinding.DialogItemBinding
import com.example.gallery.databinding.MyViewPagerItemBinding
import com.example.gallery.db.MyDbHelper
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    lateinit var myDbHelper: MyDbHelper
    lateinit var recyAdapter: RecyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        myDbHelper = MyDbHelper(this)
        recyAdapter = RecyAdapter()
        recyAdapter.list.addAll(myDbHelper.getAllImages() )

        binding.apply {
            btnAdd.setOnClickListener{
                viewPager.adapter = recyAdapter
                showDialog()
            }




        }
    }
    lateinit var itemDialog:DialogItemBinding
    fun showDialog() {
        val dialog = BottomSheetDialog(this)

        itemDialog = DialogItemBinding.inflate(layoutInflater)
        dialog.setContentView(itemDialog.root)
        dialog.show()

        itemDialog.dialogImage.setOnClickListener {
            MyImageContent.launch("image/*")
        }

        itemDialog.dialogBtnsave.setOnClickListener {
            if (filePath!="" && itemDialog.dialogEdittext.text.isNotBlank()){

                val myImage = Mytype(itemDialog.dialogEdittext.text.toString(),filePath)
                Toast.makeText(this, "Saqlandi", Toast.LENGTH_SHORT).show()
                dialog.cancel()
                recyAdapter.list.add(myImage)
                recyAdapter.notifyDataSetChanged()
            }
            else{
                Toast.makeText(this, "Avval hamma malumotlarni kiriting!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
    var filePath = ""
        val MyImageContent =
            registerForActivityResult(ActivityResultContracts.GetContent()) {

                if (it == null) {
                    return@registerForActivityResult
                }

                val simpleDateFormat = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val inputStream = contentResolver.openInputStream(it)
                val file = File(filesDir, "$simpleDateFormat.jpg")
                val fileOutputStream = FileOutputStream(file)
                inputStream?.copyTo(fileOutputStream)
                inputStream?.close()
                fileOutputStream.close()
                filePath = file.absolutePath
                itemDialog.dialogImage.setImageURI(it)

            }
}