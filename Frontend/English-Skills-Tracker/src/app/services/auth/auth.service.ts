import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { Login } from '../../models/login';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loginEndPoint ='http://localhost:8080/auth/login'; // URl de  el endpoint de Login

  constructor(private http : HttpClient) { }

  loginUser(userlogged:Login):Observable<any>{// Metodo para logear el usuario 
    return this.http.post<Login>(this.loginEndPoint,userlogged).pipe(// flujo de datos para obtener el token 
      map((response:any)=>{// Mapea la respuesta y otiene ek token
        if(response && response.token){//Si la respuesta y el token son validos entonces
          const payload = this.extractPayload(response.token); // Extrae el payload del token 
          return payload //Retorna el payload 
        }
      })
    )
    

  }

  extractPayload(token:string):any{// Metodo para extraer el payload del token 
    try {
       const payload = token.split('.')[1];// Separa el token en 3 partes y obtiene la segunda parte que es el payload
       const decodePayload = atob(payload);// Decodifica el payload ya que atob es una funcion que decodifica el base64

       return JSON.parse(decodePayload);// retorna el payload decodificado y parseado a JSON 
    } catch (error) {
      console.error('Error al decodificar el token',error);
      return null;
    }
  }
}
