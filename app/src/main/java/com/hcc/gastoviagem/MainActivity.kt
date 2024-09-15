package com.hcc.gastoviagem

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.hcc.gastoviagem.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener {

    // View Binding (Conectar a interface com o código)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicializa o View Binding (Conectar a interface com o código)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener(this)
    }


    //Boas praticas: Overide sao as primeiras funcoes
    override fun onClick(view: View) {
        if (view.id == R.id.btn_calculate) {
            calculate()
        }
    }

    private fun isValid(): Boolean {
        return (binding.editDistance.text.toString() != "" &&
                binding.editPrice.text.toString() != "" &&
                binding.editAutonomy.text.toString() != "" &&
                binding.editAutonomy.text.toString().toFloat() != 0f)
    }

    @SuppressLint("SetTextI18n")
    private fun calculate() {

        if (isValid()){
            //Toast notification
            Toast.makeText(this, R.string.success, Toast.LENGTH_LONG).show()
            val distance = binding.editDistance.text.toString().toFloat()
            val price = binding.editPrice.text.toString().toFloat()
            val autonomy = binding.editAutonomy.text.toString().toFloat()
            val total = (distance*price)/autonomy
            binding.textTotalValue.text = "R$ ${"%.2f".format(total)}"
        } else {
            Toast.makeText(this, R.string.validation_fill_all_fields, Toast.LENGTH_LONG).show()
        }
    }
}
