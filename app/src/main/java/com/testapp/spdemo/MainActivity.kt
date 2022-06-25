  package com.testapp.spdemo

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

  class MainActivity : AppCompatActivity() {
    /*we declare these variables outside of the onCreate method
    **The reason why we declare them here, we need to access them from other functions of the class
    * If we declare them on create, we would only be able to access them inside onCreate function
    **these are the two fields we created
    * */
    private lateinit var nameText: EditText
    private lateinit var ageText:EditText
    //we are going to define two reference variables for shared preference and the shared preference editor
    private lateinit var sf:SharedPreferences
    private lateinit var editor:SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameText = findViewById(R.id.userName)
        ageText = findViewById(R.id.userAge)
/*    ** we initialize the shared preferences instance here
      ** my_sf will be the name of the file we'll create inside the phone's data folder to store shared pref
      ** the other parameter is context
      ** If we choose mode private, access to this file will be limited to our app
      ** If we choose mode append, this will append with the already existing preferences
 */
        sf = getSharedPreferences("my_sf", MODE_PRIVATE)
        editor = sf.edit()
    }
      override fun onPause(){
          super.onPause()
          val name = nameText.text.toString()
          val age = ageText.text.toString().toInt()
          editor.apply{
              putString("sf_name", name)
              putInt("sf_age", age)
//             NB:: we must use commit, otherwise the data will not be saved
              commit()
          }

      }
//      To display the saved data, you will override the onResume function
      override fun onResume(){
        super.onResume()
        val name = sf.getString("sf_name", null)
        val age = sf.getInt("sf_age",0)
        nameText.setText(name)
        if( age!= 0 ){
            ageText.setText(age.toString())
        }

      }
}