import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class LibroService {

  private baseURL = "http://localhost:8080/api/libros"

  constructor(private http: HttpClient) {}

  findAll(): Observable<Libro[]> {
    return this.http.get<Libro[]>(this.baseURL);
  }

  findOne(id: number): Observable<Libro> {
    return this.http.get<Libro>(`${this.baseURL}/${id}`);
  }

  save(Libro: Libro): Observable<Libro> {
    return this.http.post<Libro>(this.baseURL, Libro);
  }

  update(id: number, Libro: Libro): Observable<Libro> {
    return this.http.put<Libro>(`${this.baseURL}/${id}`, Libro);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseURL}/${id}`);
  }

}
