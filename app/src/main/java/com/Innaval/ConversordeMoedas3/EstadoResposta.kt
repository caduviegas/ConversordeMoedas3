package com.Innaval.ConversordeMoedas3


sealed class EstadoResposta{
    class EstadoCarregando():EstadoResposta()
    class EstadoSucesso(val respostaDTO: RespostaDTO, val reais:Double): EstadoResposta()
    class EstadoErroConexao(): EstadoResposta()
    class EstadoNumeroInvalido(): EstadoResposta()
}
