import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  newUser = new BehaviorSubject<User>(new User);

  constructor(private http: HttpClient,private router: Router) {
    this.verifyActualSession();
  }
  verifyActualSession() {
    throw new Error('Method not implemented.');
  }

  public get activeUserSession(): User {
    return this.newUser.value;
  }
  /**
   * Permite actualizar la información del usuario
   * que acabó de validarse correctamente
   * @param user 
   
  setUser(user: User) {
    this.newUser.next(user);
  }
  /**
   * Permite obtener la información del usuario
   * con datos tales como el identificador y el token
   * @returns 
   */
  getUser() {
    return this.newUser.asObservable();
  }
  /**
   * Realiza la petición al backend con el correo y la contraseña
   * para verificar si existe o no en la plataforma
   * @param infoUser JSON con la información de correo y contraseña
   * @returns Respuesta HTTP la cual indica si el usuario tiene permiso de acceso
   */
  login(infoUser: User): Observable<User> {
    return this.http.post<User>(`${environment.url_gateway}/login`, infoUser);
  }

  /***

   */

  /**
   * Guarda los datos tales como el identificador
   * y token del usuario en una base de datos 
   * interna del navegador llamada local storage
   * @param dataSesion información del usuario
   * @returns un booleano que indica si la información 
   * fue almacenada correctamente
   */
   saveDataSession(dataSesion: any) {
    let actualSession = localStorage.getItem('sesion');
      let data: User = {
        e_mail: dataSesion.user_id,
        password:dataSesion.password,
        token:dataSesion.token,
      };
      localStorage.setItem('session', JSON.stringify(data));
      this.setUser(data);
  }
  setUser(data: User) {
    throw new Error('Method not implemented.');
  }

  /**
   * Permite cerrar la sesión del usuario
   * que estaba previamente logueado
   */
  logout() {
    localStorage.removeItem('session');
    this.setUser(new User());
  }
  /**
   * Permite verificar si actualmente en el local storage
   * existe información de un usuario previamente logueado 
   */
  verificarusu() {
    let actualSession = this.getDatosSesion();
    if (actualSession) {
      this.setUser(JSON.parse(actualSession));
    }
  }
  /**
   * Verifica si hay una sesion activa 
   * @returns 
   */
  sesionExiste(): boolean {
    let actualSession = this.getDatosSesion();
    return (actualSession) ? true : false;
  }
  /**
   * Permite obtener los dato de la sesión activa en el 
   * local storage
   * @returns 
   */
  getDatosSesion() {
    let actualSession = localStorage.getItem('session');
    return actualSession;
  }
}
