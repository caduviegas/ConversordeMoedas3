package com.Innaval.ConversordeMoedas3

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.Innaval.ConversordeMoedas3.EstadoResposta.*
import com.android.volley.Response
import com.google.gson.Gson
import org.json.JSONObject

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val repository = ConversaoRepository(context)
    private val liveData = MutableLiveData<EstadoResposta>()

    fun getLiveData():LiveData<EstadoResposta>{
        return liveData
    }

    fun baixarConversaoDeMoedas(valorDigitado:String){
        val reais = valorDigitado.toDoubleOrNull()
        if(reais==null){
            liveData.postValue(EstadoNumeroInvalido())
        } else{
            liveData.postValue(EstadoCarregando())
            val respostaSucesso = Response.Listener<JSONObject>{ json ->
                if(json != null){
                    val gson = Gson()
                    val respostaDTO = gson.fromJson<RespostaDTO>(json.toString(),
                    RespostaDTO::class.java)
                    liveData.postValue(EstadoSucesso(respostaDTO, reais))
                }else{
                    liveData.postValue(EstadoErroConexao())
                }
            }
            val respostaErro = Response.ErrorListener{
                liveData.postValue(EstadoErroConexao())
            }
            repository.baixarDTODaInternet(respostaSucesso, respostaErro)
        }
    }
}