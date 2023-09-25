package com.android.architecture

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity

class ChooseCountry: AppCompatActivity() {
    private lateinit var checkbox:CheckBox
    private var country = ""
    private lateinit var prefference:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_country)
        prefference = getSharedPreferences("com.android.architecture"+1,MODE_PRIVATE)
        checkbox = findViewById(R.id.canada);
        val editor = prefference.edit()
        try {
            if(prefference.contains("country") && prefference.getString("country","") != "") {
                country = prefference.getString("country", "")!!
                println(country)
            }
        }catch (e:AssertionError){
            throw RuntimeException("Assertuobn Error")
        }
        checkbox.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked){
                country  = "ca"
                editor.putString("country",country)
                editor.putBoolean("checked",true)
                editor.apply()
                intent.putExtra("country",country)
                setResult(RESULT_OK,intent)
            }
            else{
                editor.putBoolean("checked",false)
                editor.apply()
            }
        }
      findViewById<CheckBox>(R.id.us).setOnCheckedChangeListener { compoundButton, isChecked->
            if (isChecked){
                country  = "us"
                editor.putString("country",country)
                editor.apply()
                editor.putBoolean("checked",true)
                editor.apply()
                intent.putExtra("country",country)
                setResult(RESULT_OK,intent)
            }
            else{
                editor.putBoolean("checked",false)
                editor.apply()
            }
        }

        findViewById<CheckBox>(R.id.ind).setOnCheckedChangeListener { compoundButton, isChecked->
            if (isChecked){
                country  = "in"
                editor.putString("country",country)
                editor.apply()
                editor.putBoolean("checked",true)
                editor.apply()
                intent.putExtra("country",country)
                setResult(RESULT_OK,intent)
            }
            else{
                editor.putBoolean("checked",false)
                editor.apply()
            }
        }
        when(country){
            "in" ->{
                var is_che = prefference.getBoolean("checked",false)
                if(is_che) {
                    findViewById<CheckBox>(R.id.ind).isChecked = is_che
                    findViewById<CheckBox>(R.id.us).isChecked = false
                    findViewById<CheckBox>(R.id.canada).isChecked = false
                }
                else{
                    findViewById<CheckBox>(R.id.ind).isChecked = is_che
                }
            }
            "ca" ->{
                var is_che = prefference.getBoolean("checked",false)
                if(is_che) {
                    findViewById<CheckBox>(R.id.ind).isChecked = false
                    findViewById<CheckBox>(R.id.us).isChecked = false
                    findViewById<CheckBox>(R.id.canada).isChecked = is_che
                }
                else{
                    findViewById<CheckBox>(R.id.canada).isChecked = is_che
                }
            }
            "us"->{
                var is_che = prefference.getBoolean("checked",false)
                if(is_che) {
                    findViewById<CheckBox>(R.id.ind).isChecked = false
                    findViewById<CheckBox>(R.id.us).isChecked = is_che
                    findViewById<CheckBox>(R.id.canada).isChecked = false
                }
                else{
                    findViewById<CheckBox>(R.id.us).isChecked = is_che
                }
            }
            else->{
                country = ""
                editor.putString("country","")
                editor.apply()
                intent.putExtra("country",country)
                setResult(RESULT_OK,intent)
            }
        }
    }
    override fun onStop() {
        super.onStop()
        finish()
    }
}