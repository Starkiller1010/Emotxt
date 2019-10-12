import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { environment as env } from '../../../environments/environment';
import { map, catchError } from 'rxjs/operators';
import { Subject, Observable, Observer } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConnectionService {

  constructor(private http: HttpClient) { }

  private subject: Subject<MessageEvent>;

  public connect(url): Subject<MessageEvent> {
    if (!this.subject) {
      this.subject = this.create(url);
      console.log('Successfully connected: ' + url);
    }
    return this.subject;
  }

  private create(url): Subject<MessageEvent> {
    const ws = new WebSocket(url);

    const observable = Observable.create((obs: Observer<MessageEvent>) => {
      ws.onmessage = obs.next.bind(obs);
      ws.onerror = obs.error.bind(obs);
      ws.onclose = obs.complete.bind(obs);
      return ws.close.bind(ws);
    });
    const observer = {
      next: (data: object) => {
        if (ws.readyState === WebSocket.OPEN) {
          ws.send(JSON.stringify(data));
        }
      }
    };
    return Subject.create(observer, observable);
  }

  /**
   * A HTTP `GET` method request to the endpoint of the api specified in the `route` param
   * @param  route URI to API endpoint which will be processing the request.
   * @param  headers Headers that can be included to the request.
   * @return  A `Observable<HttpResponse<object>>` that was returned by the API endpoint.
   */
  sendGet = (route: string, headers?: HttpHeaders) => {
    return this.http.get(`${env.api_url}/${route}`, { headers }).pipe(
      map(resp => {
        console.log(`In ConnectionService.sendGet(), retrieved: ${resp}`);
        return resp as HttpResponse<object>;
      })
    );
  }

  /**
   * A HTTP `POST` method request to the endpoint of the api specified in the `route` param
   * @param  route URI to API endpoint which will be processing the request.
   * @param  headers Headers that can be included to the request.
   * @param  body The body of the request that will be processed by the API.
   * @return  A `Observable<HttpResponse<object>>` that was returned by the API endpoint.
   */
  sendPost = (route: string, body, headers?: HttpHeaders) => {
    return this.http.post(`${env.api_url}/${route}`, body, { headers }).pipe(
      map(resp => {
        console.log(`In ConnectionService.sendPost(), retrieved: ${resp}`);
        return resp as HttpResponse<object>;
      })
    );
  }

  /**
   * A HTTP `PUT` method request to the endpoint of the api specified in the `route` param
   * @param  route URI to API endpoint which will be processing the request.
   * @param  headers Headers that can be included to the request.
   * @param  body The body of the request that will be processed by the API.
   * @return  A `Observable<HttpResponse<object>>` that was returned by the API endpoint.
   */
  sendPut = (route: string, body, headers?: HttpHeaders) => {
    return this.http.put(`${env.api_url}/${route}`, body, { headers }).pipe(
      map(resp => {
        console.log(`In ConnectionService.sendPut(), retrieved: ${resp}`);
        return resp as HttpResponse<object>;
      })
    );
  }

  /**
   * A HTTP `DELETE` method request to the endpoint of the api specified in the `route` param
   * @param  route URI to API endpoint which will be processing the request.
   * @param  headers Headers that can be included to the request.
   * @param  body The body of the request that will be processed by the API.
   * @return  A `Observable<HttpResponse<object>>` that was returned by the API endpoint.
   */
  sendDelete = (route: string, body, headers?: HttpHeaders) => {
    return this.http.put(`${env.api_url}/${route}`, body, { headers }).pipe(
      map(resp => {
        console.log(`In ConnectionService.sendPut(), retrieved: ${resp}`);
        return resp as HttpResponse<object>;
      })
  );
}
}
