import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BackendService {

  // httpOptions = {
  //   headers: new HttpHeaders({
  //     'Content-Type':  'application/json',
  //   })
  // };

  baseUrl:string="http://localhost:2020/sendEmail"

  constructor(private http:HttpClient) { }

  sendEmail(data:any)
  {
      return this.http.post(this.baseUrl, data);
  }

}

