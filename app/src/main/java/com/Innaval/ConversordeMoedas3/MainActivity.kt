package com.Innaval.ConversordeMoedas3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import com.Innaval.ConversordeMoedas3.EstadoResposta.*
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    lateinit var etValor: EditText
    lateinit var tvResultado: TextView
    lateinit var btConverter: Button
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etValor = findViewById(R.id.etValor)
        tvResultado = findViewById(R.id.tvResultado)
        btConverter.setOnClickListener {
            viewModel.baixarConversaoDeMoedas(etValor.text.toString())
        }
        viewModel.getLiveData().observe(this){estadoResposta ->
            when (estadoResposta){
                is EstadoCarregando -> btConverter.isEnabled = false
                is EstadoSucesso -> {
                    btConverter.isEnabled = true
                    exibirResultadoCalculado(EstadoResposta.respostaDTO, estadoResposta.reais)
                }
                is EstadoErroConexao -> {
                    btConverter.isEnabled = true
                    exibirErroConexao()
                }
                is EstadoNumeroInvalido -> {
                    btConverter.isEnabled = true
                    exibirErroDigitacao()
                }
            }
        }
    }

    private fun exibirResultadoCalculado(respostaDTO: RespostaDTO, reais:Double){
        val df =DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        val constanteDolar = respostaDTO.rates.USD
        val dolar = reais*constanteDolar
        val resultado = df.format(dolar)
        val resultadoFinal = "Valor em Dolar: $resultado USD$"
        tvResultado.text = resultadoFinal
    }
    private fun exibirErroDigitacao(){
        tvResultado.text
    }
    private fun exibirErroConexao(){
        tvResultado.text = "Erro ao Conectar com o Servidor"
    }
}