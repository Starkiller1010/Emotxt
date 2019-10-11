import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { environment as env } from '../../../environments/environment';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ConnectionService {

  constructor(private http: HttpClient) { }

  // tslint:disable-next-line: align
  sendGet = (headers?: HttpHeaders) => {
    return this.http.get(`${env.api_url}`, { headers }).pipe(
      map(resp => {
        console.log(`In ConnectionService.sendGet(), retrieved: ${resp}`);
        return resp as HttpResponse<object>;
      })
    );
  }

  sendPost = (body, headers?: HttpHeaders) => {
    return this.http.post(`${env.api_url}`, body, { headers }).pipe(
      map(resp => {
        console.log(`In ConnectionService.sendPost(), retrieved: ${resp}`);
        return resp as HttpResponse<object>;
      })
    );
  }

  sendPut = (body, headers?: HttpHeaders) => {
    return this.http.put(`${env.api_url}`, body, { headers }).pipe(
      map(resp => {
        console.log(`In ConnectionService.sendPut(), retrieved: ${resp}`);
        return resp as HttpResponse<object>;
      })
    );
  }
}
