package com.Innaval.ConversordeMoedas3

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class ConversaoRepository(private val context: Context) {

    fun baixarDTODaInternet(
        respostaSucesso: Response.Listener<JSONObject>,
        respostaErro: Response.ErrorListener
    ){
        val requisicoes = Volley.newRequestQueue(context)
        val url = "https://api.exchangeratesapi.io/latest?base=BRLainda"
        val metodo = Request.Method.GET
        val body = null
        val requisicaoJson = JsonObjectRequest(
            metodo,
            url,
            body,
            respostaSucesso,
            respostaErro
        )
        requisicoes.add(requisicaoJson)
    }
}