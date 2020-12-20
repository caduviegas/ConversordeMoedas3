package com.Innaval.ConversordeMoedas3

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CotacoesDTO(
    @SerializedName("USD") val USD:Double
): Parcelable
