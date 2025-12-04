package com.gmail.denuelle42.aiprompter.data.repositories.auth.response


import androidx.annotation.Keep
import com.gmail.denuelle42.aiprompter.data.remote.models.Meta
import com.gmail.denuelle42.aiprompter.data.remote.models.UserModel

@Keep
data class HelloResponse(
    val status: String? = null, // eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vbGFyYXZlbC1hcGkudGVzdC9hcGkvdjEvYXV0aC9sb2dpbiIsImlhdCI6MTc2NDU1MTY5OCwiZXhwIjoxNzY0NTU1Mjk4LCJuYmYiOjE3NjQ1NTE2OTgsImp0aSI6Inh4Wks4ZVJLV2Q1WnFicVoiLCJzdWIiOiIxIiwicHJ2IjoiMjNiZDVjODk0OWY2MDBhZGIzOWU3MDFjNDAwODcyZGI3YTU5NzZmNyJ9.A9D2miSe9wCcr_xbYlv28C83ah8LXy6ADPqNiPwS6l4
    val message: String? = null, // Bearer
)